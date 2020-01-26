package com.humanup.matrix.training.trainingmatrix.bs.impl.sender;

import com.humanup.matrix.training.trainingmatrix.vo.InternVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQInternSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQInternSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQInternSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${intern.queue.name}")
    String queueName;

    public void send(final InternVO internVO) {
        rabbitTemplate.convertAndSend(queueName, internVO);
        LOGGER.info("Sending message... {} ", internVO);
    }

}
