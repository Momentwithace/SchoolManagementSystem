package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request;

import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Timetable;
import lombok.Data;


@Data

public class UpdateCourseRequest {
    private String courseId;
    private String courseName;
    private Timetable courseTimetable;
}
