package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolUpdateRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothSchoolRegisterResponseAndSchool;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.SchoolDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.SchoolUpdateResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithEmailDoesntExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithNameAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.School;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SchoolService {
    BothSchoolRegisterResponseAndSchool registerSchool(SchoolRegisterRequest schoolRegisterRequest) throws SchoolWithNameAlreadyExistException;
    School getSchool(String schoolId);
    SchoolDeleteResponse deleteSchool(String schoolId);
    School findByEmail(String email) throws SchoolWithEmailDoesntExistException;
    void clearDatabase();
    SchoolUpdateResponse updateSchool(SchoolUpdateRequest schoolUpdateRequest);
    long getNumberOfSchool();
    List<School> getListOfAllSchool();
    String getSchoolProfile(String schoolName);
}
