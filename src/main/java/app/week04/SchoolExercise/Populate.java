package app.week04.SchoolExercise;

import app.week03.HibernateConfig;
import app.week04.SchoolExercise.dao.Semester;
import app.week04.SchoolExercise.dao.Student;
import app.week04.SchoolExercise.dao.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();


            Teacher teacher1 = new Teacher("John", "Doe");
            Teacher teacher2 = new Teacher("Jane", "Doe");
            Teacher teacher3 = new Teacher("Jens", "Jensen");


            Semester semester1 = new Semester("vinter 2022", "datamatiker uddannelse 2022-2023");
            Semester semester2 = new Semester("sommer 2023", "datamatiker uddannelse 2023-2024");
            Semester semester3 = new Semester("efterår", "datamatiker uddannelse 2024-2025");



            Student student1 = new Student("Anders", "Andersen");
            Student student2 = new Student("Bente", "Bentsen");
            Student student3 = new Student("Carsten", "Carstensen");
            Student student4 = new Student("Dorthe", "Dorthesen");



            semester1.setTeacher(teacher1);
            semester2.setTeacher(teacher2);
            semester3.setTeacher(teacher1);
            semester3.setTeacher(teacher2);


            student1.setSemester(semester1);
            student2.setSemester(semester2);
            student3.setSemester(semester3);
            student4.setSemester(semester3);



            em.persist(teacher1);
            em.persist(teacher2);
            em.persist(teacher3);

            em.persist(semester1);
            em.persist(semester2);
            em.persist(semester3);

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
