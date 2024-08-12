package com.Betek.PruebaTecnica.api_rest.controller;

import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient){
        this.patientService.create(patient);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id_patient}")
    public Patient get(@PathVariable("id_patient") int id){
        return this.patientService.get(id);
    }

    /*


    @GetMapping
    public List<Patient> getAll(){

    }

     */

}
