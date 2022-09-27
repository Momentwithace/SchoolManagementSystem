package com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request;

import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Timetable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CourseRegisterRequest {
    private String courseName;
    private Timetable CourseTimetable;
}
