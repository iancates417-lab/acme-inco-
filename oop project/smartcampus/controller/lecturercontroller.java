package com.smartcampus.controller;

import com.smartcampus.model.Lecturer;
import com.smartcampus.repository.LecturerRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturers")
@CrossOrigin
public class LecturerController {

    private final LecturerRepository lecturerRepository;

    public LecturerController(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    // Get all lecturers
    @GetMapping
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    // Get one lecturer
    @GetMapping("/{id}")
    public Lecturer getLecturer(@PathVariable Long id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    // Add a lecturer
    @PostMapping
    public Lecturer addLecturer(@RequestBody Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    // Update a lecturer
    @PutMapping("/{id}")
    public Lecturer updateLecturer(
            @PathVariable Long id,
            @RequestBody Lecturer lecturer) {

        Lecturer existingLecturer =
                lecturerRepository.findById(id).orElse(null);

        if (existingLecturer == null) {
            return null;
        }

        existingLecturer.setStaffNumber(lecturer.getStaffNumber());
        existingLecturer.setFirstName(lecturer.getFirstName());
        existingLecturer.setLastName(lecturer.getLastName());
        existingLecturer.setEmail(lecturer.getEmail());

        return lecturerRepository.save(existingLecturer);
    }

    // Delete a lecturer
    @DeleteMapping("/{id}")
    public String deleteLecturer(@PathVariable Long id) {

        if (!lecturerRepository.existsById(id)) {
            return "Lecturer not found";
        }

        lecturerRepository.deleteById(id);

        return "Lecturer deleted successfully";
    }
}