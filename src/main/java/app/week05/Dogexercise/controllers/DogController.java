package app.week05.Dogexercise.controllers;

import app.week05.Dogexercise.dtos.DogDTO;
import java.util.HashMap;
import java.util.Map;

public class DogController {

    private Map<Integer, DogDTO> dogs = new HashMap<>();

    public DogController() {
        dogs.put(1, new DogDTO(1, "Fido", "Golden Retriever", 3));
        dogs.put(2, new DogDTO(2, "Rex", "German Shepherd", 5));
        dogs.put(3, new DogDTO(3, "Buddy", "Labrador Retriever", 2));
        dogs.put(4, new DogDTO(4, "Max", "Beagle", 4));
        dogs.put(5, new DogDTO(5, "Rocky", "Bulldog", 6));
    }

    public Map<Integer, DogDTO> getAllDogs() {
        return dogs;
    }

    public DogDTO getDogById(int id) {
        return dogs.get(id);
    }

    public void createDog(DogDTO dog) {
        dogs.put(dog.getId(), dog);
    }

    public void updateDog(int id, DogDTO dog) {
        dogs.put(id, dog);
    }

    public void deleteDog(int id) {
        dogs.remove(id);
    }
}
