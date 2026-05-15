package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.appointmentDate BETWEEN :searchStart AND :searchEnd")
    boolean existsOverlappingAppointment(@Param("doctorId") Long doctorId,
                                         @Param("searchStart") LocalDateTime searchStart,
                                         @Param("searchEnd") LocalDateTime searchEnd);
}