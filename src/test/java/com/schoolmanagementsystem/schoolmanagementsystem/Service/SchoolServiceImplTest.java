package com.schoolmanagementsystem.schoolmanagementsystem.Service;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolUpdateRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithEmailDoesntExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithNameAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class SchoolServiceImplTest {

    @Autowired
    private SchoolService schoolService;


    @BeforeEach
    void setUp() {
        schoolService.clearDatabase();
    }

    @Test
    void testThatWeCanAddASchool() throws SchoolWithNameAlreadyExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034231");
        schoolService.registerSchool(schoolRegisterRequest);

        assertEquals(1, schoolService.getNumberOfSchool());
    }

    @Test
    void testThatWeCantHaveDuplicateSchoolRegistration() {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("0903423178");

        try {
            schoolService.registerSchool(schoolRegisterRequest);
        } catch (SchoolWithNameAlreadyExistException err) {
            assertEquals("School with " + schoolRegisterRequest.getSchoolName() + " already exist", err.getMessage());
        }

        SchoolRegisterRequest schoolRegisterRequest2 = new SchoolRegisterRequest();
        schoolRegisterRequest2.setSchoolName("semicolon");
        schoolRegisterRequest2.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest2.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("0903423165");

        SchoolWithNameAlreadyExistException exception = assertThrows(SchoolWithNameAlreadyExistException.class, () -> {
            schoolService.registerSchool(schoolRegisterRequest2);
        });

        assertEquals(1, schoolService.getNumberOfSchool());

    }

    @Test
    void testThatWeCanGetSchool() throws SchoolWithNameAlreadyExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        schoolService.registerSchool(schoolRegisterRequest);

        SchoolRegisterRequest schoolRegisterRequest2 = new SchoolRegisterRequest();
        schoolRegisterRequest2.setSchoolName("decagon");
        schoolRegisterRequest2.setSchoolAddress("21, lekki epe exp-way");
        schoolRegisterRequest2.setSchoolEmail("decagon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034231786");

        School school = schoolService.registerSchool(schoolRegisterRequest2).getSchool();
        schoolService.getSchool(school.getSchoolId());
        assertEquals("decagon", school.getSchoolName());

    }


    @Test
    void testThatWeCanDeleteSchool() throws SchoolWithNameAlreadyExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        schoolService.registerSchool(schoolRegisterRequest);

        SchoolRegisterRequest schoolRegisterRequest2 = new SchoolRegisterRequest();
        schoolRegisterRequest2.setSchoolName("decagon");
        schoolRegisterRequest2.setSchoolAddress("21, lekki epe exp-way");
        schoolRegisterRequest2.setSchoolEmail("decagon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034231786");

        School school = schoolService.registerSchool(schoolRegisterRequest2).getSchool();
        schoolService.deleteSchool(school.getSchoolId());
        assertEquals(1, schoolService.getNumberOfSchool());

    }

    @Test
    void testThatWeCanFindSchoolByEmail() throws SchoolWithNameAlreadyExistException, SchoolWithEmailDoesntExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        schoolService.registerSchool(schoolRegisterRequest);

        SchoolRegisterRequest schoolRegisterRequest2 = new SchoolRegisterRequest();
        schoolRegisterRequest2.setSchoolName("decagon");
        schoolRegisterRequest2.setSchoolAddress("21, lekki epe exp-way");
        schoolRegisterRequest2.setSchoolEmail("decagon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034231786");

        School school = schoolService.registerSchool(schoolRegisterRequest2).getSchool();
        schoolService.findByEmail(school.getSchoolEmail());
        assertEquals("decagon", school.getSchoolName());

    }

    @Test
    void testThatWeCanListOfSchool() throws SchoolWithNameAlreadyExistException, SchoolWithEmailDoesntExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        schoolService.registerSchool(schoolRegisterRequest);

        SchoolRegisterRequest schoolRegisterRequest2 = new SchoolRegisterRequest();
        schoolRegisterRequest2.setSchoolName("decagon");
        schoolRegisterRequest2.setSchoolAddress("21, lekki epe exp-way");
        schoolRegisterRequest2.setSchoolEmail("decagon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034231786");

        schoolService.registerSchool(schoolRegisterRequest2);
        assertEquals(2, schoolService.getListOfAllSchool().size());

    }

    @Test
    void testThatWeCanUpdateSchoolDetails() throws SchoolWithNameAlreadyExistException, SchoolWithEmailDoesntExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        var school = schoolService.registerSchool(schoolRegisterRequest);

        SchoolUpdateRequest schoolUpdateRequest = new SchoolUpdateRequest();
        schoolUpdateRequest.setSchoolAddress("semicolon-africa");
        schoolUpdateRequest.setSchoolAddress("21, lekki epe exp-way");
        schoolUpdateRequest.setSchoolPhoneNumber("099765612");
        schoolUpdateRequest.setSchoolEmail("semicolon.africa@gmail.com");
        schoolUpdateRequest.setSchoolId(school.getSchool().getSchoolId());
;
        schoolService.updateSchool(schoolUpdateRequest);
        School school1 = schoolService.getSchool(school.getSchool().getSchoolId());

        assertEquals(1, schoolService.getListOfAllSchool().size());
        assertEquals(1, schoolService.getNumberOfSchool());
        assertEquals("21, lekki epe exp-way", school1.getSchoolAddress());
        assertEquals("099765612", school1.getSchoolPhoneNumber());
        assertEquals("semicolon.africa@gmail.com", school1.getSchoolEmail());

    }

    @Test
    void testThatWeCanGetSchoolProfile() throws SchoolWithNameAlreadyExistException {
        SchoolRegisterRequest schoolRegisterRequest = new SchoolRegisterRequest();
        schoolRegisterRequest.setSchoolName("semicolon");
        schoolRegisterRequest.setSchoolAddress("312, sabo yaba");
        schoolRegisterRequest.setSchoolEmail("semicolon@gmail.com");
        schoolRegisterRequest.setSchoolPhoneNumber("09034237861");

        School school = schoolService.registerSchool(schoolRegisterRequest).getSchool();
        assertEquals(school.toString(), schoolService.getSchoolProfile("semicolon"));
    }
}