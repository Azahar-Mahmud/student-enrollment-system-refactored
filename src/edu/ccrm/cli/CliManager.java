package edu.ccrm.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.ccrm.cli.command.ManageCoursesCommand;
import edu.ccrm.cli.command.ManageDataCommand;
import edu.ccrm.cli.command.ManageEnrollmentsCommand;
import edu.ccrm.cli.command.ManageStudentsCommand;
import edu.ccrm.cli.command.MenuCommand;
import edu.ccrm.cli.command.ShowReportsCommand;
import edu.ccrm.config.AppConfig;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.repository.CourseRepositoryImpl;
import edu.ccrm.repository.ICourseRepository;
import edu.ccrm.repository.IInstructorRepository;
import edu.ccrm.repository.IStudentRepository;
import edu.ccrm.repository.InstructorRepositoryImpl;
import edu.ccrm.repository.StudentRepositoryImpl;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.ReportService;
import edu.ccrm.service.StudentService;
import edu.ccrm.util.InputValidator;

public class CliManager {
    private static final Scanner scanner = new Scanner(System.in);

    private static final IStudentRepository studentRepo = new StudentRepositoryImpl();
    private static final ICourseRepository courseRepo = new CourseRepositoryImpl();
    private static final IInstructorRepository instructorRepo = new InstructorRepositoryImpl();

    private static final StudentService studentService = new StudentService(studentRepo);
    private static final CourseService courseService = new CourseService(courseRepo);
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static final ReportService reportService = new ReportService(studentRepo);
    private static final ImportExportService importExportService = new ImportExportService(studentRepo, courseRepo, instructorRepo);
    private static final BackupService backupService = new BackupService();

    private static final Map<Integer, MenuCommand> commands = new HashMap<>();

    static {
        commands.put(1, new ManageStudentsCommand(studentService, studentRepo));
        commands.put(2, new ManageCoursesCommand(courseService));
        commands.put(3, new ManageEnrollmentsCommand(enrollmentService));
        commands.put(4, new ManageDataCommand(importExportService, backupService));
        commands.put(5, new ShowReportsCommand(reportService));
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Course & Enrollment Management System!");
        System.out.println("Configuration loaded. Data directory: " + AppConfig.getInstance().getDataDirectory());

        importExportService.importData();

        while (true) {
            printMainMenu();
            int choice = InputValidator.getInt(scanner, "Enter your choice: ");

            if (choice == 6) {
                System.out.println("Exiting application...");
                printPlatformNote();
                break;
            }

            MenuCommand command = commands.get(choice);
            if (command != null) {
                command.execute(scanner);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments & Grades");
        System.out.println("4. Import/Export & Backup");
        System.out.println("5. View Reports");
        System.out.println("6. Exit");
    }

    private static void printPlatformNote() {
        System.out.println("\n--- Java Platform Summary ---");
        System.out.println(" * Java SE (Standard Edition): Core Java platform for desktop and server applications.");
        System.out.println(" * Java EE (Enterprise Edition): Built on SE, adds APIs for large-scale, distributed enterprise applications.");
        System.out.println(" * Java ME (Micro Edition): A subset of SE for resource-constrained devices.");
        System.out.println("-----------------------------\n");
    }
}