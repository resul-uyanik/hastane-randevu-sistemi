package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Appointment;
import com.example.hastanerandevusistemi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);

    long countByCompleted(boolean completed);

    List<Appointment> findTop5ByOrderByIdDesc();

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);

    @Query(value = "SELECT * FROM appointment WHERE is_active = false", nativeQuery = true)
    List<Appointment> findDeletedAppointments();

    @Query(value = "SELECT * FROM appointment WHERE is_active = false AND appointment_date >= ?1 AND appointment_date <= ?2", nativeQuery = true)
    List<Appointment> findDeletedAppointmentsByDate(LocalDateTime start, LocalDateTime end);
}