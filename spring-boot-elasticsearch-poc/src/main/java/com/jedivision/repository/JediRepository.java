package com.jedivision.repository;

import com.jedivision.document.Jedi;
import com.jedivision.document.Rank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JediRepository extends ElasticsearchRepository<Jedi, String> {
    List<Jedi> findByForceBetween(Long begin, Long end);
    List<Jedi> findByRankOrderByAge(Rank rank);
}
