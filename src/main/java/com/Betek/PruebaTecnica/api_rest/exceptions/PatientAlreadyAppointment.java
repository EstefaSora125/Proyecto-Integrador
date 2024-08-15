package com.Betek.PruebaTecnica.api_rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PatientAlreadyAppointment extends RuntimeException{

    public PatientAlreadyAppointment(String message) {
        super(message);
    }
}
