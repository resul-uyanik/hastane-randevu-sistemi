package com.example.hastanerandevusistemi.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorRequest {

    @NotBlank(message = "Doktor ismi boş bırakılamaz.")
    private String name;

    @NotBlank(message = "Uzmanlık alanı boş bırakılamaz.")
    private String specialty;

    public DoctorRequest() {
    }

    // Getter ve Setterlar
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}