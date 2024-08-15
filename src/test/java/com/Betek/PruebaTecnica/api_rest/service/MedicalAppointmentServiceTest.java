package com.Betek.PruebaTecnica.api_rest.service;

import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.model.enums.TypeId;
import com.Betek.PruebaTecnica.api_rest.model.enums.TypePatient;
import com.Betek.PruebaTecnica.api_rest.repository.MedicalAppointmentRepository;
import com.Betek.PruebaTecnica.api_rest.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MedicalAppointmentServiceTest {


    MedicalAppointmentRepository medicalAppointmentRepository;

    PatientRepository patientRepository;

    MedicalAppointmentService medicalAppointmentService;

    @BeforeEach
    void setUp() {
        medicalAppointmentRepository = mock(MedicalAppointmentRepository.class);
        patientRepository = mock(PatientRepository.class);
        medicalAppointmentService = new MedicalAppointmentService(medicalAppointmentRepository, patientRepository);
    }

    @Test
    void createMedialAppointment() {
        Patient patient = new Patient("1243a",TypeId.CC, TypePatient.PARTICULAR.getName(),312121653,"test","hola","direccion");
        MedicalAppointment appointment = new MedicalAppointment(1, "test", LocalDate.now(), patient);
        when(medicalAppointmentRepository.save(appointment)).thenReturn(appointment);
        when(patientRepository.findById(anyString())).thenReturn(Optional.of(patient));

        boolean result = medicalAppointmentService.create(appointment);

        assertTrue(result);
        verify(medicalAppointmentRepository, times(1)).save(appointment);
    }

    @Test
    void createMedicalAppointmentPatientNotFound() {
        Patient patient = new Patient("1243a", TypeId.CC, TypePatient.PARTICULAR.getName(), 312121653, "test", "hola", "direccion");
        MedicalAppointment appointment = new MedicalAppointment(1, "test", LocalDate.now(), patient);

        when(patientRepository.findById(anyString())).thenReturn(Optional.empty());

        boolean result = medicalAppointmentService.create(appointment);

        assertFalse(result);

        verify(medicalAppointmentRepository, times(0)).save(any(MedicalAppointment.class));
    }
}