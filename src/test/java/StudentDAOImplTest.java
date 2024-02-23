import app.week04.SchoolExercise.dao.*;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentDAOImplTest {

    @Mock
    private EntityManager entityManager;

    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        studentDAO = new StudentDAOImpl(entityManager);

        TypedQuery<Student> mockedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Student.class))).thenReturn(mockedQuery);
        when(mockedQuery.setParameter(anyString(), any())).thenReturn(mockedQuery);
        when(mockedQuery.getResultList()).thenReturn(List.of(new Student("Anders", "Andersen")));
    }

    @Test
    void testFindAllStudentsByLastName() {
        String lastName = "Doe";
        List<Student> students = studentDAO.findAllStudentsByLastName(lastName);

        assertFalse(students.isEmpty(), "List of students should not be empty");
        for (Student student : students) {
            assertEquals(lastName, student.getLastName(), "Last name of all students should be " + lastName);
        }
    }

    @Test
    void testFindTotalNumberOfStudentsBySemester() {
        String semesterName = "Fall 2022";
        long count = studentDAO.findTotalNumberOfStudentsBySemester(semesterName);

        assertTrue(count > 0, "The total number of students by semester should be greater than 0");
    }

    @Test
    void testFindTotalNumberOfStudentsByTeacher() {
        Teacher teacher = new Teacher("John", "Doe");
        long count = studentDAO.findTotalNumberOfStudentsByTeacher(teacher);

        assertTrue(count > 0, "The total number of students by teacher should be greater than 0");
    }

    @Test
    void testFindTeacherWithMostSemesters() {
        Teacher teacher = studentDAO.findTeacherWithMostSemesters();

        assertNotNull(teacher, "The teacher with the most semesters should not be null");
    }

    @Test
    void testFindSemesterWithFewestStudents() {
        Semester semester = studentDAO.findSemesterWithFewestStudents();

        assertNotNull(semester, "The semester with the fewest students should not be null");
    }

    @Test
    void testGetAllStudentInfo() {
        int id = 1;
        StudentInfo studentInfo = studentDAO.getAllStudentInfo(id);

        assertNotNull(studentInfo, "The student info should not be null");
        assertEquals(id, studentInfo.getStudent().getId(), "The id of the student info should be " + id);
    }

}
