package app.week03;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

public class JPALifecycle {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public static void main(String[] args) {
        // entity is in transient state
        Student student = new Student("Michelle", "Schmidt", "schmidt@mail.com", 30);
        createStudent(student);
    }

    public static void createStudent(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        }
    }

    public static Student readStudent(int id) {
        Student student;
        try (EntityManager em = emf.createEntityManager()) {
            student = em.find(Student.class, id);
        }
        return student;
    }

    public static Student updateStudent(Student updStd) {
        Student student;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            student = em.merge(updStd);
            em.getTransaction().commit();
        }
        return student;
    }

    public static void deleteStudent(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
                em.getTransaction().commit();
            }
        }
    }

    public static List<Student> readAllStudents() {
        List<Student> students;
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();
        }
        return students;
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
