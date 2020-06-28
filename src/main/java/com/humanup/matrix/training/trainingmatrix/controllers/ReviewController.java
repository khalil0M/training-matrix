package com.humanup.matrix.training.trainingmatrix.controllers;

import com.humanup.matrix.training.trainingmatrix.aop.dto.ReviewException;
import com.humanup.matrix.training.trainingmatrix.bs.ReviewBS;
import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {
  @Autowired private ReviewBS reviewBS;

  @Operation(
      summary = "Create new review",
      description = "Create new review",
      tags = {"review"})
  @RequestMapping(
      value = "/review",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createReview(@RequestBody final ReviewVO review) throws ReviewException {
    final Optional<ReviewVO> reviewFound =
        Optional.ofNullable(reviewBS.getReviewById(review.getCourseId(), review.getInternId()));
    if (reviewFound.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("Review already exists.");
    }
    reviewBS.createReview(review);
    return ResponseEntity.status(HttpStatus.CREATED).body(review);
  }

  @Operation(
      summary = "Find review by id",
      description = "Find review by id.",
      tags = {"review"})
  @RequestMapping(value = "/review", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getReviewById(
      @RequestParam(value = "courseId") final long courseId,
      @RequestParam(value = "internId") final long internId) {
    final Optional<ReviewVO> reviewFound =
        Optional.ofNullable(reviewBS.getReviewById(courseId, internId));
    if (reviewFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(reviewFound.get());
  }

  @Operation(
      summary = "Find review by score",
      description = "Find review by score.",
      tags = {"review"})
  @RequestMapping(value = "/review/all/score", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getReviewByScore(@RequestParam(value = "score") final int score) {
    final List<ReviewVO> reviewListFound = reviewBS.getListReviewByScore(score);
    if (reviewListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(reviewListFound);
  }

  @Operation(
      summary = "Find review by course title",
      description = "Find review by course title",
      tags = {"review"})
  @RequestMapping(value = "/review/all/coursetitle", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllReviewByCourseTitle(
      @RequestParam(value = "courseTitle") final String courseTitle) {
    final List<ReviewVO> reviewListFound = reviewBS.getListReviewByCourseTitle(courseTitle);
    if (reviewListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(reviewListFound);
  }

  @Operation(
      summary = "Find review by intern email",
      description = "Find review by intern email",
      tags = {"review"})
  @RequestMapping(value = "/review/all/internemail", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllReviewByInternEmail(
      @RequestParam(value = "internEmail") final String internEmail) {
    final List<ReviewVO> reviewListFound = reviewBS.getListReviewByInternEmail(internEmail);
    if (reviewListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(reviewListFound);
  }

  @Operation(
      summary = "Find all reviews",
      description = "Find all reviews",
      tags = {"review"})
  @RequestMapping(value = "/review/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllReviewInfo() {
    final List<ReviewVO> reviewListFound = reviewBS.getListReview();
    if (reviewListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(reviewListFound);
  }
}
