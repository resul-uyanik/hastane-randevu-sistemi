package com.example.hastanerandevusistemi.repository;

import java.util.List;
import com.example.hastanerandevusistemi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    boolean existsByIdentityNumber(String identityNumber);

    @Query(value = "SELECT * FROM patient WHERE is_active = false", nativeQuery = true)
    List<Patient> findDeletedPatients();

    @Query(value = "SELECT * FROM patient WHERE is_active = false AND (LOWER(first_name) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(last_name) LIKE LOWER(CONCAT('%', ?1, '%')))", nativeQuery = true)
    List<Patient> searchDeletedPatients(String name);

}