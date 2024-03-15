package app.week06.REST;

import app.week06.DTO.UserDTO;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationConfig {
    private ObjectMapper om = new ObjectMapper();
    private static ApplicationConfig instance;
    private Javalin app;

    private ApplicationConfig() {
        // Initialize ObjectMapper
        ObjectMapper om = new ObjectMapper();

        // Initialize Javalin app
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/json";
            config.routing.contextPath = "/api";
        });
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public ApplicationConfig initiateServer() {
        return instance;
    }

    public ApplicationConfig startServer(int portNumber) {
        app.start(portNumber);
        return instance;
    }

    public ApplicationConfig setRoute(EndpointGroup route) {
        app.routes(route);
        return instance;
    }

    public ApplicationConfig setExceptionHandlers() {

        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
            ctx.status(500).json(node);
        });

        app.error(404, ctx -> {
            ctx.status(404).result("Not Found");
        });

        app.exception(IllegalStateException.class, (e, ctx) -> {
            ctx.status(400).result("Bad Request: " + e.getMessage());
        });

        return instance;
    }
    public ApplicationConfig checkSecurityRoles() {
        ObjectMapper objectMapper = new ObjectMapper(); // Assuming objectMapper is available in the scope

        app.updateConfig(config -> {
            config.accessManager((handler, ctx, permittedRoles) -> {
                Set<String> allowedRoles = new HashSet<>(); // Initialize allowedRoles with the permittedRoles

                // Check if the request method is OPTIONS or if ANYONE role is allowed
                if (allowedRoles.contains("ANYONE") || ctx.method().equals("OPTIONS")) {
                    handler.handle(ctx);
                    return;
                }

                // Retrieve the authenticated user from the context attribute
                UserDTO user = ctx.attribute("user");

                // Check if the user is authenticated
                if (user == null) {
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(objectMapper.createObjectNode()
                                    .put("msg", "Not authorized. No user information available."));
                    return;
                }

                // Check if the user's role is allowed to access the route
                if (allowedRoles.stream().anyMatch(role -> user.getRoles().contains(role))) {
                    handler.handle(ctx); // Allow the request to proceed
                } else {
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(objectMapper.createObjectNode()
                                    .put("msg", "Not authorized. Insufficient permissions."));
                }
            });
        });

        return instance; // Assuming instance is declared and initialized somewhere in your code
    }


    public void stopServer() {
        app.stop();
    }
}
