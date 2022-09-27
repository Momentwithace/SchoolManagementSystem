package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.StudentRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothStudentRegisterResponseAndStudent;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.StudentDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithEmailNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.studentWithNameNotAvailableException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Student;


import java.util.List;


public interface StudentService {
    BothStudentRegisterResponseAndStudent addStudent(StudentRegisterRequest studentRegisterRequest) throws studentAlreadyExistException;
    Student getStudentById(String studentId);
    StudentDeleteResponse deleteStudent(String studentId);
    long getNumberOfStudent();
    void clearDatabase();
    List<Student> getListOfStudentWithSameFirstName(String firstName) throws studentWithNameNotAvailableException;
    List<Student> getListOfStudentWithSameLastName(String lastName) throws studentWithNameNotAvailableException;
    List<Student> getListOfAllStudent();
    boolean findStudentByEmail(String email);
    Student getStudentByEmail(String email) throws studentWithEmailNotAvailableException;
    String getStudentProfile(String studentName) throws studentWithEmailNotAvailableException;

}
