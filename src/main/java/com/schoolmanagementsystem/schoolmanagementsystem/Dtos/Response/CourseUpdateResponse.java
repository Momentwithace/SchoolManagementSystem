package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response;

import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Timetable;
import lombok.Data;

@Data

public class CourseUpdateResponse {
    private String message;
    private Timetable timetable;
    private String id;
    public CourseUpdateResponse(String message) {
        this.message = message;
    }
}
