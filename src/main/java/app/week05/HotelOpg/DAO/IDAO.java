package app.week05.HotelOpg.DAO;

import java.util.List;

public interface IDAO<T> {
    List<T> getAll();
    T getById(int id);
    void create(T t);
    void update(T t);
    void delete(int id);
}
