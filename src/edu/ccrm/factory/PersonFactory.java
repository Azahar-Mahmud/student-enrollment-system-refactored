package edu.ccrm.factory;

import edu.ccrm.domain.Person;
import java.time.LocalDate;

/**
 * Factory Method Pattern: Abstract factory for creating Person objects.
 * Subclasses (StudentFactory, InstructorFactory) decide which concrete class to instantiate.
 */
public abstract class PersonFactory {
    public abstract Person createPerson(int id, String fullName, String email,
                                        LocalDate dateOfBirth, String... extras);
}
