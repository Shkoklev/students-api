package com.dimitri.studentsapi.api;

import com.dimitri.studentsapi.model.StudyProgram;
import com.dimitri.studentsapi.model.requests.StudyProgramRequest;
import com.dimitri.studentsapi.service.StudyProgramService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin({"*", "localhost:3000"})
@RestController
@RequestMapping(value = "/study_programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramController {

    private final StudyProgramService studyProgramService;

    public StudyProgramController(StudyProgramService studyProgramService) {
        this.studyProgramService = studyProgramService;
    }

    @GetMapping
    public List<StudyProgram> getAllStudyPrograms() {
        return studyProgramService.getAllStudyPrograms();
    }

    @GetMapping("/{id}")
    public StudyProgram getStudyProgramById(@PathVariable("id") Long id) {
        return studyProgramService.getStudyProgramById(id);
    }

    @PostMapping
    public StudyProgram saveStudyProgram(@RequestBody StudyProgramRequest studyProgramRequest) {
        return studyProgramService.saveStudyProgram(studyProgramRequest.name);
    }

    @PatchMapping("/{id}")
    public StudyProgram updateStudyProgram(@PathVariable("id") Long id, @RequestBody StudyProgramRequest studyProgramRequest) {
        return studyProgramService.updateStudyProgram(id,studyProgramRequest.name);
    }

    @DeleteMapping("/{id}")
    public void deleteStudyProgramById(@PathVariable("id") Long id) {
        studyProgramService.deleteStudyProgramById(id);
    }

}
