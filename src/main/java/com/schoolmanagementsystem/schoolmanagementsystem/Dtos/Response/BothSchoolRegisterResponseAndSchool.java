package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response;

import com.schoolmanagementsystem.schoolmanagementsystem.Models.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BothSchoolRegisterResponseAndSchool {
    private School school;
    private SchoolUpdateResponse schoolRegisterResponse;
}
