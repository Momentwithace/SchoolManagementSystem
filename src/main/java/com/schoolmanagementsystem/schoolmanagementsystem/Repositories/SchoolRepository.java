package com.schoolmanagementsystem.schoolmanagementsystem.Repositories;

import com.schoolmanagementsystem.schoolmanagementsystem.Models.School;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, String> {
    School findBySchoolName(String schoolName);
}
