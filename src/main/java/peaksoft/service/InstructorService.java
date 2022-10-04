package peaksoft.service;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {

    void saveInstructorByCompanyId(Long id,Instructor instructor);

    void deleteInstructorById(Long id);

    void updateInstructor(Long id,Instructor instructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructorsByCompanyId(Long companyId);

    void assignInstructorToCourse(Long insId,Long courseId);

}
