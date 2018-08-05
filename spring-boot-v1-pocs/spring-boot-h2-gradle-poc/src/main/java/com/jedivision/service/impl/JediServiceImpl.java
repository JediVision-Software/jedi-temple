package com.jedivision.service.impl;

import com.jedivision.dao.JediDao;
import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import com.jedivision.service.JediService;
import com.jedivision.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JediServiceImpl implements JediService {
    // DAO
    private final JediDao jediDao;

    @Autowired
    public JediServiceImpl(JediDao jediDao) {
        this.jediDao = jediDao;
    }


    @Override
    public Jedi findOne(Integer jediId) {
        return jediDao.findOne(jediId);
    }

    @Override
    public List<Jedi> findAll() {
        return StreamUtils.stream(jediDao.findAll()).collect(Collectors.toList());
    }

    @Override
    public List<Jedi> findByRank(Rank rank) {
        return StreamUtils.stream(jediDao.findByRank(rank)).collect(Collectors.toList());
    }
}
