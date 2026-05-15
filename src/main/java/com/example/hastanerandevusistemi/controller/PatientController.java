package com.example.hastanerandevusistemi.controller;

import com.example.hastanerandevusistemi.dto.PatientRequest; // Import eklemeyi unutma
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.service.PatientService;
import jakarta.validation.Valid; // Validasyon için gerekli
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.getAllPatients();
    }

    @PostMapping
    public ResponseEntity<Patient> create(@Valid @RequestBody PatientRequest patientRequest) {
        Patient createdPatient = patientService.createPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String name) {
        return patientService.searchPatients(name);
    }

    @GetMapping("/exists/{idNo}")
    public boolean exists(@PathVariable String idNo) {
        return patientService.checkIfPatientExists(idNo);
    }
}