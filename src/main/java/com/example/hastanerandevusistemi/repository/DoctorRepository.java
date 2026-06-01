package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialtyContainingIgnoreCase(String specialty);
    List<Doctor> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM doctor WHERE is_active = false", nativeQuery = true)
    List<Doctor> findDeletedDoctors();

    @Query(value = "SELECT * FROM doctor WHERE is_active = false AND LOWER(name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Doctor> searchDeletedDoctors(String name);
}