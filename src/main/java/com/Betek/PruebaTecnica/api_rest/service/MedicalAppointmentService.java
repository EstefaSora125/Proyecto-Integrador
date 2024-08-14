package com.Betek.PruebaTecnica.api_rest.service;

import com.Betek.PruebaTecnica.api_rest.exceptions.PatientAlreadyAppoiment;
import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.repository.MedicalAppointmentRepository;
import com.Betek.PruebaTecnica.api_rest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MedicalAppointmentService {
    private MedicalAppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;

    @Autowired
    public MedicalAppointmentService(MedicalAppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public boolean create(MedicalAppointment appointment) {
        if (validatePatient(appointment.getPatient().getIdentityPatient())){
            Optional<Patient> existingPatient = patientRepository.findById(appointment.getPatient().getIdentityPatient());
            if (existingPatient.isPresent()) {
                appointment.setPatient(existingPatient.get());
                appointmentRepository.save(appointment);
                return true;
            }else {
                return false;
            }
        }else {
            throw new PatientAlreadyAppoiment("El usuario con identificación " +
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

    public boolean validatePatient(String id){
        Stream<MedicalAppointment> appointmentStream= getAll().stream().filter(patient-> patient.getPatient().getIdentityPatient().equals(id));
        if (appointmentStream.findAny().isEmpty()){
            return true;
        }else {
            Stream<MedicalAppointment> validateDate = appointmentStream.filter(appointment -> appointment.getDate().isAfter(LocalDate.now()));
            return validateDate.findAny().isEmpty();
        }
    }

}
