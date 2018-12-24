package com.dimitri.studentsapi.repository;

import com.dimitri.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {

    StudyProgram findByName(String name);
    StudyProgram findStudyProgramById(Long id);
}
