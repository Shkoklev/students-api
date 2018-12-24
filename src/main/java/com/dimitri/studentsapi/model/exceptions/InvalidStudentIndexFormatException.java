package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidStudentIndexFormatException extends RuntimeException{

    public InvalidStudentIndexFormatException(String studentIndex) {
        super("The index '" + studentIndex + "' is not in a valid index format !");
    }
}
