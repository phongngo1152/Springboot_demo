package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "UserRequestModel.exchange";

//create-update
    public static final String QUEUE = "UserRequestModel.queue";
    public static final String ROUTING_KEY = "UserRequestModel.routingkey";
//delete
    public static final String QUEUE_DELETE = "UserRequestModel.queue.delete";
    public static final String ROUTING_DELETE = "user.delete";
    // //create-update-Transactional
    public static final String QUEUE_Transactional = "UserRequestModel.queue.Transactional";
    public static final String ROUTING_Transactional = "UserRequestModel.Transactional";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
//delete
    @Bean
    public Queue deleteQueue() {
        return new Queue(QUEUE_DELETE, true);
    }

    @Bean
    public Binding bindingDelete(Queue deleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deleteQueue).to(exchange).with(ROUTING_DELETE);
    }
    //Transactional
    @Bean
    public Queue TransactionalDeleteQueue() {return new Queue(QUEUE_Transactional, true);}
    @Bean
    public Binding bindingTransactionalDelete(Queue TransactionalDeleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(TransactionalDeleteQueue).to(exchange).with(ROUTING_Transactional);
    }

    // Dùng để serialize/deserialize JSON message
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
