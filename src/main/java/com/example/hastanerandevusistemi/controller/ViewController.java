package com.example.hastanerandevusistemi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/view/doctors")
    public String doctorsPage() {
        return "doctors";
    }

    @GetMapping("/view/patients")
    public String patientsPage() {
        return "patients";
    }

    @GetMapping("/view/appointments")
    public String appointmentsPage() {
        return "appointments";
    }
}