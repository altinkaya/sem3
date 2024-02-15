package app.week03;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

public class ThePointexercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pointsPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            for (int i = 0; i < 1000; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();


            Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
            System.out.println("Total Points: " + q1.getSingleResult());


            Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
            System.out.println("Average X: " + q2.getSingleResult());


            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            List<Point> results = query.getResultList();
            for (Point p : results) {
                System.out.println(p);
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    @Entity
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Point {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
