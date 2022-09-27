package com.schoolmanagementsystem.schoolmanagementsystem.Repositories;

import com.schoolmanagementsystem.schoolmanagementsystem.Models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}
