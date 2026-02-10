package edu.ccrm.repository;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    void save(Student student);
    Optional<Student> findByRegNo(String regNo);
    List<Student> findAll();
}