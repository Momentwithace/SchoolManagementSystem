package com.schoolmanagementsystem.schoolmanagementsystem.Models;

import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Timetable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@Builder
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private Timetable courseTimetable;

}
