package com.jedivision.service;

import com.jedivision.document.Jedi;
import com.jedivision.document.Rank;
import com.jedivision.repository.JediRepository;
import com.jedivision.test.runner.ApplicationServiceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

import static com.jedivision.test.RandomUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class JediServiceTest extends ApplicationServiceRunner {

    @Autowired
    private JediRepository jediRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private JediService serviceUnderTest;

    @Before
    public void before() throws Exception {
        reset(jediRepository, elasticsearchTemplate);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(jediRepository, elasticsearchTemplate);
    }

    @Test
    public void index() {
        // Act
        serviceUnderTest.index();

        // Assert
        verify(elasticsearchTemplate).deleteIndex(Jedi.class);
        verify(elasticsearchTemplate).createIndex(Jedi.class);
        verify(elasticsearchTemplate).putMapping(Jedi.class);
        verify(elasticsearchTemplate).refresh(Jedi.class);
    }

    @Test
    public void saveJedis() {
        // Act
        serviceUnderTest.saveJedis();

        // Assert
        ArgumentCaptor<Iterable> jedi = ArgumentCaptor.forClass(Iterable.class);
        verify(jediRepository).save(jedi.capture());
    }

    @Test
    public void findAll() {
        // Act
        serviceUnderTest.findAll();

        // Assert
        verify(jediRepository).findAll();
    }

    @Test
    public void findAllSortedByForceByAge() {
        // Act
        serviceUnderTest.findAllSortedByForceByAge();

        // Assert
        ArgumentCaptor<Sort> sort = ArgumentCaptor.forClass(Sort.class);
        verify(jediRepository).findAll(sort.capture());
        Sort force = new Sort(Sort.Direction.DESC, "force");
        Sort age = new Sort(Sort.Direction.DESC, "age");
        assertEquals(force.and(age), sort.getValue());
    }

    @Test
    public void findByForceBetween() {
        // Arrange
        Long begin = randomLong();
        Long end = randomLong();

        // Act
        serviceUnderTest.findByForceBetween(begin, end);

        // Assert
        verify(jediRepository).findByForceBetween(eq(begin), eq(end));
    }

    @Test
    public void findByRankOrderByAge() {
        // Arrange
        Rank rank = randomEnum(Rank.class);

        // Act
        serviceUnderTest.findByRankOrderByAge(rank);

        // Assert
        verify(jediRepository).findByRankOrderByAge(eq(rank));
    }

    @Test
    public void findByForceGreaterThanAndRankIs() {
        // Arrange
        Long force = randomLong();
        Rank rank = randomEnum(Rank.class);

        // Act
        serviceUnderTest.findByForceGreaterThanAndRankIs(force, rank);

        // Assert
        ArgumentCaptor<CriteriaQuery> criteriaQuery = ArgumentCaptor.forClass(CriteriaQuery.class);
        verify(elasticsearchTemplate).queryForList(criteriaQuery.capture(), eq(Jedi.class));
        Criteria criteria = criteriaQuery.getValue().getCriteria();
        List<Criteria> criteriaChain = criteria.getCriteriaChain();
        assertTrue(" AND ".equals(criteria.getConjunctionOperator()));
        Criteria forceCriteria = criteriaChain.get(0);
        Criteria.CriteriaEntry forceCE = forceCriteria.getQueryCriteriaEntries().iterator().next();
        assertTrue("force".equals(forceCriteria.getField().getName()));
        assertTrue(Criteria.OperationKey.GREATER.equals(forceCE.getKey()));
        assertTrue(force.equals(forceCE.getValue()));
        Criteria rankCriteria = criteriaChain.get(1);
        Criteria.CriteriaEntry rankCE = rankCriteria.getQueryCriteriaEntries().iterator().next();
        assertTrue("rank".equals(rankCriteria.getField().getName()));
        assertTrue(Criteria.OperationKey.EQUALS.equals(rankCE.getKey()));
        assertTrue(rank.equals(rankCE.getValue()));
    }

    @Test
    public void findNear() {
        // Arrange
        Double latitude = randomDouble();
        Double longitude = randomDouble();
        String distance = randomString();

        // Act
        serviceUnderTest.findNear(latitude, longitude, distance);

        // Assert
        ArgumentCaptor<CriteriaQuery> criteriaQuery = ArgumentCaptor.forClass(CriteriaQuery.class);
        verify(elasticsearchTemplate).queryForList(criteriaQuery.capture(), eq(Jedi.class));
        Criteria criteria = criteriaQuery.getValue().getCriteria();
        List<Criteria> criteriaChain = criteria.getCriteriaChain();
        Criteria locationCriteria = criteriaChain.get(0);
        Criteria.CriteriaEntry locationCE = locationCriteria.getFilterCriteria().iterator().next();
        assertTrue("location".equals(locationCriteria.getField().getName()));
        assertTrue(Criteria.OperationKey.WITHIN.equals(locationCE.getKey()));
        Object[] queryAttributes = (Object[]) locationCE.getValue();
        GeoPoint geopoint = (GeoPoint) queryAttributes[0];
        assertTrue(latitude.equals(geopoint.getLat()));
        assertTrue(longitude.equals(geopoint.getLon()));
        assertTrue(distance.equals(queryAttributes[1]));
    }

    @Test
    public void countByForceLessThan() {
        // Arrange
        Long force = randomLong();

        // Act
        serviceUnderTest.countByForceLessThan(force);

        // Assert
        ArgumentCaptor<CriteriaQuery> criteriaQuery = ArgumentCaptor.forClass(CriteriaQuery.class);
        verify(elasticsearchTemplate).count(criteriaQuery.capture(), eq(Jedi.class));
        Criteria criteria = criteriaQuery.getValue().getCriteria();
        List<Criteria> criteriaChain = criteria.getCriteriaChain();
        Criteria forceCriteria = criteriaChain.get(0);
        Criteria.CriteriaEntry forceCE = forceCriteria.getQueryCriteriaEntries().iterator().next();
        assertTrue("force".equals(forceCriteria.getField().getName()));
        assertTrue(Criteria.OperationKey.LESS.equals(forceCE.getKey()));
        assertTrue(force.equals(forceCE.getValue()));
    }
}
