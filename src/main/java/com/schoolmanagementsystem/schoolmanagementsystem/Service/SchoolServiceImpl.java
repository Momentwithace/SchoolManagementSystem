package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.SchoolUpdateRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothSchoolRegisterResponseAndSchool;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.SchoolDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.SchoolUpdateResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithEmailDoesntExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Exceptions.SchoolWithNameAlreadyExistException;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.School;
import com.schoolmanagementsystem.schoolmanagementsystem.Repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public BothSchoolRegisterResponseAndSchool registerSchool(SchoolRegisterRequest schoolRegisterRequest) throws SchoolWithNameAlreadyExistException {
        if(VerifySchoolName(schoolRegisterRequest.getSchoolName())){
            throw new SchoolWithNameAlreadyExistException("School with " + schoolRegisterRequest.getSchoolName()+ " already exist!");
        }
        School school = School.builder()
                .schoolName(schoolRegisterRequest.getSchoolName())
                .schoolEmail(schoolRegisterRequest.getSchoolEmail())
                .schoolPhoneNumber(schoolRegisterRequest.getSchoolPhoneNumber())
                .schoolAddress(schoolRegisterRequest.getSchoolAddress()).build();

        schoolRepository.save(school);

        return BothSchoolRegisterResponseAndSchool.builder()
                .school(school)
                .schoolRegisterResponse(new SchoolUpdateResponse("School Successfully Added!")).build();
    }

    private boolean VerifySchoolName(String schoolName) {
        for(School school : schoolRepository.findAll()){
            if(school.getSchoolName().equalsIgnoreCase(schoolName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public School getSchool(String schoolId) {
        return schoolRepository.findById(schoolId).get();
    }

    @Override
    public SchoolDeleteResponse deleteSchool(String schoolId) {
        var school = schoolRepository.findById(schoolId);
        schoolRepository.delete(school.get());
        return new SchoolDeleteResponse("School Successfully Deleteed!");
    }

    @Override
    public School findByEmail(String email) throws SchoolWithEmailDoesntExistException {
        for(School school : schoolRepository.findAll()){
            if(school.getSchoolEmail().equalsIgnoreCase(email)){
                return school;
            }
        }
        throw new SchoolWithEmailDoesntExistException("School with " + email + " doesn't exist!");
    }

    @Override
    public void clearDatabase() {
        schoolRepository.deleteAll();
    }

    @Override
    public SchoolUpdateResponse updateSchool(SchoolUpdateRequest schoolUpdateRequest) {
        var school = getSchool(schoolUpdateRequest.getSchoolId());
        if(IsNotNullOrEmpty(schoolUpdateRequest.getSchoolName())){
            school.setSchoolEmail(schoolUpdateRequest.getSchoolName());
        }
        if(IsNotNullOrEmpty(schoolUpdateRequest.getSchoolAddress())){
            school.setSchoolAddress(schoolUpdateRequest.getSchoolAddress());
        }
        if(IsNotNullOrEmpty(schoolUpdateRequest.getSchoolPhoneNumber())){
            school.setSchoolPhoneNumber(schoolUpdateRequest.getSchoolPhoneNumber());
        }
        if(IsNotNullOrEmpty(schoolUpdateRequest.getSchoolEmail())){
            school.setSchoolEmail(schoolUpdateRequest.getSchoolEmail());
        }
        schoolRepository.save(school);
        return new SchoolUpdateResponse("School with details successfully updated!");
    }

    private boolean IsNotNullOrEmpty(String data) {
        return data != null && !data.equals("");
    }

    @Override
    public long getNumberOfSchool() {
        return schoolRepository.count();
    }

    @Override
    public List<School> getListOfAllSchool() {
        return schoolRepository.findAll();
    }

    @Override
    public String getSchoolProfile(String schoolName) {
        return schoolRepository.findBySchoolName(schoolName).toString();
    }

}
