package com.jedivision.service;

import com.jedivision.document.Equipment;
import com.jedivision.document.EquipmentType;
import com.jedivision.document.Jedi;
import com.jedivision.document.Rank;
import com.jedivision.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JediService {
    private static final String FORCE = "force";
    private static final String RANK = "rank";
    private static final String AGE = "age";

    private final JediRepository jediRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public JediService(JediRepository jediRepository,
                       ElasticsearchTemplate elasticsearchTemplate) {
        this.jediRepository = jediRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public void index() {
        elasticsearchTemplate.deleteIndex(Jedi.class);
        elasticsearchTemplate.createIndex(Jedi.class);
        elasticsearchTemplate.putMapping(Jedi.class);
        elasticsearchTemplate.refresh(Jedi.class);
    }

    public void saveJedis() {
        // Yoda
        Jedi jedi1 = Jedi.builder()
                .fullName("Yoda")
                .rank(Rank.MASTER)
                .age(700)
                .force(1000L)
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.ROBE)
                                .value(5)
                                .build()
                )
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.LIGHTSABER)
                                .value(20)
                                .build()
                )
                .build();

        // Qui-Gon Jinn
        Jedi jedi2 = Jedi.builder()
                .fullName("Qui-Gon Jinn")
                .rank(Rank.MASTER)
                .age(50)
                .force(950L)
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.ARMOR)
                                .value(7)
                                .build()
                )
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.LIGHTSABER)
                                .value(15)
                                .build()
                )
                .build();

        // Obi-Wan Kenobi
        Jedi jedi3 = Jedi.builder()
                .fullName("Obi-Wan Kenobi")
                .rank(Rank.PADAWAN)
                .age(20)
                .force(850L)
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.ARMOR)
                                .value(10)
                                .build()
                )
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.ROBE)
                                .value(10)
                                .build()
                )
                .equipment(
                        Equipment.builder()
                                .equipmentType(EquipmentType.LIGHTSABER)
                                .value(15)
                                .build()
                )
                .build();

        // Anakin Skywalker
        Jedi jedi4 = Jedi.builder()
                .fullName("Anakin Skywalker")
                .rank(Rank.YOUNGLING)
                .age(11)
                .force(1001L)
                .build();

        // saving
        jediRepository.save(Arrays.asList(jedi1, jedi2, jedi3, jedi4));
    }

    public Iterable<Jedi> findAll() {
        return jediRepository.findAll();
    }

    public Iterable<Jedi> findAllSortedByForceByAge() {
        Sort force = new Sort(Sort.Direction.DESC, FORCE);
        Sort age = new Sort(Sort.Direction.DESC, AGE);
        Sort sort = force.and(age);
        return jediRepository.findAll(sort);
    }

    public List<Jedi> findByForceBetween(Long begin, Long end) {
        return jediRepository.findByForceBetween(begin, end);
    }

    public List<Jedi> findByRankOrderByAge(Rank rank) {
        return jediRepository.findByRankOrderByAge(rank);
    }

    // NOTE: equipments is []
    public List<Jedi> findByForceGreaterThanAndRankIs(long force, Rank rank) {
        Criteria forceCriteria = Criteria.where(FORCE).greaterThan(force);
        Criteria rankCriteria = Criteria.where(RANK).is(rank);
        CriteriaQuery query = new CriteriaQuery(forceCriteria.and(rankCriteria));
        return elasticsearchTemplate.queryForList(query, Jedi.class);
    }

    public long countByForceLessThan(long force) {
        Criteria forceCriteria = Criteria.where(FORCE).lessThan(force);
        CriteriaQuery query = new CriteriaQuery(forceCriteria);
        return elasticsearchTemplate.count(query, Jedi.class);
    }
}
