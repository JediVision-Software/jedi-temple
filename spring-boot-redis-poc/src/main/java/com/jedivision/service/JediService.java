package com.jedivision.service;

import com.jedivision.entity.Jedi;

import java.util.List;

public interface JediService {
    List<String> put(String key, Jedi value);
    Jedi get(String key);
}
