package peaksoft.service;

import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonService {
    void saveLessonByCourseId(Long id, Lesson lesson);

    Lesson getLessonById(Long id);
    void deleteLesson(Long id);

    void updateLessonById(Long id,Lesson lesson);

    List<Lesson> getAllLessonsByCourseId(Long id);
}
