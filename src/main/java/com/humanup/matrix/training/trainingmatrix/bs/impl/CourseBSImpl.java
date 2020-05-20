package com.humanup.matrix.training.trainingmatrix.bs.impl;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseException;
import com.humanup.matrix.training.trainingmatrix.bs.CourseBS;
import com.humanup.matrix.training.trainingmatrix.bs.impl.sender.RabbitMQCourseSender;
import com.humanup.matrix.training.trainingmatrix.dao.CourseDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import com.humanup.matrix.training.trainingmatrix.vo.CourseVO;
import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class CourseBSImpl implements CourseBS {
  @Autowired private CourseDAO courseDAO;
  @Autowired private RabbitMQCourseSender rabbitMQCourseSender;

  @Override
  @Transactional(
      transactionManager = "transactionManagerWrite",
      rollbackFor = CourseException.class)
  public boolean createCourse(final CourseVO course) throws CourseException {
    if (null == course) {
      throw new CourseException();
    }
    rabbitMQCourseSender.send(course);
    return true;
  }

  @Override
  public List<CourseVO> getListCourse() {
    return StreamSupport.stream(courseDAO.findAll().spliterator(), false)
        .map(
            courseFound ->
                CourseVO.builder()
                    .title(courseFound.getTitle())
                    .description(courseFound.getDescription())
                    .startDate(courseFound.getStartDate())
                    .endDate(courseFound.getEndDate())
                    .courseTypeTitle(courseFound.getCourseType().getTypeTitle())
                    .trainerEmail(courseFound.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(courseFound.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public CourseVO getCourseById(final long courseId) {
    final Optional<Course> courseFound = courseDAO.findById(courseId);
    return courseFound
        .map(
            course ->
                CourseVO.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .courseTypeTitle(course.getCourseType().getTypeTitle())
                    .trainerEmail(course.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(course.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .orElse(null);
  }

  @Override
  public CourseVO getCourseByTitle(final String courseTitle) {
    final Optional<Course> courseFound = courseDAO.findByTitle(courseTitle);
    return courseFound
        .map(
            course ->
                CourseVO.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .courseTypeTitle(course.getCourseType().getTypeTitle())
                    .trainerEmail(course.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(course.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .orElse(null);
  }

  @Override
  public List<CourseVO> getListCourseByTypeTitle(final String typeTitle) {
    final List<Course> courseList = courseDAO.findAllByTypeTitle(typeTitle);
    return courseList.stream()
        .map(
            course ->
                CourseVO.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .courseTypeTitle(course.getCourseType().getTypeTitle())
                    .trainerEmail(course.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(course.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<CourseVO> getListCourseByTrainerEmail(final String trainerEmail) {
    final List<Course> courseList = courseDAO.findAllByTrainerEmail(trainerEmail);
    return courseList.stream()
        .map(
            course ->
                CourseVO.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .courseTypeTitle(course.getCourseType().getTypeTitle())
                    .trainerEmail(course.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(course.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<CourseVO> getListCourseByInternEmail(final String internEmail) {
    final List<Course> courseList = courseDAO.findAllByInternEmail(internEmail);
    return courseList.stream()
        .map(
            course ->
                CourseVO.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .startDate(course.getStartDate())
                    .endDate(course.getEndDate())
                    .courseTypeTitle(course.getCourseType().getTypeTitle())
                    .trainerEmail(course.getTrainer().getEmail())
                    .reviewList(
                        Optional.ofNullable(course.getReviewList())
                            .orElseGet(Collections::emptyList).stream()
                            .filter(Objects::nonNull)
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseId(review.getCourse().getId())
                                        .internId(review.getIntern().getId())
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }
}
