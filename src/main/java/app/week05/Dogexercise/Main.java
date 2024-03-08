package app.week05.Dogexercise;

import app.week05.Dogexercise.controllers.DogController;
import app.week05.Dogexercise.dtos.DogDTO;
import io.javalin.Javalin;


public class Main {
    public static void main(String[] args) {
        DogController dogController = new DogController();

        Javalin app = Javalin.create().start(7007);

            app.get("/api/dogs/", ctx -> ctx.json(dogController.getAllDogs()));

            app.get("/api/dogs/dog/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DogDTO dog = dogController.getDogById(id);
                if (dog != null) {
                    ctx.json(dog);
                } else {
                    ctx.status(404);
                }
            });
            app.post("/api/dogs/dog", ctx -> {
                DogDTO dog = ctx.bodyAsClass(DogDTO.class);
                dogController.createDog(dog);
                ctx.status(201);
            });
            app.put("/api/dogs/dog/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DogDTO dog = ctx.bodyAsClass(DogDTO.class);
                dogController.updateDog(id, dog);
                ctx.status(204);
            });
            app.delete("/api/dogs/dog/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                dogController.deleteDog(id);
                ctx.status(204);
            });

    }
}
