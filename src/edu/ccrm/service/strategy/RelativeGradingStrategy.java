package edu.ccrm.service.strategy;

import edu.ccrm.domain.Grade;

/**
 * Strategy Pattern: Lenient/curved grading with lower thresholds.
 * 85+ = S, 75+ = A, 65+ = B, 55+ = C, 45+ = D, 35+ = E, below = F.
 */
public class RelativeGradingStrategy implements GradingStrategy {
    @Override
    public Grade determineGrade(int marks) {
        if (marks >= 85) return Grade.S;
        if (marks >= 75) return Grade.A;
        if (marks >= 65) return Grade.B;
        if (marks >= 55) return Grade.C;
        if (marks >= 45) return Grade.D;
        if (marks >= 35) return Grade.E;
        return Grade.F;
    }
}
