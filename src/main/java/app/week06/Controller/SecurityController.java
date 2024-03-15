package app.week06.Controller;

import app.week06.DAO.UserDAO;

import app.week06.DTO.TokenDTO;
import app.week06.DTO.UserDTO;
import app.week06.Persistence.HibernateConfig;
import app.week06.Persistence.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

public class SecurityController {

    private static UserDAO userDAO;

    // Random string generator is used to generate a secret key at 32bytes (256 bits)
    private static String SECRET_KEY = "4iarp1bJRQMPshCQ4AOA5IlLKHswfVZT";


    public SecurityController(UserDAO userDAO) {
        if (userDAO == null) {
            throw new IllegalArgumentException("UserDAO and RoleDAO must not be null");
        }
        this.userDAO = userDAO;
    }

    public Handler authenticate() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnObject = objectMapper.createObjectNode();
        return (ctx) -> {
            if (ctx.method().toString().equals("OPTIONS")) {
                ctx.status(200);
                return;
            }
            String header = ctx.header("Authorization");
            if (header == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header missing"));
                return;
            }
            String token = header.split(" ")[1];
            if (token == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header malformed"));
                return;
            }
            UserDTO verifiedTokenUser = verifyToken(token);
            if (verifiedTokenUser == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Invalid User or Token"));
            }
            System.out.println("USER IN AUTHENTICATE: " + verifiedTokenUser);
            ctx.attribute("user", verifiedTokenUser);
        };
    }

    private UserDTO verifyToken(String token) {
        try {
            // Parse the token and extract claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Extract username from claims
            String username = claims.getSubject();

            // Fetch user information from database using username
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
            User user;
            try (EntityManager em = emf.createEntityManager()) {
                user = em.find(User.class, username);
            }

            // Return UserDTO if user exists
            return user != null ? new UserDTO(user) : null;
        } catch (JwtException | IllegalArgumentException e) {
            // Token parsing failed or invalid
            e.printStackTrace();
            return null;
        }
    }


    public Handler login() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode(); // for sending json messages back to the client
            try {
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                User verifiedUserEntity = userDAO.getVerifiedUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));

            } catch (EntityNotFoundException | ValidationException e) {
                ctx.status(401);
                System.out.println(e.getMessage());
                ctx.json(returnObject.put("msg", e.getMessage()));
            }
        };
    }

    public String createToken(UserDTO user) throws JOSEException {
        try {
            String ISSUER;
            String TOKEN_EXPIRE_TIME;

            if (System.getenv("DEPLOYED") != null) {
                ISSUER = System.getenv("ISSUER");
                TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
                SECRET_KEY = System.getenv("SECRET_KEY");
            } else {
                ISSUER = "hotelopg";
                TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
            }

            if (SECRET_KEY == null) {
                throw new IllegalStateException("Secret key is null");
            }

            return createToken(user, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new JOSEException("Could not create token", e);
        }
    }

    public String createToken(UserDTO user, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY) throws
            JOSEException {
        try {

            if (user == null) {
                throw new IllegalArgumentException("UserDTO is null");
            }

            Set<String> roles = user.getRoles();
            String rolesString = String.join(",", roles);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer(ISSUER)
                    .claim("username", user.getUsername())
                    .claim("roles", rolesString)
                    .expirationTime(new Date(System.currentTimeMillis() + Long.parseLong(TOKEN_EXPIRE_TIME)))
                    .build();
            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSSigner signer = new MACSigner(SECRET_KEY);

            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            JWSObject jwsObject = new JWSObject(jwsHeader, payload);


            jwsObject.sign(signer);


            return jwsObject.serialize();

        } catch (Exception e) {

            e.printStackTrace();
            throw new JOSEException("Could not create token", e);
        }
    }

    public Handler register() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode();
            try {
                UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
                User created = userDAO.createUser(userInput.getUsername(), userInput.getPassword());

                String token = createToken(new UserDTO(created));
                ctx.status(HttpStatus.CREATED).json(new TokenDTO(token, userInput.getUsername()));
            } catch (EntityExistsException e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                ctx.json(returnObject.put("msg", "User already exists"));
            }
        };
    }


    public Handler assignRoleToUser() {
        return ctx -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode();

            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
            if (userDTO == null || userDTO.getUsername() == null || userDTO.getRolesAsStrings() == null) {
                ctx.status(HttpStatus.BAD_REQUEST).json(returnObject.put("error", "Invalid input data"));
                return;
            }

            try {
                // Assign role to user using DAO
                User updatedUser = userDAO.addUserRole(userDTO.getUsername(), userDTO.getRolesAsStrings());

                if (updatedUser != null) {
                    returnObject.put("msg", "Role assigned successfully");
                    ctx.status(HttpStatus.OK).json(returnObject);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).json(returnObject.put("error", "User not found"));
                }
            } catch (Exception e) {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).json(returnObject.put("error", "Error assigning role to user"));
            }
        };
    }

}
