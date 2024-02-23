package app.week04.SchoolExercise.dao;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String semesterName;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "teacher_semester",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

    public Semester() {
    }

    public Semester(String semesterName, String description) {
        this.semesterName = semesterName;
        this.description = description;
    }

    public void setTeacher(Teacher teacher1) {
        this.teachers = Collections.singletonList(teacher1);
    }
}
