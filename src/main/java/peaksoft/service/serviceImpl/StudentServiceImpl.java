package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Company;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void saveStudentByCompanyId(Long id, Student student) {
        studentRepository.saveStudentByCompanyId(id, student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentById(id);
    }

    @Override
    public void updateStudentById(Long id, Student student) {
        studentRepository.updateStudentById(id, student);
    }

    @Override
    public List<Student> getAllStudentByCompanyId(Long companyId) {
        return studentRepository.getAllStudentByCompanyId(companyId);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }
}
