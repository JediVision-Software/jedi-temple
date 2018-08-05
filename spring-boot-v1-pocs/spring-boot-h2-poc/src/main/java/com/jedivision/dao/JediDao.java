package com.jedivision.dao;

import com.jedivision.entity.Jedi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JediDao extends CrudRepository<Jedi, Integer> {

}
