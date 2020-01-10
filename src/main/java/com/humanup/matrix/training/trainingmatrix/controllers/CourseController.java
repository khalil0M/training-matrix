package com.humanup.matrix.training.trainingmatrix.controllers;

import com.humanup.matrix.training.trainingmatrix.bs.CourseBS;
import com.humanup.matrix.training.trainingmatrix.vo.CourseVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseBS courseBS;

    @Operation(summary = "Create new course", description = "Create new course", tags = { "course" })
    @RequestMapping(value="/course", method=RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createCourse(@RequestBody final CourseVO course) {
        final Optional<Object> courseFound = Optional.ofNullable(courseBS.getCourseByTitle(course.getTitle()));
        if(courseFound.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body("Course already exists.");
        }
        courseBS.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @Operation(summary = "Find course by id", description = "Find course by id", tags = { "course" })
    @RequestMapping(value="/course", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCourseById(@RequestParam(value="id") final long id) {
        final Optional<CourseVO> courseFound = Optional.ofNullable(courseBS.getCourseById(id));
        if(courseFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseFound.get());
    }

    @Operation(summary = "Find course by title", description = "Find course by title", tags = { "course" })
    @RequestMapping(value="/course/title", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCourseByTitle(@RequestParam(value="title") final String title) {
        final Optional<CourseVO> courseFound = Optional.ofNullable(courseBS.getCourseByTitle(title));
        if(courseFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseFound.get());
    }

    @Operation(summary = "Find all courses", description = "Find all courses", tags = { "course" })
    @RequestMapping(value="/course/all", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCourseInfo() {
        final List<CourseVO> courseListFound = courseBS.getListCourse();
        if(courseListFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseListFound);
    }

    @Operation(summary = "Find all courses by type title", description = "Find all courses by type title", tags = { "course" })
    @RequestMapping(value="/course/all/typetitle", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getListCourseByTypeTitle(@RequestParam(value="typetitle") final String typetitle) {
        final List<CourseVO> courseListFound = courseBS.getListCourseByTypeTitle(typetitle);
        if(courseListFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseListFound);
    }

    @Operation(summary = "Find all courses by trainer email", description = "Find all courses by trainer email", tags = { "course" })
    @RequestMapping(value="/course/all/trainerEmail", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getListCourseByTrainerEmail(@RequestParam(value="trainerEmail") final String trainerEmail) {
        final List<CourseVO> courseListFound = courseBS.getListCourseByTrainerEmail(trainerEmail);
        if(courseListFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseListFound);
    }

    @Operation(summary = "Find all courses by intern email", description = "Find all courses by intern email", tags = { "course" })
    @RequestMapping(value="/course/all/internEmail", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getListCourseByInternEmail(@RequestParam(value="internEmail") final String internEmail) {
        final List<CourseVO> courseListFound = courseBS.getListCourseByInternEmail(internEmail);
        if(courseListFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseListFound);
    }

    @Operation(summary = "Add trainer to course", description = "Add trainer to course", tags = { "course" })
    @RequestMapping(value="/course/traineremail", method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addTrainer(@RequestParam(value="courseTitle") final String courseTitle, @RequestParam(value="traineremail") final String trainerEmail) {
        final boolean isCourseModified = courseBS.addTrainerToCourse(trainerEmail, courseTitle);
        if(isCourseModified) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Add course type to course", description = "Add course type to course", tags = { "course" })
    @RequestMapping(value="/course/coursetype", method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity addCourseType(@RequestParam(value="courseTitle") final String courseTitle, @RequestParam(value="courseType") final String courseType) {
        final boolean isCourseModified = courseBS.addCourseTypeToCourse(courseType, courseTitle);
        if(isCourseModified) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
