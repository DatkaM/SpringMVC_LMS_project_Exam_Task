package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {


    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveLessonByCourseId(Long id, Lesson lesson) {
        lessonRepository.saveLessonByCourseId(id,lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteLesson(id);
    }

    @Override
    public void updateLessonById(Long id, Lesson lesson) {
        lessonRepository.updateLessonById(id,lesson);
    }

    @Override
    public List<Lesson> getAllLessonsByCourseId(Long id) {
        return lessonRepository.getAllLessonsByCourseId(id);
    }
}
