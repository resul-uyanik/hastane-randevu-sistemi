package com.example.hastanerandevusistemi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequest {

    @NotBlank(message = "Hasta adı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "Hasta adı 2 ile 50 karakter arasında olmalıdır.")
    private String firstName;

    @NotBlank(message = "Hasta soyadı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "Hasta soyadı 2 ile 50 karakter arasında olmalıdır.")
    private String lastName;

    @NotBlank(message = "Kimlik numarası zorunludur.")
    @Size(min = 11, max = 11, message = "Kimlik numarası tam 11 haneli olmalıdır.")
    private String identityNumber;

    public PatientRequest() {
    }

    public PatientRequest(String firstName, String lastName, String identityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
    }

    // Getter ve Setterlar
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdentityNumber() { return identityNumber; }
    public void setIdentityNumber(String identityNumber) { this.identityNumber = identityNumber; }
}