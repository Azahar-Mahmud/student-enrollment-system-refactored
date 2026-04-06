package edu.ccrm.factory;

import edu.ccrm.domain.Instructor;
import java.time.LocalDate;

/**
 * Factory Method Pattern: Concrete factory that creates Instructor objects.
 * extras[0] = department, extras[1] = officeLocation
 */
public class InstructorFactory extends PersonFactory {
    @Override
    public Instructor createPerson(int id, String fullName, String email,
                                   LocalDate dateOfBirth, String... extras) {
        String department = extras[0];
        String officeLocation = extras[1];
        return new Instructor(id, fullName, email, dateOfBirth, department, officeLocation);
    }
}
