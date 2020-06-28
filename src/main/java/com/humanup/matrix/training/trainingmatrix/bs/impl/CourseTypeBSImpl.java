package com.humanup.matrix.training.trainingmatrix.bs.impl;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseException;
import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseTypeException;
import com.humanup.matrix.training.trainingmatrix.bs.CourseTypeBS;
import com.humanup.matrix.training.trainingmatrix.bs.impl.sender.RabbitMQCourseTypeSender;
import com.humanup.matrix.training.trainingmatrix.dao.CourseTypeDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.CourseType;
import com.humanup.matrix.training.trainingmatrix.vo.CourseTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class CourseTypeBSImpl implements CourseTypeBS {
  @Autowired private CourseTypeDAO courseTypeDAO;
  @Autowired private RabbitMQCourseTypeSender rabbitMQCourseTypeSender;

  @Override
  @Transactional(
      transactionManager = "transactionManagerWrite",
      rollbackFor = CourseTypeException.class)
  public boolean createCourseType(final CourseTypeVO courseType) throws CourseTypeException {
    if (null == courseType) {
      throw new CourseTypeException();
    }
    rabbitMQCourseTypeSender.send(courseType);
    return true;
  }

  @Override
  public List<CourseTypeVO> getListCourseType() {
    return StreamSupport.stream(courseTypeDAO.findAll().spliterator(), false)
        .map(
            courseType ->
                CourseTypeVO.builder()
                    .typeTitle(courseType.getTypeTitle())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public CourseTypeVO getCourseTypeById(final long id) {
    final Optional<CourseType> courseTypeFound = courseTypeDAO.findById(id);
    return courseTypeFound
        .map(
            courseType ->
                CourseTypeVO.builder()
                    .typeTitle(courseType.getTypeTitle())
                    .build())
        .orElse(null);
  }

  @Override
  public CourseTypeVO getCourseTypeByTitle(final String title) {
    final Optional<CourseType> courseTypeFound = courseTypeDAO.findByTypeTitle(title);
    return courseTypeFound
        .map(
            courseType ->
                CourseTypeVO.builder()
                    .typeTitle(courseType.getTypeTitle())
                    .build())
        .orElse(null);
  }
}
