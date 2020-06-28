package com.humanup.matrix.training.trainingmatrix.bs.impl.listener;

import com.humanup.matrix.training.trainingmatrix.dao.CourseDAO;
import com.humanup.matrix.training.trainingmatrix.dao.CourseTypeDAO;
import com.humanup.matrix.training.trainingmatrix.dao.ReviewDAO;
import com.humanup.matrix.training.trainingmatrix.dao.TrainerDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import com.humanup.matrix.training.trainingmatrix.dao.entities.CourseType;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Review;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Trainer;
import com.humanup.matrix.training.trainingmatrix.vo.CourseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@EnableRabbit
@RefreshScope
public class RabbitMQCourseListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQCourseListener.class);

  @Autowired private CourseDAO courseDAO;
  @Autowired private CourseTypeDAO courseTypeDAO;
  @Autowired private TrainerDAO trainerDAO;
  @Autowired private ReviewDAO reviewDAO;

  @RabbitListener(queues = {"${course.queue.name}"})
  public void receive(final CourseVO course) {
    try {
      LOGGER.info("Received message... {} ", course);
      final Optional<CourseType> courseType =
          courseTypeDAO.findByTypeTitle(course.getCourseTypeTitle());
      final Optional<Trainer> trainer = trainerDAO.findByEmail(course.getTrainerEmail());
      final List<Review> reviewList = reviewDAO.findAllByCourseTitle(course.getTitle());

      if (!courseType.isPresent() || !trainer.isPresent() || reviewList.isEmpty()) {
        LOGGER.info("Received message as generic: {}", course);
      }

      final Course courseToSave =
          Course.builder()
              .title(course.getTitle())
              .description(course.getDescription())
              .startDate(course.getStartDate())
              .endDate(course.getEndDate())
              .courseType(courseType.get())
              .trainer(trainer.get())
              .reviewList(reviewList)
              .build();
      courseDAO.save(courseToSave);
    } catch (final Exception ex) {
      LOGGER.info("Error message... {} ", ex.getMessage(), ex);
    }
  }
}
