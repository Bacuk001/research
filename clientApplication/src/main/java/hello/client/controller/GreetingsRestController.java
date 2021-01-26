package hello.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GreetingsRestController {
    private static final String GREETING_EVENT = "greeting_event".toUpperCase();
    @Autowired
    private ApplicationEventPublisher appEventPublisher;

    @GetMapping("/hi")
    public String greet(Principal p) {
        String msg = "hello, " + p.getName() + "!";
        Map<String, Object> messages = new HashMap<>();
        messages.put("controller", msg);
        messages.put("Thread", Thread.currentThread().getName());
        AuditEvent auditEvent = new AuditEvent(p.getName(), GREETING_EVENT, messages);
        this.appEventPublisher.publishEvent(new AuditApplicationEvent(auditEvent));
        return msg;
    }
}
