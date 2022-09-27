package com.schoolmanagementsystem.schoolmanagementsystem.Service;


import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.CourseRegisterRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Dtos.Request.UpdateCourseRequest;
import com.schoolmanagementsystem.schoolmanagementsystem.Enum.Timetable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private  CourseService courseService;

    @BeforeEach
    void setUp(){
        courseService.clearDatabase();
    }

    @Test
    void testThatCourseListIsEmpty(){
        assertTrue(courseService.getListOfCourse().isEmpty());
    }

    @Test
    void testThatYouCanAddCourse(){
        CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
        courseRegisterRequest.setCourseName("Java");
        courseRegisterRequest.setCourseTimetable(Timetable.MONDAY);
        courseService.addCourse(courseRegisterRequest);

        assertEquals(1, courseService.getListOfCourse().size());
    }

    @Test
    void testThatWeCanDeleteCourseUsingCourseName(){
        CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
        courseRegisterRequest.setCourseName("Python");
        courseRegisterRequest.setCourseTimetable(Timetable.THURSDAY);
        var savedCourse =courseService.addCourse(courseRegisterRequest);

        courseService.deleteCourse(savedCourse.getCourse().getCourseId());

        assertEquals(0, courseService.getListOfCourse().size());
    }

    @Test
    void testThatWeCanGetCourse(){
        CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
        courseRegisterRequest.setCourseName("JavaScript");
        courseRegisterRequest.setCourseTimetable(Timetable.FRIDAY);
        var savedCourse = courseService.addCourse(courseRegisterRequest);

        assertEquals("JavaScript", savedCourse.getCourse().getCourseName());
    }

    @Test
    void testThatWeCanUpdatedCourse(){
        CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
        courseRegisterRequest.setCourseName("Html-Css");
        courseRegisterRequest.setCourseTimetable(Timetable.WEDNESDAY);
        var savedCourse = courseService.addCourse(courseRegisterRequest);

        UpdateCourseRequest updateCourseRequest = new UpdateCourseRequest();
        updateCourseRequest.setCourseId(savedCourse.getCourse().getCourseId());
        updateCourseRequest.setCourseTimetable(Timetable.FRIDAY);
        var updatedCourse =  courseService.updateCourse(updateCourseRequest);

        assertEquals(Timetable.FRIDAY, updatedCourse.getTimetable());
    }
}

