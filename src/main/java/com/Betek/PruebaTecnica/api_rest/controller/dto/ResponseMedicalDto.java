package com.Betek.PruebaTecnica.api_rest.controller.dto;

import com.Betek.PruebaTecnica.api_rest.model.enums.TypePatient;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ResponseMedicalDto {
    private Integer id;
    @JsonProperty("especialidad")
    private String specialty;
    @JsonProperty("identificaciónUsuario")
    private String identityPatient;
    @JsonProperty("tipoUsuario")
    private TypePatient typePatient;
    @JsonProperty("fechaCita")
    private LocalDate date;
}
