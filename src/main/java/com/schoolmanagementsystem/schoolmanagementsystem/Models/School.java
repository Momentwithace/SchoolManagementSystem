package com.schoolmanagementsystem.schoolmanagementsystem.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@Builder
public class School {
    @Id
    private String schoolId;
    private String schoolName;
    private String schoolAddress;
    private String schoolEmail;
    private String schoolPhoneNumber;

}
