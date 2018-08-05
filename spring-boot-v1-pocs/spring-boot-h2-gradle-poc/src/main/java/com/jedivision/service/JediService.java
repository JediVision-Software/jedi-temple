package com.jedivision.service;

import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;

import java.util.List;

public interface JediService {
    Jedi findOne(Integer jediId);
    List<Jedi> findAll();
    List<Jedi> findByRank(Rank rank);
}
