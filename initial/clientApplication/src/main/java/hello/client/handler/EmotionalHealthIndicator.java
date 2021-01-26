package hello.client.handler;

import hello.client.handler.event.EmotionalEvent;
import hello.client.handler.event.SadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class EmotionalHealthIndicator extends AbstractHealthIndicator {
    private Date when;
    private EmotionalEvent event;

    /**
     * We’ll connect this listener method to ApplicationContext events using the
     *
     * @EventListener annotation.
     */
    @EventListener
    public void onHealthEvent(EmotionalEvent event) {
        this.event = event;
        this.when = new Date();
    }

    /**
     * The doHealthCheck method uses the Health.Builder to toggle the state of
     * the health indicator based on the last known, recorded EmotionalEvent.
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        Optional.ofNullable(this.event)
                .ifPresent(evt -> doHealth(event, builder));
    }

    private void doHealth(EmotionalEvent event, Health.Builder builder) {
        Class<? extends EmotionalEvent> eventClass = event.getClass();
        Health.Builder healthBuilder = eventClass.isAssignableFrom(SadEvent.class) ? builder.down() : builder.up();
        String eventTimeAsString = when.toInstant().toString();
        healthBuilder.withDetail("class", eventClass).withDetail("when", eventTimeAsString);
    }
}
