package app.week06;


import app.week06.REST.ApplicationConfig;
import app.week06.Routes.Route;

public class Main {
    public static void main(String[] args) {
        ApplicationConfig app = ApplicationConfig.getInstance();
        app.initiateServer()
                .startServer(7007)
                .setExceptionHandlers()
                .setRoute(Route.addRoutes());
    }
}
