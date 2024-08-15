package com.Betek.PruebaTecnica.api_rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="medical_appointment")
public class MedicalAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="speciality")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String specialty;
    @Column(name="date")
    private LocalDate date;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "identity_patient", referencedColumnName = "identity_patient")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  Patient patient;

    public MedicalAppointment() {
    }

    public MedicalAppointment(int id, String specialty, LocalDate date, Patient patient) {
        this.id = id;
        this.specialty = specialty;
        this.date = date;
        this.patient = patient;
    }
}
