package com.jedivision.scheduler;

import com.jedivision.api.Instance1;
import com.jedivision.api.Instance2;
import com.jedivision.entity.User;
import com.jedivision.feign.FeignBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Scheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static Random random = ThreadLocalRandom.current();

    private int usersSize = 0;

    private final Instance1 instance1;
    private final Instance2 instance2;

    @Autowired
    public Scheduler(FeignBuilder feignBuilder) {
        this.instance1 = feignBuilder.instance1();
        this.instance2 = feignBuilder.instance2();
    }

    // every 15 seconds
    @Scheduled(cron = "*/15 * * * * *")
    public void start() {
        LOGGER.info("==================================================");

        List<User> users = instance2.findUsers();
        LOGGER.info("Static users size {}", usersSize);
        LOGGER.info("Retrieved users size {}", users.size());
        if (users.size() != usersSize) {
            LOGGER.info("Assert users size ERROR");
        } else {
            LOGGER.info("Assert users size SUCCESS");
        }

        LOGGER.info("Save user...");
        instance1.saveUser();
        usersSize++;

        int bulkSize = randomBulkSize();
        LOGGER.info("Save {} users...", bulkSize);
        instance1.saveUsers(bulkSize);
        usersSize = usersSize + bulkSize;

        LOGGER.info("==================================================");
    }

    private int randomBulkSize() {
        return 2 + random.nextInt(3);
    }
}
