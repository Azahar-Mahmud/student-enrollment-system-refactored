package edu.ccrm.io.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import edu.ccrm.repository.ICourseRepository;

public class CourseCsvExporter implements ICsvExporter {
    private final ICourseRepository courseRepo;
    private final Path dataDir;
    
    public CourseCsvExporter(ICourseRepository courseRepo, Path dataDir) {
        this.courseRepo = courseRepo;
        this.dataDir = dataDir;
    }
    
    @Override
    public void export() throws IOException {
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        
        Path filePath = dataDir.resolve("courses_export.csv");
        
        List<String> lines = courseRepo.findAll().stream()
            .map(c -> String.format("%s,%s,%d,%s,%d,%s",
                c.getCourseCode().getFullCode(), c.getTitle(), 
                c.getCredits(), c.getSemester(), 
                c.getInstructor().getId(), c.getDepartment()))
            .collect(Collectors.toList());
        
        lines.add(0, "courseCode,title,credits,semester,instructorId,department");
        Files.write(filePath, lines);
    }
}