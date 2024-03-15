package app.week06.Routes;

import app.week06.Controller.RoomController;
import app.week06.DAO.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomRoute {
    private static RoomDAO roomDAO;

    public RoomRoute(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public EndpointGroup roomRoutes() {
        return () -> {
            get("/rooms", RoomController.getAll(roomDAO));
            get("/rooms/{id}", RoomController.getRoom(roomDAO));
            post("/rooms", RoomController.create(roomDAO));
            put("/rooms/{id}", RoomController.update(roomDAO));
            delete("/rooms/{id}", RoomController.delete(roomDAO));
        };
    }
}
