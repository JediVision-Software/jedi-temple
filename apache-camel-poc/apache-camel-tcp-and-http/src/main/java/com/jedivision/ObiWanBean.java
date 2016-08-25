package com.jedivision;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class ObiWanBean {
    private static final CountDownLatch latch = new CountDownLatch(1);

    public String process(String request) {
        exitCheck(request);
        log.info("Text: " + request);
        return "Jedi says: " + request;
    }

    public String exitCheck(String request) {
        if ("exit".equalsIgnoreCase(request)) {
            log.info("Your eyes can deceive you. Don’t trust them!");
            latch.countDown();
        }
        return request;
    }

    public static CountDownLatch getLatch() {
        return latch;
    }
}
