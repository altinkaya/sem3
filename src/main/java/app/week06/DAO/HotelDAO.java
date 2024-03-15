package app.week06.DAO;

import app.week06.Persistence.Hotel;
import app.week06.Persistence.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class HotelDAO extends AbstractDAO<Hotel> {

    private static HotelDAO instance;
    private static EntityManagerFactory emf;

    private HotelDAO(EntityManagerFactory _emf, Class<Hotel> entityClass) {
        super(_emf, entityClass);
    }

    public static HotelDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HotelDAO(emf, Hotel.class);
        }
        return instance;
    }

    public List<Hotel> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h", Hotel.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Hotel hotelFound = em.find(Hotel.class, id);

            if (hotelFound != null) {
                for (Room room : hotelFound.getRooms()) {
                    em.remove(room);
                }

                em.remove(hotelFound);

                em.getTransaction().commit();
                return 1;
            }
            return 0;
        } finally {
            em.close();
        }
    }
}
