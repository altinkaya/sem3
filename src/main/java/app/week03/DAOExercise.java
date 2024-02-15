package app.week03;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class DAOExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");
        EntityManager em = emf.createEntityManager();
        StudentDAO studentDAO = new StudentDAOImpl(em);

        try {
            // Create student
            Student student = new Student();
            student.setName("John Doe");
            student.setEmail("john.doe@example.com");
            studentDAO.create(student);

            // Read student
            Student readStudent = studentDAO.read(student.getId());
            System.out.println(readStudent.getName());

            // Update student
            readStudent.setName("Jane Doe");
            studentDAO.update(readStudent);

            // Delete student
            studentDAO.delete(readStudent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;
        @Temporal(TemporalType.TIMESTAMP)
        private Date updatedAt;

        @PrePersist
        protected void onCreate() {
            createdAt = new Date();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = new Date();
        }
    }

    public interface StudentDAO {
        void create(Student student);
        Student read(Long id);
        void update(Student student);
        void delete(Student student);
        List<Student> getAllStudents();
    }

    public static class StudentDAOImpl implements StudentDAO {
        private EntityManager entityManager;

        public StudentDAOImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public void create(Student student) {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }

        @Override
        public Student read(Long id) {
            return entityManager.find(Student.class, id);
        }

        @Override
        public void update(Student student) {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        }

        @Override
        public void delete(Student student) {
            entityManager.getTransaction().begin();
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        }

        @Override
        public List<Student> getAllStudents() {
            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        }
    }
}
