package edu.ccrm.io.template;

import java.nio.file.Path;
import java.util.Collection;
import edu.ccrm.domain.Course;
import edu.ccrm.repository.ICourseRepository;

public class CourseCsvExporter extends CsvExportHandler<Course> {
    private final ICourseRepository courseRepo;

    public CourseCsvExporter(ICourseRepository courseRepo, Path dataDir) {
        super(dataDir);
        this.courseRepo = courseRepo;
    }

    @Override
    protected Path getFilePath() {
        return dataDir.resolve("courses_export.csv");
    }

    @Override
    protected String getHeader() {
        return "courseCode,title,credits,semester,instructorId,department";
    }

    @Override
    protected Collection<Course> getData() {
        return courseRepo.findAll();
    }

    @Override
    protected String formatRow(Course c) {
        return String.format("%s,%s,%d,%s,%d,%s",
            c.getCourseCode().getFullCode(), c.getTitle(), c.getCredits(),
            c.getSemester(), c.getInstructor().getId(), c.getDepartment());
    }
}