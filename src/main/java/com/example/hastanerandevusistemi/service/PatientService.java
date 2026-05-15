package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.PatientRequest;
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient createPatient(PatientRequest request) {
        Patient patient = new Patient();

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setIdentityNumber(request.getIdentityNumber());

        return patientRepository.save(patient);
    }

    public List<Patient> searchPatients(String name) {
        return patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public boolean checkIfPatientExists(String idNo) {
        return patientRepository.existsByIdentityNumber(idNo);
    }
}