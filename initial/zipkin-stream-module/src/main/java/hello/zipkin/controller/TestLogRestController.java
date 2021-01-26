package hello.zipkin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestLogRestController {
    private static final String GREETING_EVENT = "greeting_event".toUpperCase();

    @GetMapping("/hi")
    public ResponseEntity<?> greet(Principal p) {
        log.info("Published current thread {}", Thread.currentThread().getName());
        return ResponseEntity.ok()
                .build();
    }
}
