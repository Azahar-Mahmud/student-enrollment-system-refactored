package edu.ccrm.repository;

import edu.ccrm.domain.Instructor;
import java.util.*;

public class InstructorRepositoryImpl implements IInstructorRepository {
    private final Map<Integer, Instructor> storage = new HashMap<>();

    @Override
    public void save(Instructor instructor) {
        storage.put(instructor.getId(), instructor);
    }

    @Override
    public Optional<Instructor> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }
}