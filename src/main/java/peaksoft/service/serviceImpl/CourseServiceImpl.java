package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public void saveCourseByCompanyId(Long id,Course course) {
        courseRepository.saveCourseByCompanyId(id,course);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteCourseById(id);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public List<Course> getAllCoursesById(Long id) {
        return courseRepository.getAllCoursesById(id);
    }

    @Override
    public void updateCourseById(Long id, Course course) {
        courseRepository.updateCourseById(id, course);
    }
}
