package app.week04.SchoolExercise.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

        private final EntityManager entityManager;

        public StudentDAOImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName", Student.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }


    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastname", Student.class);
        query.setParameter("lastname", lastName);
        return query.getResultList();
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Student s JOIN s.semester sem WHERE sem.name = :semesterName", Long.class);
        query.setParameter("semesterName", semesterName);
        return query.getSingleResult();
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Student s JOIN s.semester sem JOIN sem.teacher t WHERE t = :teacher", Long.class);
        query.setParameter("teacher", teacher);
        return query.getSingleResult();
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        TypedQuery<Teacher> query = entityManager.createQuery(
                "SELECT t FROM Teacher t JOIN t.semesters sem GROUP BY t ORDER BY COUNT(sem) DESC", Teacher.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        TypedQuery<Semester> query = entityManager.createQuery(
                "SELECT sem FROM Semester sem LEFT JOIN sem.students s GROUP BY sem ORDER BY COUNT(s) ASC", Semester.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            return new StudentInfo(student, student.getSemester());
        }
        return null;
    }


}
