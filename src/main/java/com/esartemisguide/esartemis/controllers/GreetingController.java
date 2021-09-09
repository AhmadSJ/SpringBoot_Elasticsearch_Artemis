package com.esartemisguide.esartemis.controllers;

import com.esartemisguide.esartemis.services.DispatcherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeting")
@Slf4j
public class GreetingController {

    private final DispatcherService dispatcherService;

    @PostMapping("/")
    public String sendMessage(@RequestBody String message) {
        log.info("Send this message to jms queue: " + message);
        dispatcherService.sendMessage(message);
        return "Message sent " + message;
    }
}
