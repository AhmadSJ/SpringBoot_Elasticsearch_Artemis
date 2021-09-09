package com.esartemisguide.esartemis.services;

import com.esartemisguide.esartemis.models.SpaceShip;
import com.esartemisguide.esartemis.repositories.SpaceShipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiverService {

    private final SpaceShipRepository spaceShipRepository;

    @JmsListener(destination = "${jms.queue}")
    public void receiveMessage(String message){
        log.info("Received message: "+message);
    }

    @JmsListener(destination = "spaceshipQueue", containerFactory = "spaceshipFactory")
    public void handle(SpaceShip ship){
        spaceShipRepository.save(ship);
        log.info("SpaceSchip : " + ship.getName() + " has been saved to ElasticSearch");
    }

}
