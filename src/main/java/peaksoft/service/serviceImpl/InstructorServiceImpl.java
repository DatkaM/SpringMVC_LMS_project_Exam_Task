package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Instructor;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {


    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void saveInstructorByCompanyId(Long id,Instructor instructor) {
        instructorRepository.saveInstructorByCompanyId(id,instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteInstructorById(id);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        instructorRepository.updateInstructor(id, instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id);
    }


    @Override
    public List<Instructor> getAllInstructorsByCompanyId(Long companyId) {
        return instructorRepository.getAllInstructorsByCompanyId(companyId);
    }

    @Override
    public void assignInstructorToCourse(Long insId, Long courseId) {
        instructorRepository.assignInstructorToCourse(insId, courseId);
    }
}
