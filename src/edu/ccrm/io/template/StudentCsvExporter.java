package edu.ccrm.io.template;

import java.nio.file.Path;
import java.util.Collection;
import edu.ccrm.domain.Student;
import edu.ccrm.repository.IStudentRepository;

public class StudentCsvExporter extends CsvExportHandler<Student> {
    private final IStudentRepository studentRepo;

    public StudentCsvExporter(IStudentRepository studentRepo, Path dataDir) {
        super(dataDir);
        this.studentRepo = studentRepo;
    }

    @Override
    protected Path getFilePath() {
        return dataDir.resolve("students_export.csv");
    }

    @Override
    protected String getHeader() {
        return "id,regNo,fullName,email,dob,status";
    }

    @Override
    protected Collection<Student> getData() {
        return studentRepo.findAll();
    }

    @Override
    protected String formatRow(Student s) {
        return String.format("%d,%s,%s,%s,%s,%s",
            s.getId(), s.getRegNo(), s.getFullName(), 
            s.getEmail(), s.getDateOfBirth(), s.getStatus());
    }
}