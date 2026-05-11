package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}