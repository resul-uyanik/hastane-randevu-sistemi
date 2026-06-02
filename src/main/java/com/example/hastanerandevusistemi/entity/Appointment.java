package com.example.hastanerandevusistemi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@SQLDelete(sql = "UPDATE appointment SET is_active = false WHERE id = ?")
@SQLRestriction("is_active = true")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private boolean completed;

    @Column(nullable = false)
    private boolean isActive = true;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime appointmentDate, Doctor doctor, Patient patient, boolean completed) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.patient = patient;
        this.completed = completed;
    }

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

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }
}