package edu.ccrm.io.template;

import java.nio.file.Path;
import java.time.LocalDate;
import edu.ccrm.domain.Student;
import edu.ccrm.repository.IStudentRepository;

public class StudentCsvImporter extends CsvImportHandler {
    private final IStudentRepository studentRepo;

    public StudentCsvImporter(IStudentRepository studentRepo, Path dataDir) {
        super(dataDir);
        this.studentRepo = studentRepo;
    }

    @Override
    protected Path getFilePath() {
        return dataDir.resolve("students.csv");
    }

    @Override
    protected void processRow(String[] parts) {
        Student student = new Student(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            parts[3],
            LocalDate.parse(parts[4])
        );
        studentRepo.save(student);
    }
}