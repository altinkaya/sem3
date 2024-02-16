package app.week03;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

public class JPALifecycle {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALifecycle");

    public static void main(String[] args) {
        Student student = new Student("Michelle", "Schmidt", "schmidt@mail.com", 30);
        createStudent(student);
    }

    public static void createStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public static Student readStudent(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public static Student updateStudent(Student updStd) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.merge(updStd);
            em.getTransaction().commit();
            return student;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public static void deleteStudent(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public static List<Student> readAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table(name = "students")
    public static class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String firstName;
        private String lastName;
        @Column(unique = true)
        private String email;
        private int age;

        public Student(String firstName, String lastName, String email, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.age = age;
        }

        @PrePersist
        @PreUpdate
        private void validateEmail() {
            if (!Pattern.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$", email)) {
                throw new IllegalArgumentException("Invalid email address");
            }
        }
    }
}
