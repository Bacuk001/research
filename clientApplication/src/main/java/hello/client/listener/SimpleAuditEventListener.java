package hello.client.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static java.lang.Thread.currentThread;

@Component
public class SimpleAuditEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuditEventListener.class);

    @EventListener(AuditApplicationEvent.class)
    @Async
    public void onAuditEvent(AuditApplicationEvent event) {
        LOGGER.info("audit event: {} - handled in {}", event.getAuditEvent(), currentThread().getName());
    }
}
