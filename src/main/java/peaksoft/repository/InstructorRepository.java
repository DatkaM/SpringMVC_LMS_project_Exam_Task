package peaksoft.repository;

import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorRepository {

    void saveInstructorByCompanyId(Long id,Instructor instructor);

    void deleteInstructorById(Long id);

    Instructor getInstructorById(Long id);

    void updateInstructor(Long id,Instructor instructor);

    List<Instructor> getAllInstructorsByCompanyId(Long companyId);

    void assignInstructorToCourse(Long insId,Long courseId);

}
