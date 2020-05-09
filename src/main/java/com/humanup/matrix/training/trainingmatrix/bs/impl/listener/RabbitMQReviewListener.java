package com.humanup.matrix.training.trainingmatrix.bs.impl.listener;

import com.humanup.matrix.training.trainingmatrix.dao.CourseDAO;
import com.humanup.matrix.training.trainingmatrix.dao.InternDAO;
import com.humanup.matrix.training.trainingmatrix.dao.ReviewDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Intern;
import com.humanup.matrix.training.trainingmatrix.dao.entities.InternCourseId;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Review;
import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableRabbit
@RefreshScope
public class RabbitMQReviewListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQReviewListener.class);

  @Autowired private InternDAO internDAO;
  @Autowired private CourseDAO courseDAO;
  @Autowired private ReviewDAO reviewDAO;

  @RabbitListener(queues = {"${review.queue.name}"})
  public void receive(final ReviewVO review) {
    try {
      LOGGER.info("Received message... {} ", review);
      final Optional<Intern> intern = internDAO.findById(review.getInternId());
      final Optional<Course> course = courseDAO.findById(review.getCourseId());

      if (!intern.isPresent() || !course.isPresent()) {
        LOGGER.info("Received message as generic: {}", review);
      }

      final Review reviewToSave =
          Review.builder()
              .id(
                  InternCourseId.builder()
                      .courseId(review.getCourseId())
                      .internId(review.getInternId())
                      .build())
              .intern(intern.get())
              .course(course.get())
              .score(review.getScore())
              .createdOn(review.getCreatedOn())
              .build();
      reviewDAO.save(reviewToSave);
    } catch (final Exception ex) {
      LOGGER.info("Error message... {} ", ex.getMessage(), ex);
    }
  }
}
