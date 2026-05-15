package com.example.hastanerandevusistemi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequest {

    @NotBlank(message = "Hasta adı boş bırakılamaz.")
    private String firstName;

    @NotBlank(message = "Hasta soyadı boş bırakılamaz.")
    private String lastName;

    @NotBlank(message = "Kimlik numarası zorunludur.")
    @Size(min = 11, max = 11, message = "Kimlik numarası tam 11 haneli olmalıdır.")
    private String identityNumber;

    // Getter ve Setterlar
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdentityNumber() { return identityNumber; }
    public void setIdentityNumber(String identityNumber) { this.identityNumber = identityNumber; }
}