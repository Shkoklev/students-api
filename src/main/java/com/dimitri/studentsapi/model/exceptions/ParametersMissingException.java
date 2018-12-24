package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ParametersMissingException extends RuntimeException {

    public ParametersMissingException() {
            super("You must provide all parameters !");
    }
}
