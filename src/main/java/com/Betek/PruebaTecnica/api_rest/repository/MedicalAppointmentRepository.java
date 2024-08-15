package com.Betek.PruebaTecnica.api_rest.repository;

import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Integer> {

}
