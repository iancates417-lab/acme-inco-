package com.smartcampus.controller;

import com.smartcampus.model.Course;
import com.smartcampus.repository.CourseRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get one course
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Add a course
    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // Update a course
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course existingCourse =
                courseRepository.findById(id).orElse(null);

        if (existingCourse == null) {
            return null;
        }

        existingCourse.setCode(course.getCode());
        existingCourse.setName(course.getName());
        existingCourse.setCreditUnits(course.getCreditUnits());
        existingCourse.setProgram(course.getProgram());
        existingCourse.setLecturer(course.getLecturer());

        return courseRepository.save(existingCourse);
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {

        if (!courseRepository.existsById(id)) {
            return "Course not found";
        }

        courseRepository.deleteById(id);

        return "Course deleted successfully";
    }
}