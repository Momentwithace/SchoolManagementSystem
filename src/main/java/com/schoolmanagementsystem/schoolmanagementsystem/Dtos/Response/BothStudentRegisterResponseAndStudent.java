package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response;

import com.schoolmanagementsystem.schoolmanagementsystem.Models.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BothStudentRegisterResponseAndStudent {
    private Student student;
    private StudentRegisterResponse studentRegisterResponse;
}
