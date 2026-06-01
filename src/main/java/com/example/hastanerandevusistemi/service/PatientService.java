package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.PatientRequest;
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.entity.Appointment;
import com.example.hastanerandevusistemi.repository.PatientRepository;
import com.example.hastanerandevusistemi.repository.AppointmentRepository;
import com.example.hastanerandevusistemi.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public Patient createPatient(PatientRequest request) {
        if (patientRepository.existsByIdentityNumber(request.getIdentityNumber())) {
            throw new DuplicateResourceException("Bu kimlik numarası ile kayıtlı bir hasta zaten var!");
        }

        Patient patient = new Patient(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getIdentityNumber()
        );

        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, PatientRequest request) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();

            if (request.getIdentityNumber() != null && !existingPatient.getIdentityNumber().equals(request.getIdentityNumber())) {
                if (patientRepository.existsByIdentityNumber(request.getIdentityNumber())) {
                    throw new DuplicateResourceException("Güncellenmek istenen kimlik numarası başka bir hastaya ait!");
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

    @Transactional
    public boolean deletePatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Patient> searchPatients(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }
        String cleanName = name.trim();
        return patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(cleanName, cleanName);
    }

    public boolean checkIfPatientExists(String idNo) {
        return patientRepository.existsByIdentityNumber(idNo);
    }

    public List<Patient> getDeletedPatients() {
        return patientRepository.findDeletedPatients();
    }

    public List<Patient> searchDeletedPatients(String name) {
        if (name == null || name.trim().isEmpty()) {
            return patientRepository.findDeletedPatients();
        }
        return patientRepository.searchDeletedPatients(name.trim());
    }
}