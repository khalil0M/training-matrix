package com.humanup.matrix.training.trainingmatrix.bs;

import com.humanup.matrix.training.trainingmatrix.vo.CourseTypeVO;

import java.util.List;

public interface CourseTypeBS {
    boolean createCourseType(CourseTypeVO courseType);
    List<CourseTypeVO> getListCourseType();
    CourseTypeVO getCourseTypeById(long id);
    CourseTypeVO getCourseTypeByTitle(String title);
}
