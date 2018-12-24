package com.dimitri.studentsapi.repository;

import com.dimitri.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByIndex(String index);
    List<Student> findByStudyProgram_Id(Long id);

}
