package com.humanup.matrix.training.trainingmatrix.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQInternConfig {

  @Value("${intern.queue.name}")
  String queueName;

  @Value("${intern.exchange.name}")
  String exchange;

  @Value("${intern.routing.key}")
  String routingkey;

  @Bean
  Queue internQueue() {
    return new Queue(queueName, true, false, true);
  }

  @Bean
  DirectExchange internExchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  Binding internBinding(final Queue internQueue, final DirectExchange internExchange) {
    return BindingBuilder.bind(internQueue).to(internExchange).with(routingkey);
  }
}
