package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.PatientRequest;
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.hastanerandevusistemi.exception.AppointmentConflictException;

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
        if (patientRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new AppointmentConflictException("Bu kimlik numarası ile kayıtlı bir hasta zaten var!");
        }

        Patient patient = new Patient(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getIdentityNumber()
        );

        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Long id, PatientRequest request) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();

            if (request.getIdentityNumber() != null && !existingPatient.getIdentityNumber().equals(request.getIdentityNumber())) {
                if (patientRepository.existsByIdentityNumber(request.getIdentityNumber())) {
                    throw new AppointmentConflictException("Güncellenmek istenen kimlik numarası başka bir hastaya ait!");
                }
                existingPatient.setIdentityNumber(request.getIdentityNumber());
            }

            if (request.getFirstName() != null) {
                existingPatient.setFirstName(request.getFirstName());
            }

            if (request.getLastName() != null) {
                existingPatient.setLastName(request.getLastName());
            }

            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public boolean deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Patient> searchPatients(String name) {
        return patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public boolean checkIfPatientExists(String idNo) {
        return patientRepository.existsByIdentityNumber(idNo);
    }
}