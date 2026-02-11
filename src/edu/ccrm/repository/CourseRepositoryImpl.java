package edu.ccrm.repository;

import edu.ccrm.domain.Course;
import java.util.*;

public class CourseRepositoryImpl implements ICourseRepository {
    private final Map<String, Course> storage = new HashMap<>();

    @Override
    public void save(Course course) {
        storage.put(course.getCourseCode().getFullCode(), course);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return Optional.ofNullable(storage.get(code));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(storage.values());
    }
}