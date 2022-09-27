package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.StudentRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Gender;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithEmailNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithNameNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;


    @BeforeEach
    void setup() {
        studentService.clearDatabase();
    }

    @Test
    void testThatStudentCanBeAdded() throws studentAlreadyExistException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ace");
        studentRegisterRequest.setLastName("joe");
        studentRegisterRequest.setEmail("ace@gmail.com");
        studentRegisterRequest.setAge("23");
        studentRegisterRequest.setGender(Gender.MALE);

        studentService.addStudent(studentRegisterRequest);


        assertEquals(1, studentService.getNumberOfStudent());
        assertEquals(1, studentService.getListOfAllStudent().size());
    }

    @Test
    void testThatWeCanNotAddStudentWithDuplicateEmail(){
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("john");
        studentRegisterRequest.setLastName("poe");
        studentRegisterRequest.setGender(Gender.MALE);
        studentRegisterRequest.setEmail("poe@gmail.com");
        studentRegisterRequest.setAge("24");
                try {
            studentService.addStudent(studentRegisterRequest);
        } catch (studentAlreadyExistException err){
            assertEquals("Student with " + studentRegisterRequest.getEmail() + " already exist!",err.getMessage());
        }


        StudentRegisterRequest studentRegisterRequest2 = new StudentRegisterRequest();
        studentRegisterRequest2.setFirstName("john");
        studentRegisterRequest2.setLastName("poe");
        studentRegisterRequest2.setGender(Gender.MALE);
        studentRegisterRequest2.setEmail("poe@gmail.com");
        studentRegisterRequest2.setAge("24");

        studentAlreadyExistException exception = assertThrows(studentAlreadyExistException.class, ()-> {
            studentService.addStudent(studentRegisterRequest2);
        });

        assertEquals("Student with " + studentRegisterRequest.getEmail() + " already exist!", exception.getMessage());

        assertEquals(1, studentService.getNumberOfStudent());

        assertEquals(1, studentService.getListOfAllStudent().size());
    }

    @Test
    void testThatWeCanGetStudent() throws studentAlreadyExistException, studentWithEmailNotAvailableException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ray");
        studentRegisterRequest.setLastName("sarah");
        studentRegisterRequest.setAge("21");
        studentRegisterRequest.setGender(Gender.FEMALE);
        studentRegisterRequest.setEmail("sarah@gmail.com");

        studentService.addStudent(studentRegisterRequest);

        Student student = studentService.getStudentByEmail("sarah@gmail.com");

        assertEquals("ray", student.getFirstName());
    }

    @Test
    void testThatWeCanGetLIstOfStudentWithSameFirstName() throws studentAlreadyExistException, studentWithNameNotAvailableException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ray");
        studentRegisterRequest.setLastName("yolanda");
        studentRegisterRequest.setAge("21");
        studentRegisterRequest.setGender(Gender.FEMALE);
        studentRegisterRequest.setEmail("sarah@gmail.com");

        studentService.addStudent(studentRegisterRequest);

        StudentRegisterRequest studentRegisterRequest2 = new StudentRegisterRequest();
        studentRegisterRequest2.setFirstName("Michelle");
        studentRegisterRequest2.setLastName("sarah");
        studentRegisterRequest2.setAge("21");
        studentRegisterRequest2.setGender(Gender.FEMALE);
        studentRegisterRequest2.setEmail("michelle@gmail.com");

        studentService.addStudent(studentRegisterRequest2);

        StudentRegisterRequest studentRegisterRequest3 = new StudentRegisterRequest();
        studentRegisterRequest3.setFirstName("ray");
        studentRegisterRequest3.setLastName("kendrick");
        studentRegisterRequest3.setAge("21");
        studentRegisterRequest3.setGender(Gender.FEMALE);
        studentRegisterRequest3.setEmail("kendrick@gmail.com");

        studentService.addStudent(studentRegisterRequest3);


        assertEquals(3,studentService.getNumberOfStudent());
        assertEquals(2, studentService.getListOfStudentWithSameFirstName("ray").size());

    }

    @Test
    void testThatWeCanGetLIstOfStudentWithSameLastName() throws studentAlreadyExistException, studentWithNameNotAvailableException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("yolanda");
        studentRegisterRequest.setLastName("smith");
        studentRegisterRequest.setAge("21");
        studentRegisterRequest.setGender(Gender.FEMALE);
        studentRegisterRequest.setEmail("sarah@gmail.com");

        studentService.addStudent(studentRegisterRequest);

        StudentRegisterRequest studentRegisterRequest2 = new StudentRegisterRequest();
        studentRegisterRequest2.setFirstName("Michelle");
        studentRegisterRequest2.setLastName("smith");
        studentRegisterRequest2.setAge("21");
        studentRegisterRequest2.setGender(Gender.FEMALE);
        studentRegisterRequest2.setEmail("michelle@gmail.com");

        studentService.addStudent(studentRegisterRequest2);

        StudentRegisterRequest studentRegisterRequest3 = new StudentRegisterRequest();
        studentRegisterRequest3.setFirstName("ray");
        studentRegisterRequest3.setLastName("kendrick");
        studentRegisterRequest3.setAge("21");
        studentRegisterRequest3.setGender(Gender.FEMALE);
        studentRegisterRequest3.setEmail("kendrick@gmail.com");

        studentService.addStudent(studentRegisterRequest3);


        assertEquals(3,studentService.getNumberOfStudent());
        assertEquals(2, studentService.getListOfStudentWithSameLastName("smith").size());

    }

    @Test
    void testThatWeCanGetRemoveStudent() throws studentAlreadyExistException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("yolanda");
        studentRegisterRequest.setLastName("smith");
        studentRegisterRequest.setAge("21");
        studentRegisterRequest.setGender(Gender.FEMALE);
        studentRegisterRequest.setEmail("sarah@gmail.com");

        Student student = studentService.addStudent(studentRegisterRequest).getStudent();
        studentService.deleteStudent(student.getStudentId());

        StudentRegisterRequest studentRegisterRequest2 = new StudentRegisterRequest();
        studentRegisterRequest2.setFirstName("Michelle");
        studentRegisterRequest2.setLastName("smith");
        studentRegisterRequest2.setAge("21");
        studentRegisterRequest2.setGender(Gender.FEMALE);
        studentRegisterRequest2.setEmail("michelle@gmail.com");

        studentService.addStudent(studentRegisterRequest2);

        assertEquals(1, studentService.getNumberOfStudent());
    }

    @Test
    void testThatWeCanGetListOfAllStudent() throws studentAlreadyExistException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ace");
        studentRegisterRequest.setLastName("check");
        studentRegisterRequest.setEmail("check@gmail.com");
        studentRegisterRequest.setGender(Gender.MALE);
        studentRegisterRequest.setAge("21");

        studentService.addStudent(studentRegisterRequest);

        StudentRegisterRequest studentRegisterRequest2 = new StudentRegisterRequest();
        studentRegisterRequest2.setFirstName("Michelle");
        studentRegisterRequest2.setLastName("smith");
        studentRegisterRequest2.setAge("21");
        studentRegisterRequest2.setGender(Gender.FEMALE);
        studentRegisterRequest2.setEmail("michelle@gmail.com");

        studentService.addStudent(studentRegisterRequest2);

        assertEquals(2, studentService.getListOfAllStudent().size());
    }

    @Test
    void testThatWeCanGetStudentById() throws studentAlreadyExistException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ace");
        studentRegisterRequest.setLastName("check");
        studentRegisterRequest.setEmail("check@gmail.com");
        studentRegisterRequest.setGender(Gender.MALE);
        studentRegisterRequest.setAge("21");

        Student student = studentService.addStudent(studentRegisterRequest).getStudent();

        studentService.getStudentById(student.getStudentId());
        assertEquals("ace", student.getFirstName());
    }

    @Test
    void testThatWeCanGetStudentProfile() throws studentAlreadyExistException, studentWithEmailNotAvailableException {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        studentRegisterRequest.setFirstName("ace");
        studentRegisterRequest.setLastName("check");
        studentRegisterRequest.setEmail("check@gmail.com");
        studentRegisterRequest.setGender(Gender.MALE);
        studentRegisterRequest.setAge("21");

        Student student = studentService.addStudent(studentRegisterRequest).getStudent();

        assertEquals(student.toString(), studentService.getStudentProfile("check@gmail.com"));
    }

}