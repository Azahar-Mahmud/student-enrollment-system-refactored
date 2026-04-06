package edu.ccrm.service.strategy;

import edu.ccrm.domain.Grade;

/**
 * Strategy Pattern: Interface for interchangeable grading algorithms.
 * Different grading policies can be swapped without changing the EnrollmentService.
 */
public interface GradingStrategy {
    Grade determineGrade(int marks);
}
