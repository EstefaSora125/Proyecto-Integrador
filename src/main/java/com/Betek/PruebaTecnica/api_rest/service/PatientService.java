package com.Betek.PruebaTecnica.api_rest.service;

import com.Betek.PruebaTecnica.api_rest.model.Patient;
import com.Betek.PruebaTecnica.api_rest.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public void create(Patient patient){
        this.patientRepository.save(patient);
    }

    public Patient get(int id){
        Optional<Patient> patientOptional = this.patientRepository.findById(id);
        return patientOptional.orElseGet(Patient::new);
    }
}
