package com.example.hastanerandevusistemi.controller;

import jakarta.validation.Valid;
import com.example.hastanerandevusistemi.dto.AppointmentRequest;
import com.example.hastanerandevusistemi.entity.Appointment;
import com.example.hastanerandevusistemi.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(appointment -> ResponseEntity.ok(appointment))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment createdAppointment = appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentRequest);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        boolean deleted = appointmentService.deleteAppointment(id);

        if (deleted) {
            return ResponseEntity.ok("Randevu başarıyla iptal edildi.");
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Appointment> completeAppointment(@PathVariable Long id) {
        Appointment updated = appointmentService.completeAppointment(id);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/date")
    public List<Appointment> getByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsByDate(date);
    }

    @GetMapping("/count")
    public long countAppointmentsByCompletedStatus(@RequestParam boolean completed) {
        return appointmentService.countByCompleted(completed);
    }

    @GetMapping("/latest")
    public List<Appointment> getLatestFiveAppointments() {
        return appointmentService.getLatestFiveAppointments();
    }

    @GetMapping("/deleted")
    public List<Appointment> getDeleted() {
        return appointmentService.getDeletedAppointments();
    }

    @GetMapping("/deleted/date")
    public List<Appointment> searchDeletedByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.searchDeletedAppointmentsByDate(date);
    }
}