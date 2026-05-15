package com.example.hastanerandevusistemi.repository;

import java.util.List;
import com.example.hastanerandevusistemi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    boolean existsByIdentityNumber(String identityNumber);

}