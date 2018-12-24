package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudyProgramAlreadyExistsException extends RuntimeException {

    public StudyProgramAlreadyExistsException(String name) {
        super("Study Program with name '" + name + "' already exists !");
    }
}
