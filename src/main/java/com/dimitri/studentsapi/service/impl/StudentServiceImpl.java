package com.dimitri.studentsapi.service.impl;

import com.dimitri.studentsapi.model.Student;
import com.dimitri.studentsapi.model.StudyProgram;
import com.dimitri.studentsapi.model.exceptions.*;
import com.dimitri.studentsapi.model.requests.StudentRequest;
import com.dimitri.studentsapi.repository.StudentRepository;
import com.dimitri.studentsapi.repository.StudyProgramRepository;
import com.dimitri.studentsapi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final StudyProgramRepository studyProgramRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudyProgramRepository studyProgramRepository) {
        this.studentRepository = studentRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByIndex(String index) throws StudentNotFoundException {
        Student student =studentRepository.findByIndex(index);
        if(student == null)
            throw new StudentNotFoundException(index);
        return student;
    }

    @Override
    public List<Student> getStudentsByStudyProgram(Long id) throws StudyProgramNotFoundException {
        StudyProgram studyProgram = studyProgramRepository.findStudyProgramById(id);
        if(studyProgram == null)
            throw new StudyProgramNotFoundException(id);
        return studentRepository.findByStudyProgram_Id(id);
    }

    @Override
    public void saveStudent(StudentRequest studentRequest) {
        StudyProgram studyProgram = studyProgramRepository.findByName(studentRequest.studyProgramName);
        validateStudentRequest(studentRequest, studyProgram);
        String index = studentRequest.index;
        String studentName = studentRequest.name;
        String studentLastName = studentRequest.lastName;
        Student student = new Student(index,studentName,studentLastName,studyProgram);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentByIndex(String index) throws StudentNotFoundException {
        Student student = studentRepository.findByIndex(index);
        if(student == null)
            throw new StudentNotFoundException(index);
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudent(StudentRequest studentRequest, String index) {
        Student studentToUpdate = studentRepository.findByIndex(index);
        if(studentToUpdate == null )
            throw new StudentNotFoundException(index);
        String name = studentRequest.name;
        String lastName = studentRequest.lastName;
        String studyProgramName = studentRequest.studyProgramName;
        StudyProgram studyProgram = studyProgramRepository.findByName(studyProgramName);
        if(studyProgram == null)
            throw new StudyProgramNotFoundException(studyProgramName);
        if(!name.isEmpty())
            studentToUpdate.setName(name);
        if(!lastName.isEmpty())
            studentToUpdate.setLastName(lastName);
        studentToUpdate.setStudyProgram(studyProgram);
        return studentRepository.save(studentToUpdate);
    }

    public void validateStudentRequest(StudentRequest studentRequest,StudyProgram studyProgram) {
            checkMissingParameters(studentRequest);
            validateStudentIndex(studentRequest.index);
            validateStudyProgram(studyProgram,studentRequest.studyProgramName);
            validateStudentExistence(studentRequest.index);
    }

    public void checkMissingParameters(StudentRequest request) {
        if(request.index.isEmpty() || request.name.isEmpty() || request.lastName.isEmpty() || request.studyProgramName.isEmpty())
            throw new ParametersMissingException();
    }

    public void validateStudentIndex(String index) {
        if(!index.matches("\\d{6}"))
            throw new InvalidStudentIndexFormatException(index);
    }

    public void validateStudyProgram(StudyProgram studyProgram,String requestStudyProgramName) {
        if(studyProgram == null)
            throw new StudyProgramNotFoundException(requestStudyProgramName);
    }

    public void validateStudentExistence(String studentIndex) {
        Student student = studentRepository.findByIndex(studentIndex);
        if(student != null)
            throw new StudentAlreadyExistsException(studentIndex);
    }

}
