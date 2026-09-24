package com.smartcampus.controller;

import com.smartcampus.model.Program;
import com.smartcampus.repository.ProgramRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin
public class ProgramController {

    private final ProgramRepository programRepository;

    public ProgramController(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    // Get all programs
    @GetMapping
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    // Get one program
    @GetMapping("/{id}")
    public Program getProgram(@PathVariable Long id) {
        return programRepository.findById(id).orElse(null);
    }

    // Add a program
    @PostMapping
    public Program addProgram(@RequestBody Program program) {
        return programRepository.save(program);
    }

    // Update a program
    @PutMapping("/{id}")
    public Program updateProgram(
            @PathVariable Long id,
            @RequestBody Program program) {

        Program existingProgram =
                programRepository.findById(id).orElse(null);

        if (existingProgram == null) {
            return null;
        }

        existingProgram.setName(program.getName());
        existingProgram.setCode(program.getCode());
        existingProgram.setDepartment(program.getDepartment());

        return programRepository.save(existingProgram);
    }

    // Delete a program
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {

        if (!programRepository.existsById(id)) {
            return "Program not found";
        }

        programRepository.deleteById(id);

        return "Program deleted successfully";
    }
}