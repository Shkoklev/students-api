package com.dimitri.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudyProgramHasStudentsException extends RuntimeException {

    public StudyProgramHasStudentsException(String name) {
        super("The study program " + name + " can't be deleted because there are students participating !");
    }
}
