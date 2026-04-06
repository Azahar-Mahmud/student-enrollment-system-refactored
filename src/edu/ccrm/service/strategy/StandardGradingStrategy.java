package edu.ccrm.service.strategy;

import edu.ccrm.domain.Grade;

/**
 * Strategy Pattern: Standard absolute grading (current 10-point scale).
 * 90+ = S, 80+ = A, 70+ = B, 60+ = C, 50+ = D, 40+ = E, below = F.
 */
public class StandardGradingStrategy implements GradingStrategy {
    @Override
    public Grade determineGrade(int marks) {
        if (marks >= 90) return Grade.S;
        if (marks >= 80) return Grade.A;
        if (marks >= 70) return Grade.B;
        if (marks >= 60) return Grade.C;
        if (marks >= 50) return Grade.D;
        if (marks >= 40) return Grade.E;
        return Grade.F;
    }
}
