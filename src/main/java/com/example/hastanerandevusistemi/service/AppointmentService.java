package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.AppointmentRequest;
import com.example.hastanerandevusistemi.entity.Appointment;
import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.repository.AppointmentRepository;
import com.example.hastanerandevusistemi.repository.DoctorRepository;
import com.example.hastanerandevusistemi.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import com.example.hastanerandevusistemi.exception.AppointmentConflictException;
import com.example.hastanerandevusistemi.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doktor bulunamadı!"));

        Patient patient = patientRepository.findById(appointmentRequest.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Hasta bulunamadı!"));

        LocalDateTime start = appointmentRequest.getAppointmentDate();
        LocalDateTime end = start.plusMinutes(30);

        boolean isOverlapping = appointmentRepository.existsByDoctorIdAndAppointmentDateBetween(
                doctor.getId(),
                start.minusMinutes(29).plusSeconds(1),
                end.minusSeconds(1)
        );

        if (isOverlapping) {
            throw new AppointmentConflictException("Seçilen doktorun bu zaman diliminde zaten bir randevusu bulunuyor!");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(start);
        appointment.setCompleted(false);

        return appointmentRepository.save(appointment);
    }


    @Transactional
    public boolean deleteAppointment(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Appointment completeAppointment(Long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setCompleted(true);
            return appointmentRepository.save(appointment);
        }

        return null;
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByAppointmentDateBetween(startOfDay, endOfDay);
    }

    public long countByCompleted(boolean completed) {
        return appointmentRepository.countByCompleted(completed);
    }

    public List<Appointment> getLatestFiveAppointments() {
        return appointmentRepository.findTop5ByOrderByIdDesc();
    }
}