package com.Betek.PruebaTecnica.api_rest.model;

import com.Betek.PruebaTecnica.api_rest.model.enums.TypeId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

   @Id
    @Column(name = "identity_patient")
    private String identityPatient;
    @Column(name = "type_id")
    private TypeId typeId;
    @Column(name = "type_patient")
    private String typePatient;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
}
