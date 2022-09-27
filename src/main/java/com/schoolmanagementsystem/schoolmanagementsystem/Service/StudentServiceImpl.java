package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.StudentRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothStudentRegisterResponseAndStudent;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.StudentDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.StudentRegisterResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithEmailNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithNameNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Student;
import com.schoolmanagementsystem.schoolmanagementsystem.Repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Override
    public BothStudentRegisterResponseAndStudent addStudent(StudentRegisterRequest studentRegisterRequest) throws studentAlreadyExistException {

        if(findStudentByEmail(studentRegisterRequest.getEmail())) {
            throw new studentAlreadyExistException("Student with " + studentRegisterRequest.getEmail() + " already exist!");}

            Student student = Student.builder()
                    .age(studentRegisterRequest.getAge())
                    .firstName(studentRegisterRequest.getFirstName())
                    .lastName(studentRegisterRequest.getLastName())
                    .gender(studentRegisterRequest.getGender())
                    .email(studentRegisterRequest.getEmail()).build();

            studentRepository.save(student);
            return BothStudentRegisterResponseAndStudent.
                    builder().student(student).
                    studentRegisterResponse(new StudentRegisterResponse("Student successfully added"))
                    .build();


    }

    @Override
    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public StudentDeleteResponse deleteStudent(String studentId) {
        var student = studentRepository.findById(studentId);
        studentRepository.deleteById(studentId);
        return new StudentDeleteResponse("Student Deleted Successfully!");
    }

    @Override
    public long getNumberOfStudent() {
        return studentRepository.findAll().size();
    }

    @Override
    public void clearDatabase() {
        studentRepository.deleteAll();
    }

    @Override
    public List<Student> getListOfStudentWithSameFirstName(String firstName) throws studentWithNameNotAvailableException {
        List<Student> newStudentList = new ArrayList<>();
        for(Student student : studentRepository.findAll()){
            if(student.getFirstName().equalsIgnoreCase(firstName)){
                newStudentList.add(student);
            }
        }

        if(!newStudentList.isEmpty()) {
            return newStudentList;
        }

        throw new studentWithNameNotAvailableException("Student with " + firstName + " doesn't exist!");

    }

    @Override
    public List<Student> getListOfStudentWithSameLastName(String lastName) throws studentWithNameNotAvailableException {
        List<Student> newStudentList = new ArrayList<>();
        for(Student student : studentRepository.findAll()){
            if(student.getLastName().equalsIgnoreCase(lastName)){
                newStudentList.add(student);
            }

        }
        if(!newStudentList.isEmpty()){
            return newStudentList;
        }
        throw new studentWithNameNotAvailableException("Student with " + lastName + " doesn't exist!");
    }




    @Override
    public List<Student> getListOfAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public boolean findStudentByEmail(String email) {
        for(Student student: studentRepository.findAll()){
            if(student.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Student getStudentByEmail(String email) throws studentWithEmailNotAvailableException {
        for(Student student: studentRepository.findAll()){
            if(student.getEmail().equalsIgnoreCase(email)){
                return student;
            }
        }
        throw new studentWithEmailNotAvailableException("Student with " + email + " doesn't exist");
    }

    @Override
    public String getStudentProfile(String email) throws studentWithEmailNotAvailableException {
        return getStudentByEmail(email).toString();
    }


}

