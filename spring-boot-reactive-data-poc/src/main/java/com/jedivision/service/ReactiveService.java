package com.jedivision.service;

import com.jedivision.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReactiveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveService.class);

    public void demo() {
        LOGGER.info("Reactive Demo...");
    }
}
