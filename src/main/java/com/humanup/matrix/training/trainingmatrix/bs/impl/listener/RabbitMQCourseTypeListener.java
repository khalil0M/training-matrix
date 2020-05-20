package com.humanup.matrix.training.trainingmatrix.bs.impl.listener;

import com.humanup.matrix.training.trainingmatrix.dao.CourseTypeDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.CourseType;
import com.humanup.matrix.training.trainingmatrix.vo.CourseTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@RefreshScope
public class RabbitMQCourseTypeListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQCourseTypeListener.class);

  @Autowired private CourseTypeDAO courseTypeDAO;

  @RabbitListener(queues = {"${coursetype.queue.name}"})
  public void receive(final CourseTypeVO courseType) {
    try {
      LOGGER.info("Received message... {} ", courseType);

      final CourseType courseTypeToSave =
          CourseType.builder().typeTitle(courseType.getTypeTitle()).build();
      courseTypeDAO.save(courseTypeToSave);
    } catch (final Exception ex) {
      LOGGER.info("Error message... {} ", ex.getMessage(), ex);
    }
  }
}
