package com.humanup.matrix.training.trainingmatrix.bs.impl.sender;

import com.humanup.matrix.training.trainingmatrix.vo.CourseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQCourseSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQCourseSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQCourseSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${course.queue.name}")
    String queueName;

    public void send(final CourseVO courseVO) {
        rabbitTemplate.convertAndSend(queueName, courseVO);
        LOGGER.info("Sending message... {} ", courseVO);
    }

}
