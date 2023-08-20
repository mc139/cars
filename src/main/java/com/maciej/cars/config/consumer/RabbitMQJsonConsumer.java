package com.maciej.cars.config.consumer;

import com.maciej.cars.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);


    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void receiveMessage(Car car) {
        logger.info(String.format("Received message: %s", car));
    }
}
