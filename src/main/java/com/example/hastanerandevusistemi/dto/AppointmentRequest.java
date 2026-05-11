package com.example.hastanerandevusistemi.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentRequest {

    @NotNull(message = "Doktor seçimi zorunludur.")
    private Long doctorId;

    @NotNull(message = "Hasta seçimi zorunludur.Lütfen doldurunuz!!!")
    private Long patientId;

    @NotNull(message = "Randevu tarihi boş bırakılamaz.Lütfen doldurunuz!!!")
    private LocalDateTime appointmentDate;

    public AppointmentRequest() {
    }

    public AppointmentRequest(Long doctorId, Long patientId, LocalDateTime appointmentDate) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
    }

    //Getter ve Setterlar
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
}