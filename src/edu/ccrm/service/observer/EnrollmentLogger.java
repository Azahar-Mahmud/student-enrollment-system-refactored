package edu.ccrm.service.observer;

import java.time.LocalDateTime;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

/**
 * Observer Pattern: Concrete observer that logs enrollment events with timestamps.
 */
public class EnrollmentLogger implements EnrollmentObserver {
    @Override
    public void onEnrollment(Student student, Course course) {
        System.out.printf("[AUDIT %s] Student %s (%s) enrolled in %s (%s)%n",
                LocalDateTime.now(),
                student.getFullName(), student.getRegNo(),
                course.getTitle(), course.getCourseCode().getFullCode());
    }

    @Override
    public void onGradeRecorded(Student student, Course course, String grade) {
        System.out.printf("[AUDIT %s] Grade '%s' recorded for %s in %s%n",
                LocalDateTime.now(), grade,
                student.getRegNo(), course.getCourseCode().getFullCode());
    }
}
