package com.example.hastanerandevusistemi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDate;

    // Birçok randevu bir doktora ait olabilir (Hocanın projesinden daha ileri bir seviye)
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // Birçok randevu bir hastaya ait olabilir
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private boolean completed;

    // 1. Boş Constructor (Hocanın projesindeki standart)
    public Appointment() {
    }

    // 2. Parametreli Constructor
    public Appointment(Long id, LocalDateTime appointmentDate, Doctor doctor, Patient patient, boolean completed) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        this.completed = completed;
    }

    // 3. Getter ve Setter Metotları (Hocanın stilinde)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}