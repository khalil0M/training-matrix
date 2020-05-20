package com.humanup.matrix.training.trainingmatrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCourseConfig {

  @Value("${course.queue.name}")
  String queueName;

  @Value("${course.exchange.name}")
  String exchange;

  @Value("${course.routing.key}")
  String routingkey;

  @Bean
  Queue courseQueue() {
    return new Queue(queueName, true, false, true);
  }

  @Bean
  DirectExchange courseExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding courseBinding(final Queue courseQueue, final DirectExchange courseExchange) {
    return BindingBuilder.bind(courseQueue).to(courseExchange).with(routingkey);
  }
}
