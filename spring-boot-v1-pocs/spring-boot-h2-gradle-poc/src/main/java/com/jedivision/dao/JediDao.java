package com.jedivision.dao;

import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JediDao extends CrudRepository<Jedi, Integer> {
    List<Jedi> findByRank(Rank rank);
}
