package com.example.hastanerandevusistemi.controller;

import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.service.PatientService;
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
    public List<Patient> getAll() { return patientService.getAllPatients(); }

    @PostMapping
    public Patient create(@RequestBody Patient patient) { return patientService.createPatient(patient); }
}