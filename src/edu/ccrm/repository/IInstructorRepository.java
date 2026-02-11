package edu.ccrm.repository;

import edu.ccrm.domain.Instructor;
import java.util.Optional;

public interface IInstructorRepository {
    void save(Instructor instructor);
    Optional<Instructor> findById(int id);
}