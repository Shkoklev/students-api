package com.dimitri.studentsapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudyProgram {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;


    public StudyProgram() {}

    public StudyProgram(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
