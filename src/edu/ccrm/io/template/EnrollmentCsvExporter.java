package edu.ccrm.io.template;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;
import edu.ccrm.repository.IStudentRepository;

public class EnrollmentCsvExporter extends CsvExportHandler<EnrollmentCsvExporter.EnrollmentRow> {
    private final IStudentRepository studentRepo;

    public EnrollmentCsvExporter(IStudentRepository studentRepo, Path dataDir) {
        super(dataDir);
        this.studentRepo = studentRepo;
    }

    @Override
    protected Path getFilePath() {
        return dataDir.resolve("enrollments_export.csv");
    }

    @Override
    protected String getHeader() {
        return "regNo,courseCode,grade";
    }

    @Override
    protected Collection<EnrollmentRow> getData() {
        return studentRepo.findAll().stream()
            .flatMap(s -> s.getEnrolledCourses().stream()
                .map(en -> new EnrollmentRow(
                    s.getRegNo(),
                    en.getCourse().getCourseCode().getFullCode(),
                    en.getGrade().toString())))
            .collect(Collectors.toList());
    }

    @Override
    protected String formatRow(EnrollmentRow row) {
        return String.format("%s,%s,%s", row.regNo, row.courseCode, row.grade);
    }

    static class EnrollmentRow {
        String regNo, courseCode, grade;
        EnrollmentRow(String r, String c, String g) {
            this.regNo = r; this.courseCode = c; this.grade = g;
        }
    }
}