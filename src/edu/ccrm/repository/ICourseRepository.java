package edu.ccrm.repository;

import edu.ccrm.domain.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void save(Course course);
    Optional<Course> findByCode(String courseCode);
    List<Course> findAll();
}