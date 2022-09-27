package com.schoolmanagementsystem.schoolmanagementsystem.Service;

import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.CourseRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.UpdateCourseRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.BothCourseRegisterResponseAndCourse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.CourseDeleteResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.CourseRegisterResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Response.CourseUpdateResponse;
import com.schoolmanagementsystem.schoolmanagementsystem.Models.Course;
import com.schoolmanagementsystem.schoolmanagementsystem.Repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{
    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public BothCourseRegisterResponseAndCourse addCourse(CourseRegisterRequest courseRegisterRequest) {
        Course course = Course.builder()
                .courseName(courseRegisterRequest.getCourseName())
                .courseTimetable(courseRegisterRequest.getCourseTimetable()).build();

        courseRepository.save(course);
        return BothCourseRegisterResponseAndCourse.builder()
                .course(course)
                .courseRegisterResponse(new CourseRegisterResponse("Course successfully Added")).build();
    }

    @Override
    public Course getCourse(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public CourseDeleteResponse deleteCourse(String courseId) {
         courseRepository.deleteById(courseId);
        return new CourseDeleteResponse("Course Successfully Deleted!");
    }

    @Override
    public long getNumberOfCourse() {
        return 0;
    }

    @Override
    public List<Course> getListOfCourse() {
        return courseRepository.findAll();
    }

    @Override
    public CourseUpdateResponse updateCourse(UpdateCourseRequest updateCourseRequest) {
        var course = getCourse(updateCourseRequest.getCourseId());
        if(NotNullAndNotEmpty(updateCourseRequest.getCourseName())){
            course.setCourseName(updateCourseRequest.getCourseName());
        }

        if(NotNullAndNotEmpty(String.valueOf(updateCourseRequest.getCourseTimetable()))){
            course.setCourseTimetable(updateCourseRequest.getCourseTimetable());
        }

        var savedCourse = courseRepository.save(course);
        CourseUpdateResponse courseUpdateResponse = new CourseUpdateResponse("Course Details Successfully Updated!");
        courseUpdateResponse.setTimetable(savedCourse.getCourseTimetable());
     return  courseUpdateResponse;
    }

    private boolean NotNullAndNotEmpty(String courseTimetable) {
        return courseTimetable != null && !courseTimetable.equals("");

    }


    @Override
    public String getCourseTimeTable(String courseName) {
        return null;
    }

    @Override
    public void clearDatabase() {
        courseRepository.deleteAll();
    }
}
