package com.Betek.PruebaTecnica.api_rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PatientAlreadyAppoiment extends RuntimeException{

    public PatientAlreadyAppoiment(String message) {
        super(message);
    }
}
