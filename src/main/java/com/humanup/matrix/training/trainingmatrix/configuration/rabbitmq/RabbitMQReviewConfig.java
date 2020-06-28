package com.humanup.matrix.training.trainingmatrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQReviewConfig {

  @Value("${review.queue.name}")
  String queueName;

  @Value("${review.exchange.name}")
  String exchange;

  @Value("${review.routing.key}")
  String routingkey;

  @Bean
  Queue reviewQueue() {
    return new Queue(queueName, true, false, true);
  }

  @Bean
  DirectExchange reviewExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding reviewBinding(final Queue reviewQueue, final DirectExchange reviewExchange) {
    return BindingBuilder.bind(reviewQueue).to(reviewExchange).with(routingkey);
  }
}
