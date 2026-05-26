package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    boolean existsByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);

    long countByCompleted(boolean completed);

    List<Appointment> findTop5ByOrderByIdDesc();
}