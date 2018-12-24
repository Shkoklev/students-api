package com.dimitri.studentsapi.service;

import com.dimitri.studentsapi.model.Student;
import com.dimitri.studentsapi.model.exceptions.StudentNotFoundException;
import com.dimitri.studentsapi.model.exceptions.StudyProgramNotFoundException;
import com.dimitri.studentsapi.model.requests.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentByIndex(String index) throws StudentNotFoundException;

    List<Student> getStudentsByStudyProgram(Long id) throws StudyProgramNotFoundException;

    void saveStudent(StudentRequest studentRequest);

    void deleteStudentByIndex(String index);

    Student updateStudent(StudentRequest studentRequest, String index);


}
