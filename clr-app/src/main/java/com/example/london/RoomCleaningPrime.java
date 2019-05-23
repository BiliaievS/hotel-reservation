package com.example.london;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Sergii Biliaiev
 * Created on 20/05/2019.
 */
@Component
public class RoomCleaningPrime implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCleaningPrime.class);

    @Value("${amqp.queue.name}")
    private String queueName;


    private RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoomCleaningPrime(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context, ObjectMapper objectMapper) {
        super();
        this.restTemplate = new RestTemplate();
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "http://localhost:8000/api/rooms";
        Room[] roomsArr = restTemplate.getForObject(url, Room[].class);
        List<Room> rooms = Arrays.asList(roomsArr);
        rooms.forEach(room -> {
            try {
                String json = objectMapper.writeValueAsString(room);
                LOGGER.info("Sending message: {}", json);
                rabbitTemplate.convertAndSend(queueName, json);
            } catch (JsonProcessingException e) {
                LOGGER.error("Parsing exception", e);
            }
        });

        context.close();
    }
}
