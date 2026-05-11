package com.example.hastanerandevusistemi.controller;

import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.service.DoctorService;
import org.springframework.web.bind.annotation.*;
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
    public Doctor create(@RequestBody Doctor doctor) { return doctorService.createDoctor(doctor); }
}