package com.humanup.matrix.training.trainingmatrix.bs.impl.listener;

import com.humanup.matrix.training.trainingmatrix.dao.InternDAO;
import com.humanup.matrix.training.trainingmatrix.dao.ReviewDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Intern;
import com.humanup.matrix.training.trainingmatrix.vo.InternVO;
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
public class RabbitMQInternListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQInternListener.class);

  @Autowired private InternDAO internDAO;
  @Autowired private ReviewDAO reviewDAO;

  @RabbitListener(queues = {"${intern.queue.name}"})
  public void receive(final InternVO intern) {
    try {
      LOGGER.info("Received message... {} ", intern);

      final Intern internToSave = Intern.builder().emailPerson(intern.getEmailPerson()).build();
      internDAO.save(internToSave);
    } catch (final Exception ex) {
      LOGGER.info("Error message... {} ", ex.getMessage(), ex);
    }
  }
}
