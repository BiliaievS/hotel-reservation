package com.example.london;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Sergii Biliaiev
 * Created on 20/05/2019.
 */
@Component
public class RoomCleanerProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCleanerProcessor.class);

    private final ObjectMapper objectMapper;

    public RoomCleanerProcessor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void receiveMessage(String json) {
        LOGGER.info("Message received");
//        LOGGER.info("Message received: {}", json);
        try {
            Room room = this.objectMapper.readValue(json, Room.class);
            LOGGER.info("Room is ready to cleaning: {}", room);
        } catch (IOException e) {
            LOGGER.error("Error to parse", e);
        }

    }
}

