package edu.ccrm.cli.command;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import edu.ccrm.domain.Student;
import edu.ccrm.repository.IStudentRepository;
import edu.ccrm.service.StudentService;
import edu.ccrm.util.InputValidator;

public class ManageStudentsCommand implements MenuCommand {
    private final StudentService studentService;
    private final IStudentRepository studentRepo;

    public ManageStudentsCommand(StudentService studentService, IStudentRepository studentRepo) {
        this.studentService = studentService;
        this.studentRepo = studentRepo;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.println("3. View Student Profile & Transcript");
        System.out.println("4. Update Student Status");

        int choice = InputValidator.getInt(scanner, "Enter choice: ");
        switch (choice) {
            case 1 -> addStudent(scanner);
            case 2 -> listStudents();
            case 3 -> viewTranscript(scanner);
            case 4 -> updateStatus(scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addStudent(Scanner scanner) {
        System.out.println("Enter student details:");
        String regNo = InputValidator.getString(scanner, "Registration No: ");
        String name = InputValidator.getString(scanner, "Full Name: ");
        String email = InputValidator.getString(scanner, "Email: ");
        LocalDate dob = LocalDate.parse(InputValidator.getString(scanner, "Date of Birth (YYYY-MM-DD): "));

        // Logic using repo size for ID
        int newId = studentRepo.findAll().size() + 1;
        Student newStudent = new Student(newId, regNo, name, email, dob);
        studentService.addStudent(newStudent);
        System.out.println("Student added successfully.");
    }

    private void listStudents() {
        System.out.println("\n--- List of All Students ---");
        studentService.getAllStudents().forEach(System.out::println);
    }

    private void viewTranscript(Scanner scanner) {
        String regNo = InputValidator.getString(scanner, "Enter Student Registration No: ");
        try {
            System.out.println(studentService.getStudentTranscript(regNo));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void updateStatus(Scanner scanner) {
        String regNo = InputValidator.getString(scanner, "Enter Student Registration No: ");
        System.out.println("Available statuses: " + Arrays.toString(Student.StudentStatus.values()));
        String statusStr = InputValidator.getString(scanner, "Enter new status: ").toUpperCase();
        try {
            Student.StudentStatus status = Student.StudentStatus.valueOf(statusStr);
            studentService.updateStudentStatus(regNo, status);
            System.out.println("Status updated.");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid status provided.");
        }
    }
}