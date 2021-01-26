package hello.client.controller;

import hello.client.handler.event.HappyEvent;
import hello.client.handler.event.SadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionalRestController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("eventHappy")
    public ResponseEntity eventHappy() {
        publisher.publishEvent(new HappyEvent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("eventSad")
    public ResponseEntity sedEvent() {
        publisher.publishEvent(new SadEvent());
        return ResponseEntity.ok().build();
    }
}
