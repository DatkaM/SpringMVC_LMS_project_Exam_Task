package peaksoft.repository;

import peaksoft.entity.Course;

import java.util.List;

public interface CourseRepository {

    void saveCourseByCompanyId(Long id,Course course);

    void deleteCourseById(Long id);

    Course getCourseById(Long id);

    List<Course> getAllCoursesById(Long id);

    void updateCourseById(Long id, Course course);
}
