package com.smartcampus.controller;

import com.smartcampus.model.Department;
import com.smartcampus.repository.DepartmentRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get one department
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // Add a department
    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // Update a department
    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        Department existingDepartment =
                departmentRepository.findById(id).orElse(null);

        if (existingDepartment == null) {
            return null;
        }

        existingDepartment.setName(department.getName());
        existingDepartment.setCode(department.getCode());

        return departmentRepository.save(existingDepartment);
    }

    // Delete a department
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        if (!departmentRepository.existsById(id)) {
            return "Department not found";
        }

        departmentRepository.deleteById(id);

        return "Department deleted successfully";
    }
}