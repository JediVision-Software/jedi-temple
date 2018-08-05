package com.jedivision.service.impl;

import com.jedivision.entity.Jedi;
import com.jedivision.service.JediService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class JediServiceImpl implements JediService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JediServiceImpl.class);

    private final RedisTemplate<String, Jedi> redisTemplate;

    @Autowired
    public JediServiceImpl(RedisTemplate<String, Jedi> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<String> put(String key, Jedi value) {
        ValueOperations<String, Jedi> ops = redisTemplate.opsForValue();
        if (!redisTemplate.hasKey(key)) {
            ops.set(key, value);
            LOGGER.info("Jedi " + key + " are created");
        } else {
            ops.set(key, value);
            LOGGER.info("Jedi " + key + " are updated");
        }
        return Collections.singletonList("OK");
    }

    @Override
    public Jedi get(String key) {
        ValueOperations<String, Jedi> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }
}
