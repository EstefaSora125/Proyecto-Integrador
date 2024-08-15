package com.Betek.PruebaTecnica.api_rest.controller;

import com.Betek.PruebaTecnica.api_rest.controller.dto.ResponseDto;
import com.Betek.PruebaTecnica.api_rest.controller.dto.ResponseMedicalDto;
import com.Betek.PruebaTecnica.api_rest.exceptions.PatientAlreadyAppointment;
import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.model.enums.TypePatient;
import com.Betek.PruebaTecnica.api_rest.service.MedicalAppointmentService;
import com.Betek.PruebaTecnica.api_rest.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical")
public class MedicalAppointmentController {
    private final MedicalAppointmentService medical;
    private final PatientService patientService;

    @Autowired
    public MedicalAppointmentController(MedicalAppointmentService medical, PatientService patientService) {
        this.medical = medical;
        this.patientService = patientService;
    }

    @PostMapping("/agendar")
    public ResponseEntity<ResponseDto> scheduleAnAppointment(@RequestBody MedicalAppointment appointment){
        try {
            if (this.medical.create(appointment)){
                return ResponseEntity.ok(ResponseDto.builder()
                        .date(appointment.getDate())
                        .id(appointment.getId()).build());
            }
            return ResponseEntity.badRequest().body(ResponseDto.builder()
                    .mensaje("El paciente no se encuentra registrado en el hospital")
                    .build());
        }catch (PatientAlreadyAppointment e){
            return ResponseEntity.badRequest().body(ResponseDto.builder()
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/agendar/{id_cita}")
    public ResponseEntity<ResponseMedicalDto> get(@PathVariable("id_cita") int id){
        MedicalAppointment appointment = this.medical.get(id);
        Patient patient = patientService.get(appointment.getPatient().getIdentityPatient());
        return ResponseEntity.ok(ResponseMedicalDto.builder()
                .id(id)
                .specialty(appointment.getSpecialty())
                .identityPatient(patient.getIdentityPatient())
                .typePatient(TypePatient.getEnumerate(patient.getTypePatient()))
                .date(appointment.getDate()).build());
    }

    @GetMapping
    public List<MedicalAppointment> getAll(){
        return this.medical.getAll();
    }
}
