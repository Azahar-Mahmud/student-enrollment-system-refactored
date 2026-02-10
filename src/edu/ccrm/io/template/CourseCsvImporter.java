package edu.ccrm.io.template;

import java.nio.file.Path;
import edu.ccrm.domain.*;
import edu.ccrm.repository.*;

public class CourseCsvImporter extends CsvImportHandler {
    private final ICourseRepository courseRepo;
    private final IInstructorRepository instructorRepo;

    public CourseCsvImporter(ICourseRepository courseRepo, IInstructorRepository instructorRepo, Path dataDir) {
        super(dataDir);
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    @Override
    protected Path getFilePath() {
        return dataDir.resolve("courses.csv");
    }

    @Override
    protected void processRow(String[] parts) {
        String deptPrefix = parts[0].replaceAll("\\d", "");
        int courseNum = Integer.parseInt(parts[0].replaceAll("\\D", ""));
        CourseCode code = new CourseCode(deptPrefix, courseNum);

        Instructor instructor = instructorRepo.findById(Integer.parseInt(parts[4]))
            .orElseThrow(() -> new RuntimeException("Instructor not found for course import"));

        Course course = new Course.Builder(code, parts[1])
            .credits(Integer.parseInt(parts[2]))
            .semester(Semester.valueOf(parts[3].toUpperCase()))
            .instructor(instructor)
            .department(parts[5])
            .build();

        courseRepo.save(course);
    }
}