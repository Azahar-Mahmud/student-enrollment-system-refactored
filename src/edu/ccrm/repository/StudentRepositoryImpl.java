package edu.ccrm.repository;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentRepositoryImpl implements IStudentRepository {
    private final Map<String, Student> storage = new HashMap<>();

    @Override
    public void save(Student student) {
        storage.put(student.getRegNo(), student);
    }

    @Override
    public Optional<Student> findByRegNo(String regNo) {
        return Optional.ofNullable(storage.get(regNo));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(storage.values());
    }
}