package edu.ccrm.service.mediator;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

/**
 * Mediator Pattern: Interface for coordinating between services.
 * Services communicate through the mediator instead of referencing each other directly.
 */
public interface ServiceMediator {
    Student findStudent(String regNo);
    Course findCourse(String courseCode);
}
