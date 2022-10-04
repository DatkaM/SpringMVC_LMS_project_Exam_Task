package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void saveCourseByCompanyId(Long id, Course course) {
        Company company = manager.find(Company.class, id);
        course.setCompany(company);
        company.addCourse(course);
        manager.persist(course);
        //        if(myEntity.getId()>0){
//            manager.persist(myEntity);
//        } else{
//            manager.merge(myEntity);
//        }
    }


    @Override
    public void deleteCourseById(Long id) {
        Course course = manager.find(Course.class, id);
//        course.setCompany(null);
//        for (Instructor i : course.getInstructors()) {
//            i.setCourses(null);
//        }
        manager.remove(course);

    }

    @Override
    public Course getCourseById(Long id) {
        return manager.find(Course.class,id);
    }


    @Override
    public List<Course> getAllCoursesById(Long id) {
        return manager.createQuery("select c from Course c where c.company.id=:id", Course.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void updateCourseById(Long id, Course course) {
        Course course1 = manager.find(Course.class, id);
        course1.setCourseName(course.getCourseName());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        course1.setDescription(course.getDescription());
        manager.persist(course1);
    }
}
