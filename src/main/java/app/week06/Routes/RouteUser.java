package app.week06.Routes;

import app.week06.Controller.SecurityController;
import app.week06.DAO.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RouteUser {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static SecurityController securityController;

    public RouteUser(UserDAO userDAO ){
        securityController = new SecurityController(userDAO);
    }

    public EndpointGroup getSecurityRoutes() {
        return () -> {
            path("/auth", () -> {
                post("/login", securityController.login());
                post("/register", securityController.register());
                post("/addRole", securityController.assignRoleToUser());
            });
        };
    }



    public EndpointGroup getSecuredRoutes() {
        return () -> {
            path("/protected", () -> {
                before(securityController.authenticate());
                get("/user", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from USER Protected")), Role.USER);
                get("/admin", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), Role.ADMIN);
            });
        };
    }
}




enum Role implements RouteRole { ANYONE, USER, ADMIN }
