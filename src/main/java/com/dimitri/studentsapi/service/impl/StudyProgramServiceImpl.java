package com.dimitri.studentsapi.service.impl;

import com.dimitri.studentsapi.model.Student;
import com.dimitri.studentsapi.model.StudyProgram;
import com.dimitri.studentsapi.model.exceptions.ParametersMissingException;
import com.dimitri.studentsapi.model.exceptions.StudyProgramAlreadyExistsException;
import com.dimitri.studentsapi.model.exceptions.StudyProgramHasStudentsException;
import com.dimitri.studentsapi.model.exceptions.StudyProgramNotFoundException;
import com.dimitri.studentsapi.repository.StudentRepository;
import com.dimitri.studentsapi.repository.StudyProgramRepository;
import com.dimitri.studentsapi.service.StudyProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private final Logger logger = LoggerFactory.getLogger(StudyProgramServiceImpl.class);

    private final StudyProgramRepository studyProgramRepository;
    private final StudentRepository studentRepository;

    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository, StudentRepository studentRepository) {
        this.studyProgramRepository = studyProgramRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudyProgram> getAllStudyPrograms() {
        return studyProgramRepository.findAll();
    }

    @Override
    public StudyProgram getStudyProgramById(Long id) {
        StudyProgram studyProgram = studyProgramRepository.findStudyProgramById(id);
        if(studyProgram == null )
            throw new StudyProgramNotFoundException(id);
        return studyProgram;
    }

    @Override
    public StudyProgram saveStudyProgram(String name) {
        checkStudyProgramExistence(name);
        StudyProgram studyProgram = new StudyProgram(name);
        return studyProgramRepository.save(studyProgram);
    }

    @Override
    public StudyProgram updateStudyProgram(Long id, String name) {
        if(name.isEmpty())
            throw new ParametersMissingException();
        checkStudyProgramExistence(name);
        StudyProgram studyProgram = studyProgramRepository.findStudyProgramById(id);
        studyProgram.setName(name);
        return studyProgramRepository.save(studyProgram);
    }

    @Override
    public void deleteStudyProgramById(Long id) {
        checkForStudyProgramStudents(id);
        studyProgramRepository.deleteById(id);
    }

    public void checkStudyProgramExistence(String name) {
        StudyProgram studyProgram = studyProgramRepository.findByName(name);
        if(studyProgram != null) {
            throw new StudyProgramAlreadyExistsException(studyProgram.getName());
        }
    }

    public void checkForStudyProgramStudents(Long id) {
        List<Student> students = studentRepository.findByStudyProgram_Id(id);
        if(students.size() != 0) {
            StudyProgram studyProgram = studyProgramRepository.findStudyProgramById(id);
            String studyProgramName = studyProgram.getName();
            throw new StudyProgramHasStudentsException(studyProgramName);
        }
    }


}
