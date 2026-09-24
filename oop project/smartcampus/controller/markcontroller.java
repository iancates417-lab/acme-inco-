package com.smartcampus.controller;

import com.smartcampus.model.Mark;
import com.smartcampus.repository.MarkRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin
public class MarkController {

    private final MarkRepository markRepository;

    public MarkController(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    // Get all marks
    @GetMapping
    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    // Get one mark
    @GetMapping("/{id}")
    public Mark getMark(@PathVariable Long id) {
        return markRepository.findById(id).orElse(null);
    }

    // Add marks
    @PostMapping
    public Mark addMark(@RequestBody Mark mark) {
        return markRepository.save(mark);
    }

    // Update marks
    @PutMapping("/{id}")
    public Mark updateMark(
            @PathVariable Long id,
            @RequestBody Mark mark) {

        Mark existingMark =
                markRepository.findById(id).orElse(null);

        if (existingMark == null) {
            return null;
        }

        existingMark.setStudent(mark.getStudent());
        existingMark.setCourse(mark.getCourse());
        existingMark.setCoursework(mark.getCoursework());
        existingMark.setExamination(mark.getExamination());

        return markRepository.save(existingMark);
    }

    // Delete marks
    @DeleteMapping("/{id}")
    public String deleteMark(@PathVariable Long id) {

        if (!markRepository.existsById(id)) {
            return "Mark not found";
        }

        markRepository.deleteById(id);

        return "Mark deleted successfully";
    }
}