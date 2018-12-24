package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException(String id) {
        super("Student with index '" + id + "' already exists !");
    }
}