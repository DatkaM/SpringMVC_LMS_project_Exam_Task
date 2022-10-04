package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudentByCompanyId(Long id, Student student) {
        Company company = entityManager.find(Company.class,id);
        student.setCompany(company);
        company.addStudent(student);
        entityManager.merge(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = entityManager.find(Student.class,id);
//        student.setCompany(null);
//        student.setCourse(null);
        entityManager.remove(student);
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public List<Student> getAllStudentByCompanyId(Long companyId) {
        return entityManager.createQuery("select s from Student s where s.company.id=:companyId",Student.class).
                setParameter("companyId",companyId).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }
//
//    @Override
//    public void assignStudentToCourse(Long courseId, Long studentId) {
//        Course course = entityManager.find(Course.class,courseId);
//        Student student = entityManager.find(Student.class,studentId);
//        course.addStudent(student);
//        student.setCourse(course);
//        entityManager.persist(student);
//        entityManager.persist(course);
//    }
}
