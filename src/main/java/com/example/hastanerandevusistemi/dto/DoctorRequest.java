package com.example.hastanerandevusistemi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DoctorRequest {

    @NotBlank(message = "Doktor ismi boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Doktor ismi 2 ile 100 karakter arasında olmalıdır.")
    private String name;

    @NotBlank(message = "Uzmanlık alanı boş bırakılamaz.")
    @Size(min = 3, max = 50, message = "Uzmanlık alanı 3 ile 50 karakter arasında olmalıdır.")
    private String specialty;

    public DoctorRequest() {
    }

    public DoctorRequest(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    // Getter ve Setterlar
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}