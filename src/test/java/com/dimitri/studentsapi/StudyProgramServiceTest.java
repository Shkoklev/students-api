package com.dimitri.studentsapi;

import com.dimitri.studentsapi.service.StudyProgramService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class StudyProgramServiceTest {

    @Autowired
    StudyProgramService studyProgramService;

    @Test
    public void testGetStudyPrograms() {
        given()
                .when()
                .get("study_programs")
                .then()
                .statusCode(200);
    }

}
