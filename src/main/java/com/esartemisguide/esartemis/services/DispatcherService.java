package com.esartemisguide.esartemis.services;

import com.esartemisguide.esartemis.models.SpaceShip;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Slf4j
@Service
public class DispatcherService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jms.queue}")
    String jmsQueue;

    public void sendMessage(String message){
        log.info("Dispatched message: "+message);
        jmsTemplate.convertAndSend(jmsQueue, message);
    }

    public void sendSpaceShip(SpaceShip ship){
        jmsTemplate.send("spaceshipQueue", s -> {
            try {
                TextMessage tm = s.createTextMessage(new ObjectMapper().writeValueAsString(ship));
                tm.setJMSType(SpaceShip.class.getTypeName());
                tm.setStringProperty("mytypeid", SpaceShip.class.getTypeName());
                return tm;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
