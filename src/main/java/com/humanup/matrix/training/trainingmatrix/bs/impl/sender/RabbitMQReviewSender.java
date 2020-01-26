package com.humanup.matrix.training.trainingmatrix.bs.impl.sender;

import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReviewSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQReviewSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQReviewSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${review.queue.name}")
    String queueName;

    public void send(final ReviewVO reviewVO) {
        rabbitTemplate.convertAndSend(queueName, reviewVO);
        LOGGER.info("Sending message... {} ", reviewVO);
    }

}
