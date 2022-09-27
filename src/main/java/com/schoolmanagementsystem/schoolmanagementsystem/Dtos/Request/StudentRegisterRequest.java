package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request;


import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class StudentRegisterRequest {
    @Id
    private String studentId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String age;
    private String email;
}
