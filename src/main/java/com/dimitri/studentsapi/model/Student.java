package com.dimitri.studentsapi.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @Column(name="id")
    private String index;

    @Column
    private String name;

    @Column
    private String lastName;

    @ManyToOne
    private StudyProgram studyProgram;

    public Student() {}

    public Student(String index, String name, String lastName, StudyProgram studyProgram) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.studyProgram = studyProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
