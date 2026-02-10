package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Instructor;
import edu.ccrm.io.template.*;
import edu.ccrm.repository.*;

public class ImportExportService {
    private final IInstructorRepository instructorRepo;
    private final List<CsvImportHandler> importHandlers;
    private final List<CsvExportHandler<?>> exportHandlers;
    private final Path dataDir;

    public ImportExportService(IStudentRepository studentRepo, 
                               ICourseRepository courseRepo, 
                               IInstructorRepository instructorRepo) {
        this.instructorRepo = instructorRepo;
        this.dataDir = Paths.get(AppConfig.getInstance().getDataDirectory());

        this.importHandlers = Arrays.asList(
            new StudentCsvImporter(studentRepo, dataDir),
            new CourseCsvImporter(courseRepo, instructorRepo, dataDir)
        );

        this.exportHandlers = Arrays.asList(
            new StudentCsvExporter(studentRepo, dataDir),
            new CourseCsvExporter(courseRepo, dataDir),
            new EnrollmentCsvExporter(studentRepo, dataDir)
        );
    }

    public void importData() {
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            importInstructors();

            for (CsvImportHandler handler : importHandlers) {
                handler.importData();
            }

            System.out.println("Data imported successfully from '" + dataDir + "' directory.");
        } catch (IOException e) {
            System.err.println("Error during data import: " + e.getMessage());
        }
    }

    public void exportData() {
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            for (CsvExportHandler<?> handler : exportHandlers) {
                handler.export();
            }

            System.out.println("Data exported successfully to '" + dataDir + "' directory.");
        } catch (IOException e) {
            System.err.println("Error during data export: " + e.getMessage());
        }
    }

    private void importInstructors() {
         instructorRepo.save(new Instructor(1, "Dr. Alan Turing", "alan.t@bletchley.uk",
                 LocalDate.of(1912, 6, 23), "Computer Science", "A101"));
         instructorRepo.save(new Instructor(2, "Dr. Grace Hopper", "grace.h@yale.edu",
                 LocalDate.of(1906, 12, 9), "Computer Science", "B202"));
    }
}