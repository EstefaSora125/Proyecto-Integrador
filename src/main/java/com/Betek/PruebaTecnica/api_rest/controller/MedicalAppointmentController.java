package com.Betek.PruebaTecnica.api_rest.controller;

import com.Betek.PruebaTecnica.api_rest.exceptions.PatientAlreadyAppoiment;
import com.Betek.PruebaTecnica.api_rest.model.MedicalAppointment;
import com.Betek.PruebaTecnica.api_rest.service.MedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medical")
public class MedicalAppointmentController {
    private MedicalAppointmentService medical;

    @Autowired
    public MedicalAppointmentController(MedicalAppointmentService medical) {
        this.medical = medical;
    }

    @PostMapping("/agendar")
    public ResponseEntity<MedicalAppointment> scheduleAnAppointment(@RequestBody MedicalAppointment appointment){
        try {
            if (this.medical.create(appointment)){
                return ResponseEntity.ok(new MedicalAppointment(appointment.getId(), LocalDate.now()));
            }
            return ResponseEntity.status(404).body(new MedicalAppointment());
        }catch (PatientAlreadyAppoiment e){
            return ResponseEntity.status(400).body(new MedicalAppointment());
        }


    }
    @GetMapping("/{id_cita}")
    public MedicalAppointment get(@PathVariable("id_cita") int id){
        return this.medical.get(id);
    }

    @GetMapping
    public List<MedicalAppointment> getAll(){
        return this.medical.getAll();
    }
}
