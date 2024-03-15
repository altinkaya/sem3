package app.week06.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
    List<T> getAll();
    T getById(int id);
    T create(T entity);
    int update(T entity);
    int delete(int id);
}
