package com.humanup.matrix.training.trainingmatrix.bs.impl.sender;

import com.humanup.matrix.training.trainingmatrix.vo.TrainerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTrainerSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTrainerSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQTrainerSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${trainer.exchange.name}")
    String exchange;

    @Value("${trainer.routing.key}")
    String routingkey;

    public void send(final TrainerVO trainerVO) {
        rabbitTemplate.convertAndSend(exchange, routingkey, trainerVO);
        LOGGER.info("Sending message... {} ", trainerVO);
    }

}
