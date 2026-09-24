package com.smartcampus.controller;

import com.smartcampus.model.Attendance;
import com.smartcampus.repository.AttendanceRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Get all attendance records
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Get one attendance record
    @GetMapping("/{id}")
    public Attendance getAttendance(@PathVariable Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    // Add attendance
    @PostMapping
    public Attendance addAttendance(
            @RequestBody Attendance attendance) {

        return attendanceRepository.save(attendance);
    }

    // Update attendance
    @PutMapping("/{id}")
    public Attendance updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendance) {

        Attendance existingAttendance =
                attendanceRepository.findById(id).orElse(null);

        if (existingAttendance == null) {
            return null;
        }

        existingAttendance.setStudent(attendance.getStudent());
        existingAttendance.setCourse(attendance.getCourse());
        existingAttendance.setDate(attendance.getDate());
        existingAttendance.setStatus(attendance.getStatus());

        return attendanceRepository.save(existingAttendance);
    }

    // Delete attendance
    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {

        if (!attendanceRepository.existsById(id)) {
            return "Attendance record not found";
        }

        attendanceRepository.deleteById(id);

        return "Attendance deleted successfully";
    }
}