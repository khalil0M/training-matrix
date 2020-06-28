package com.humanup.matrix.training.trainingmatrix.bs.impl.sender;

import com.humanup.matrix.training.trainingmatrix.vo.CourseTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQCourseTypeSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQCourseTypeSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQCourseTypeSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${coursetype.queue.name}")
    String queueName;

    public void send(final CourseTypeVO courseTypeVO) {
        rabbitTemplate.convertAndSend(queueName, courseTypeVO);
        LOGGER.info("Sending message... {} ", courseTypeVO);
    }

}
