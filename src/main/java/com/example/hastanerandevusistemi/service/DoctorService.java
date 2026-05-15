package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.DoctorRequest;
import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setSpecialty(doctorRequest.getSpecialty());
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyContainingIgnoreCase(specialty);
    }
}