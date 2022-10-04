package peaksoft.repository;

import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

public interface StudentRepository {

     void saveStudentByCompanyId(Long id, Student student);

     void deleteStudentById(Long id);

     void updateStudentById(Long id,Student student);

     List<Student> getAllStudentByCompanyId(Long companyId);

     Student getStudentById(Long id);

//     void assignStudentToCourse(Long courseId,Long studentId);

}
