package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.CourseRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.UpdateCourseRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothCourseRegisterResponseAndCourse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.CourseDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.CourseUpdateResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Course;

import java.util.List;

public interface CourseService {
    BothCourseRegisterResponseAndCourse addCourse(CourseRegisterRequest courseRegisterRequest);
    Course getCourse(String courseId);
    CourseDeleteResponse deleteCourse(String courseId);
    long getNumberOfCourse();
    List<Course> getListOfCourse();
    CourseUpdateResponse updateCourse(UpdateCourseRequest updatedCourseRequest);
    String getCourseTimeTable(String courseName);
    void clearDatabase();


}
