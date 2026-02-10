package edu.ccrm.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.exception.CourseNotFoundException;
import edu.ccrm.repository.ICourseRepository;

public class CourseService {
    private final ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course findCourseByCode(String courseCode) {
        return courseRepository.findByCode(courseCode)
            .orElseThrow(() -> new CourseNotFoundException(
                "Course with code '" + courseCode + "' not found."));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll().stream()
                .sorted(Comparator.comparing(c -> c.getCourseCode().getFullCode()))
                .collect(Collectors.toList());
    }

    public List<Course> searchCourses(Predicate<Course> filter) {
        return courseRepository.findAll().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }


    public static Predicate<Course> filterByInstructor(String instructorName) {
        return course -> course.getInstructor().getFullName().equalsIgnoreCase(instructorName);
    }

    public static Predicate<Course> filterByDepartment(String department) {
        return course -> course.getDepartment().equalsIgnoreCase(department);
    }

    public static Predicate<Course> filterBySemester(Semester semester) {
        return course -> course.getSemester() == semester;
    }
}