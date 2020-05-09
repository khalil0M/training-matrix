package com.humanup.matrix.training.trainingmatrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTrainerConfig {

  @Value("${trainer.queue.name}")
  String queueName;

  @Value("${trainer.exchange.name}")
  String exchange;

  @Value("${trainer.routing.key}")
  String routingkey;

  @Bean
  Queue trainerQueue() {
    return new Queue(queueName, true, false, true);
  }

  @Bean
  DirectExchange trainerExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding trainerBinding(final Queue trainerQueue, final DirectExchange trainerExchange) {
    return BindingBuilder.bind(trainerQueue).to(trainerExchange).with(routingkey);
  }
}
