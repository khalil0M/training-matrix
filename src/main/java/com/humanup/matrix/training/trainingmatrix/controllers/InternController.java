package com.humanup.matrix.training.trainingmatrix.controllers;

import com.humanup.matrix.training.trainingmatrix.aop.dto.InternException;
import com.humanup.matrix.training.trainingmatrix.bs.InternBS;
import com.humanup.matrix.training.trainingmatrix.vo.InternVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InternController {
  @Autowired private InternBS internBS;

  @Operation(
      summary = "Create new intern",
      description = "Create new intern",
      tags = {"intern"})
  @RequestMapping(
      value = "/intern",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createIntern(@RequestBody final InternVO intern) throws InternException {
    final Optional<Object> internFound =
        Optional.ofNullable(internBS.getInternByEmail(intern.getEmailPerson()));
    if (internFound.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("Intern already exists.");
    }
    internBS.createIntern(intern);
    return ResponseEntity.status(HttpStatus.CREATED).body(intern);
  }

  @Operation(
      summary = "Find intern by id",
      description = "Find intern by id.",
      tags = {"intern"})
  @RequestMapping(value = "/intern", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getInternById(@RequestParam(value = "id") final long id) {
    final Optional<InternVO> internFound = Optional.ofNullable(internBS.getInternById(id));
    if (internFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(internFound.get());
  }

  @Operation(
      summary = "Find intern by email",
      description = "Find intern by email.",
      tags = {"intern"})
  @RequestMapping(value = "/intern/email", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getInternByEmail(
      @RequestParam(value = "email", defaultValue = "robot@sqli.com") final String email) {
    final Optional<InternVO> internFound = Optional.ofNullable(internBS.getInternByEmail(email));
    if (internFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(internFound.get());
  }

  @Operation(
      summary = "Find all interns",
      description = "Find all interns",
      tags = {"intern"})
  @RequestMapping(value = "/intern/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllIntern() {
    final List<InternVO> internListFound = internBS.getListIntern();
    if (internListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(internListFound);
  }

  @Operation(
      summary = "Find all interns by course title",
      description = "Find all interns by course title",
      tags = {"intern"})
  @RequestMapping(value = "/intern/all/coursetitle", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllInternByCourseTitle(
      @RequestParam(value = "courseTitle") final String courseTitle) {
    final List<InternVO> internListFound = internBS.getListInternByCourseTitle(courseTitle);
    if (internListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(internListFound);
  }
}
