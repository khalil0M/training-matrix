package com.humanup.matrix.training.trainingmatrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCourseTypeConfig {

  @Value("${coursetype.queue.name}")
  String queueName;

  @Value("${coursetype.exchange.name}")
  String exchange;

  @Value("${coursetype.routing.key}")
  String routingkey;

  @Bean
  Queue courseTypeQueue() {
    return new Queue(queueName, true, false, true);
  }

  @Bean
  DirectExchange courseTypeExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding courseTypeBinding(final Queue courseTypeQueue, final DirectExchange courseTypeExchange) {
    return BindingBuilder.bind(courseTypeQueue).to(courseTypeExchange).with(routingkey);
  }
}
