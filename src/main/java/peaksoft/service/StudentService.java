package peaksoft.service;

import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {

    void saveStudentByCompanyId(Long id, Student student);

    void deleteStudentById(Long id);

    void updateStudentById(Long id,Student student);

    List<Student> getAllStudentByCompanyId(Long companyId);

    Student getStudentById(Long id);
}
