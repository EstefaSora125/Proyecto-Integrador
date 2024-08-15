package com.Betek.PruebaTecnica.api_rest.service;

import com.Betek.PruebaTecnica.api_rest.exceptions.PatientAlreadyAppointment;
import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.model.enums.TypePatient;
import com.Betek.PruebaTecnica.api_rest.repository.MedicalAppointmentRepository;
import com.Betek.PruebaTecnica.api_rest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MedicalAppointmentService {
    private final MedicalAppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalAppointmentService(MedicalAppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public boolean create(MedicalAppointment appointment) {
        if (validatePatient(appointment.getPatient().getIdentityPatient())){
            Optional<Patient> existingPatient = patientRepository.findById(appointment.getPatient().getIdentityPatient());
            if (existingPatient.isPresent()) {
                if(validateFields(appointment)){
                    appointment.setDate(this.getDateMedicalAppointment(existingPatient.get().getTypePatient()));
                    appointment.setPatient(existingPatient.get());
                    appointmentRepository.save(appointment);
                    return true;
                }
            }
            return false;
        }else {
            throw new PatientAlreadyAppointment("El usuario con identificación " +
                    appointment.getPatient().getIdentityPatient() +
                    " ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos." );
        }
    }

    public MedicalAppointment get(int id){
       Optional<MedicalAppointment> medicalOptional = this.appointmentRepository.findById(id);
        return medicalOptional.orElseGet(MedicalAppointment::new);
    }

    public List<MedicalAppointment> getAll(){
        return this.appointmentRepository.findAll();
    }

    public boolean validatePatient(String id) {
        Stream<MedicalAppointment> appointmentStream = getAll().stream()
                .filter(patient -> patient.getPatient().getIdentityPatient().equals(id));
        Optional<MedicalAppointment> anyAppointment = appointmentStream.findAny();

        if (anyAppointment.isEmpty()) {
            return true;
        } else {
            Stream<MedicalAppointment> validateDate = getAll().stream()
                    .filter(patient -> patient.getPatient().getIdentityPatient().equals(id))
                    .filter(appointment -> appointment.getDate().isAfter(LocalDate.now()));

            return validateDate.findAny().isEmpty();
        }
    }

    public LocalDate getDateMedicalAppointment(String type){
        return switch (type) {
            case "EPS" -> addDays(10, type);
            case "POLIZA" -> addDays(8, type);
            case "PARTICULAR" -> addDays(7, type);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private LocalDate addDays(int businessDay, String typePatient) {
        LocalDate date = LocalDate.now();
        int daysAdd = 0;
        if(!typePatient.equals(TypePatient.POLIZA.getName())){
            while (daysAdd < businessDay) {
                date = date.plusDays(1);
                if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    daysAdd++;
                }
            }
        }else {
            while (daysAdd < businessDay) {
                date = date.plusDays(1);
                daysAdd++;
            }
        }
        return date;
    }

    private boolean validateFields(MedicalAppointment medicalAppointment) {
        return medicalAppointment.getPatient().getIdentityPatient().length() <= 10 &&
                medicalAppointment.getSpecialty().length() <= 100;
    }
}
