package com.jedivision.dao;

import com.jedivision.entity.Jedi;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class JediDao extends AbstractDAO<Jedi> {

    public JediDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Jedi> findAll() {
        return list(namedQuery("Jedi.findAll"));
    }

    public Jedi findById(Integer id) {
        Optional<Jedi> optionalJedi = Optional.ofNullable(get(id));
        if (optionalJedi.isPresent()) {
            return optionalJedi.get();
        } else {
            return null;
        }
    }

    public Jedi create(Jedi jedi) {
        return persist(jedi);
    }

    public void delete(Jedi jedi) {
        currentSession().delete(jedi);
    }

}
