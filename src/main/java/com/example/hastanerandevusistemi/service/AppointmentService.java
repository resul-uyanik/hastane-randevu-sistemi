package com.example.hastanerandevusistemi.service;

import com.example.hastanerandevusistemi.dto.AppointmentRequest;
import com.example.hastanerandevusistemi.entity.Appointment;
import com.example.hastanerandevusistemi.entity.Doctor;
import com.example.hastanerandevusistemi.entity.Patient;
import com.example.hastanerandevusistemi.repository.AppointmentRepository;
import com.example.hastanerandevusistemi.repository.DoctorRepository;
import com.example.hastanerandevusistemi.repository.PatientRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(AppointmentRequest request) {

        LocalDateTime start = request.getAppointmentDate();

        if (start.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Geçmiş bir tarihe randevu oluşturamazsınız!");
        }

        LocalDateTime searchStart = start.minusMinutes(14).plusSeconds(1);
        LocalDateTime searchEnd = start.plusMinutes(14).plusSeconds(59);

        boolean exists = appointmentRepository.existsOverlappingAppointment(
                request.getDoctorId(), searchStart, searchEnd);

        if (exists) {
            throw new RuntimeException("Seçilen zaman dilimi dolu! Lütfen en az 15 dakika sonra deneyin.");
        }

        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow(() -> new RuntimeException("Doktor bulunamadı!"));
        Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow(() -> new RuntimeException("Hasta bulunamadı!"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(start);
        appointment.setCompleted(false);

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("İptal edilmek istenen randevu bulunamadı!");
        }
        appointmentRepository.deleteById(id);
    }

    public Appointment completeAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Randevu bulunamadı!"));

        appointment.setCompleted(true);
        return appointmentRepository.save(appointment);
    }
}