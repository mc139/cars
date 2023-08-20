package com.maciej.cars.config.publisher;

import com.maciej.cars.config.consumer.RabbitMQConsumer;
import com.maciej.cars.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingKey;

    private final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Car car) {
        logger.info("Sending json message: {}", car);

        rabbitTemplate.convertAndSend(exchange, routingKey, car);
    }

}
