package peaksoft.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_of_start")
    private LocalDate dateOfStart;
    private String duration;
    @Column(length = 100000)
    private String image;
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH })
    private Company company;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "courses")
    private List<Instructor> instructors;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "course")
    private List<Student> students;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Lesson> lessons;

    public Course(String courseName, LocalDate dateOfStart, String duration, String image, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.image = image;
        this.description = description;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

}
