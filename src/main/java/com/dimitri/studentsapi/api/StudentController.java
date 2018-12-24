package com.dimitri.studentsapi.api;

import com.dimitri.studentsapi.model.Student;
import com.dimitri.studentsapi.model.requests.StudentRequest;
import com.dimitri.studentsapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin({"*", "localhost:3000"})
@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{index}")
    public Student getStudentByIndex(@PathVariable("index") String index) {
        return studentService.getStudentByIndex(index);
    }

    @GetMapping("/by_study_program/{id}")
    public List<Student> getStudentsByStudyProgram(@PathVariable("id") Long id) {
        return studentService.getStudentsByStudyProgram(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody StudentRequest studentRequest, HttpServletResponse response) {
        studentService.saveStudent(studentRequest);
        response.setHeader("Location", "/students/" + studentRequest.index);
    }

    @DeleteMapping("/{index}")
    public void deleteStudentByIndex(@PathVariable("index") String index) {
        studentService.deleteStudentByIndex(index);
    }

    @PatchMapping("/{index}")
    public Student updateStudent(@PathVariable("index") String index, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentRequest,index);
    }


}
