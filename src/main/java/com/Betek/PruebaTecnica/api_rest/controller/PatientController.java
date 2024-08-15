package com.Betek.PruebaTecnica.api_rest.controller;

import com.Betek.PruebaTecnica.api_rest.controller.dto.ResponseDto;
import com.Betek.PruebaTecnica.api_rest.exceptions.DifferentTypePatient;
import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody Patient patient){
        try{
            this.patientService.create(patient);
            return ResponseEntity.ok(ResponseDto.builder()
                    .mensaje("El paciente se registró con éxito")
                    .build());
        }catch (DifferentTypePatient e){
            return ResponseEntity.badRequest().body(ResponseDto.builder()
                    .mensaje(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/{id_patient}")
    public Patient get(@PathVariable("id_patient") String id){
        return this.patientService.get(id);
    }

    @GetMapping
    public List<Patient> getAll(){
        return this.patientService.getAll();
    }
}
