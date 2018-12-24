package com.dimitri.studentsapi.service;

import com.dimitri.studentsapi.model.StudyProgram;

import java.util.List;
import java.util.Optional;

public interface StudyProgramService {

    List<StudyProgram> getAllStudyPrograms();

    StudyProgram saveStudyProgram(String name);

    void deleteStudyProgramById(Long id);

    StudyProgram updateStudyProgram(Long id, String name);

    StudyProgram getStudyProgramById(Long id);
}
