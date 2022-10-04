package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    @GeneratedValue(generator = "lesson_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "lesson_name", unique = true)
    private String lessonName;

    public Lesson(String lessonName, Course course) {
        this.lessonName = lessonName;
        this.course = course;
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    private Course course;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Task> tasks;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lesson")
    private Video video;


    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }
    public void addTask(Task task){
        tasks.add(task);
    }
}
