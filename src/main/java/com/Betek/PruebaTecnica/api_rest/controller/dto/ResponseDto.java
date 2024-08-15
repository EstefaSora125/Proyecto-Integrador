package com.Betek.PruebaTecnica.api_rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ResponseDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;

}
