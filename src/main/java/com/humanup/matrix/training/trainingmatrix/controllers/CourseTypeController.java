package com.humanup.matrix.training.trainingmatrix.controllers;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseTypeException;
import com.humanup.matrix.training.trainingmatrix.bs.CourseTypeBS;
import com.humanup.matrix.training.trainingmatrix.vo.CourseTypeVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseTypeController {
  @Autowired private CourseTypeBS courseTypeBS;

  @Operation(
      summary = "Create new course type",
      description = "Create new course type",
      tags = {"course type"})
  @RequestMapping(
      value = "/coursetype",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createCourseType(@RequestBody final CourseTypeVO courseType)
      throws CourseTypeException {
    final Optional<Object> courseTypeFound =
        Optional.ofNullable(courseTypeBS.getCourseTypeByTitle(courseType.getTypeTitle()));
    if (courseTypeFound.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("Course type already exists.");
    }
    courseTypeBS.createCourseType(courseType);
    return ResponseEntity.status(HttpStatus.CREATED).body(courseType);
  }

  @Operation(
      summary = "Find course type by id",
      description = "Find course type by id.",
      tags = {"course type"})
  @RequestMapping(value = "/coursetype", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getCourseTypeById(@RequestParam(value = "id") final long id) {
    final Optional<CourseTypeVO> courseTypeFound =
        Optional.ofNullable(courseTypeBS.getCourseTypeById(id));
    if (courseTypeFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(courseTypeFound.get());
  }

  @Operation(
      summary = "Find course type by title",
      description = "Find course type by title.",
      tags = {"course type"})
  @RequestMapping(value = "/coursetype/title", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getCourseTypeByTitle(
      @RequestParam(value = "typeTitle") final String typeTitle) {
    final Optional<CourseTypeVO> courseTypeFound =
        Optional.ofNullable(courseTypeBS.getCourseTypeByTitle(typeTitle));
    if (courseTypeFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(courseTypeFound.get());
  }

  @Operation(
      summary = "Find all course types",
      description = "Find all course types",
      tags = {"course type"})
  @RequestMapping(value = "/coursetype/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllCourseTypeInfo() {
    final List<CourseTypeVO> courseTypeListFound = courseTypeBS.getListCourseType();
    if (courseTypeListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(courseTypeListFound);
  }
}
