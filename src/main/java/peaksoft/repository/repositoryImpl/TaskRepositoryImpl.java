package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveTaskByLessonById(Long id, Task task) {
        Lesson lesson = entityManager.find(Lesson.class,id);
        task.setLesson(lesson);
        lesson.addTask(task);
        entityManager.persist(task);
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.remove(entityManager.find(Task.class,id));
    }

    @Override
    public void updateTaskById(Long id, Task task) {
        Task task1 = entityManager.find(Task.class,id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadLine(task.getDeadLine());
        entityManager.merge(task1);
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class,id);
    }

    @Override
    public List<Task> getAllTasksByLessonId(Long id) {
        return entityManager.createQuery("select t from Task t where t.lesson.id=:id",Task.class).
                setParameter("id",id).getResultList();
    }
}
