package app.week04.SchoolExercise.dao;

import app.week04.SchoolExercise.dao.Student;
import app.week04.SchoolExercise.dao.Teacher;
import app.week04.SchoolExercise.dao.Semester;
import java.util.List;

public interface StudentDAO {
    List<Student> findAllStudentsByFirstName(String firstName);
    List<Student> findAllStudentsByLastName(String lastName);
    long findTotalNumberOfStudentsBySemester(String semesterName);
    long findTotalNumberOfStudentsByTeacher(Teacher teacher);
    Teacher findTeacherWithMostSemesters();
    Semester findSemesterWithFewestStudents();
    StudentInfo getAllStudentInfo(int id);
}
