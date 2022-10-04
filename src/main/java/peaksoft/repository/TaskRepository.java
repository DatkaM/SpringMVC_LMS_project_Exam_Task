package peaksoft.repository;


import peaksoft.entity.Lesson;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskRepository {

    void saveTaskByLessonById(Long id, Task task);

    void deleteTask(Long id);

    void updateTaskById(Long id, Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasksByLessonId(Long id);


}
