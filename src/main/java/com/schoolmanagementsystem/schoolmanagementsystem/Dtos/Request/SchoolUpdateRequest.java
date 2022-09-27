package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SchoolUpdateRequest {
    @Id
    private String schoolId;
    private String schoolName;
    private String schoolAddress;
    private String schoolEmail;
    private String schoolPhoneNumber;
}
