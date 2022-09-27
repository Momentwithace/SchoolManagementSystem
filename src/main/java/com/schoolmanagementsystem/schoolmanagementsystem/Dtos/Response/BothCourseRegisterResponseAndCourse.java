package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response;

import com.schoolmanagementsystem.schoolmanagementsystem.Models.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BothCourseRegisterResponseAndCourse {
    private Course course;
    private CourseRegisterResponse courseRegisterResponse;
}
