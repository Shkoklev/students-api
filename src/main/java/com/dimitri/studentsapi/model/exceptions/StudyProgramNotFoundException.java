package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class StudyProgramNotFoundException extends RuntimeException {

    public StudyProgramNotFoundException(Long id) {
        super("study program does not exist with this id " + id + " !");
    }

    public StudyProgramNotFoundException(String name) {
        super("study program does not exist with this name " + name + " !");
    }
}
