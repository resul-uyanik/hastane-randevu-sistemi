package com.example.hastanerandevusistemi.repository;

import com.example.hastanerandevusistemi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}