package com.smartcampus.controller;

import com.smartcampus.model.Registration;
import com.smartcampus.repository.RegistrationRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // Get all registrations
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // Get one registration
    @GetMapping("/{id}")
    public Registration getRegistration(@PathVariable Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    // Add a registration
    @PostMapping
    public Registration addRegistration(
            @RequestBody Registration registration) {

        return registrationRepository.save(registration);
    }

    // Update a registration
    @PutMapping("/{id}")
    public Registration updateRegistration(
            @PathVariable Long id,
            @RequestBody Registration registration) {

        Registration existingRegistration =
                registrationRepository.findById(id).orElse(null);

        if (existingRegistration == null) {
            return null;
        }

        existingRegistration.setStudent(registration.getStudent());
        existingRegistration.setCourse(registration.getCourse());
        existingRegistration.setRegistrationDate(
                registration.getRegistrationDate()
        );

        return registrationRepository.save(existingRegistration);
    }

    // Delete a registration
    @DeleteMapping("/{id}")
    public String deleteRegistration(@PathVariable Long id) {

        if (!registrationRepository.existsById(id)) {
            return "Registration not found";
        }

        registrationRepository.deleteById(id);

        return "Registration deleted successfully";
    }
}