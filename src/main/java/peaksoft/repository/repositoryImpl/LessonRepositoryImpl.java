package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void saveLessonByCourseId(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class,id);
        lesson.setCourse(course);
        course.addLesson(lesson);
        entityManager.persist(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = entityManager.find(Lesson.class,id);
        lesson.setCourse(null);
        entityManager.remove(lesson);
    }

    @Override
    public void updateLessonById(Long id, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public List<Lesson> getAllLessonsByCourseId(Long id) {
        return entityManager.createQuery("select l from Lesson l where l.course.id = :id",Lesson.class).
                setParameter("id",id).getResultList();
    }
}
