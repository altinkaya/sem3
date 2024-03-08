package app.week05.HotelOpg.DAO;

import java.util.List;


public abstract class DAO implements IDAO{
    public void getAll() {
        System.out.println("Getting all");
    }

    public void getById() {
        System.out.println("Getting by id");

    }

}
