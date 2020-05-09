package com.humanup.matrix.training.trainingmatrix.bs;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseException;
import com.humanup.matrix.training.trainingmatrix.vo.CourseVO;

import java.util.List;

public interface CourseBS {
  boolean createCourse(CourseVO course) throws CourseException;

  List<CourseVO> getListCourse();

  CourseVO getCourseById(long courseId);

  CourseVO getCourseByTitle(String courseTitle);

  List<CourseVO> getListCourseByTypeTitle(String typeTitle);

  List<CourseVO> getListCourseByTrainerEmail(String trainerEmail);

  List<CourseVO> getListCourseByInternEmail(String internEmail);
}
