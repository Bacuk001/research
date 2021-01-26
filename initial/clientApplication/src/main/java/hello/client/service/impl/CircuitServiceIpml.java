package hello.client.service.impl;

import hello.client.listener.SimpleAuditEventListener;
import hello.client.service.CircuitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

/**
 * Using Spring Retry for service with the backoff and attempts.
 */

@Service
public class CircuitServiceIpml implements CircuitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuditEventListener.class);


    @Retryable(include = Exception.class, maxAttempts = 5, backoff = @Backoff(multiplier = 5))
    @Override
    public String execute(int taskNumber) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        Date date = new Date(currentTimeMillis());
        LOGGER.info("{} ... Execution time: {} - {}", taskNumber, Thread.currentThread().getName(), formatter.format(date));
        throw new Exception("");
    }

    @Recover
    public String recoverForGreeting(Exception e) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        Date date = new Date(currentTimeMillis());
        LOGGER.error("recoverForGreeting {}", formatter.format(date));
        return "OHAI";
    }

}
