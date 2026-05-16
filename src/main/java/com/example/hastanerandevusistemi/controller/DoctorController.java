package com.example.hastanerandevusistemi.controller;

import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.hastanerandevusistemi.dto.DoctorRequest;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAll() { return doctorService.getAllDoctors(); }

    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor createdDoctor = doctorService.createDoctor(doctorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    @GetMapping("/filter")
    public List<Doctor> filterBySpecialty(@RequestParam String specialty) {
        return doctorService.getDoctorsBySpecialty(specialty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(doctor -> ResponseEntity.ok(doctor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorRequest);

        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = doctorService.deleteDoctor(id);

        if (deleted) {
            return ResponseEntity.ok("Doktor başarıyla silindi.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String name) {
        return doctorService.searchDoctors(name);
    }

    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
        return doctorService.getDoctorById(id).isPresent();
    }
}