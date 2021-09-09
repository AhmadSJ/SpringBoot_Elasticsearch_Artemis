package com.esartemisguide.esartemis.controllers;

import com.esartemisguide.esartemis.models.SpaceShip;
import com.esartemisguide.esartemis.services.DispatcherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SpaceShipController {

    private final DispatcherService dispatcherService;

    @PostMapping("/spaceship/")
    public String sendShip(@RequestBody SpaceShip ship) {
        log.info("Send this spaceship to Artemis " + ship);
        dispatcherService.sendSpaceShip(ship);
        return "Ship sent";
    }
}
