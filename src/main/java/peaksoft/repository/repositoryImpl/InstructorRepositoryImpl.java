package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveInstructorByCompanyId(Long id,Instructor instructor) {
        Company company = entityManager.find(Company.class,id);
        instructor.setCompany(company);
        company.addInstructor(instructor);
        entityManager.merge(instructor);
    }


    @Override
    public void deleteInstructorById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
//        instructor.setCompany(null);
//        for (Course c : instructor.getCourses()) {
//            c.setInstructors(null);
//        }
        entityManager.remove(instructor);

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class,id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);
    }

    @Override
    public List<Instructor> getAllInstructorsByCompanyId(Long id) {
        return entityManager.createQuery("select i from Instructor i where i.company.id=:id", Instructor.class).setParameter("id",id).getResultList();
    }

    @Override
    public void assignInstructorToCourse(Long insId, Long courseId) {
        Instructor instructor = entityManager.find(Instructor.class,insId);
        Course course = entityManager.find(Course.class,courseId);
        instructor.addCourse(course);
        course.addInstructor(instructor);
        entityManager.persist(course);
        entityManager.persist(instructor);
    }
}
