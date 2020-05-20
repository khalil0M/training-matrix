package com.humanup.matrix.training.trainingmatrix.bs.impl.listener;

import com.humanup.matrix.training.trainingmatrix.dao.CourseDAO;
import com.humanup.matrix.training.trainingmatrix.dao.TrainerDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Trainer;
import com.humanup.matrix.training.trainingmatrix.vo.TrainerVO;
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
public class RabbitMQTrainerListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTrainerListener.class);

  @Autowired private TrainerDAO trainerDAO;
  @Autowired private CourseDAO courseDAO;

  @RabbitListener(queues = {"${trainer.queue.name}"})
  public void receive(final TrainerVO trainer) {
    try {
      LOGGER.info("Received message... {} ", trainer);

      final Trainer trainerToSave =
          Trainer.builder()
              .name(trainer.getName())
              .email(trainer.getEmail())
              .address(trainer.getAddress())
              .phone(trainer.getPhone())
              .build();
      trainerDAO.save(trainerToSave);
    } catch (final Exception ex) {
      LOGGER.info("Error message... {} ", ex.getMessage(), ex);
    }
  }
}
