package com.Betek.PruebaTecnica.api_rest.repository;

import com.Betek.PruebaTecnica.api_rest.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
