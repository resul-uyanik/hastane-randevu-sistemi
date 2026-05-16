package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.DoctorRequest;
import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor(
                null,
                doctorRequest.getName(),
                doctorRequest.getSpecialty()
        );

        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyContainingIgnoreCase(specialty);
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor updateDoctor(Long id, DoctorRequest doctorRequest) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();

            if (doctorRequest.getName() != null) {
                existingDoctor.setName(doctorRequest.getName());
            }

            if (doctorRequest.getSpecialty() != null) {
                existingDoctor.setSpecialty(doctorRequest.getSpecialty());
            }

            return doctorRepository.save(existingDoctor);
        }
        return null;
    }

    public boolean deleteDoctor(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Doctor> searchDoctors(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }
}