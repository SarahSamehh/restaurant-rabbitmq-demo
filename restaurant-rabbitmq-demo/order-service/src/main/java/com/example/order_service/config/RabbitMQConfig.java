package com.example.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "restaurant-exchange";
    public static final String KITCHEN_QUEUE = "kitchen.order.queue";
    public static final String ORDER_CREATED_KEY = "order.created";

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue kitchenQueue(){
        return new Queue(KITCHEN_QUEUE);
    }

    @Bean
    Binding binding(
        Queue kitchenQueue, DirectExchange exchange){
        return BindingBuilder.bind(kitchenQueue).to(exchange).with(ORDER_CREATED_KEY);
    }


    @Bean
    MessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }


}
