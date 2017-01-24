package com.jedivision.resource;

import com.datastax.driver.core.utils.UUIDs;
import com.jedivision.entity.Jedi;
import com.jedivision.property.ApplicationProperty;
import com.jedivision.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/jedi")
public class JediResource {
    private final JediRepository jediRepository;
    private final ApplicationProperty properties;

    @Autowired
    public JediResource(JediRepository jediRepository,
                        ApplicationProperty properties) {
        this.jediRepository = jediRepository;
        this.properties = properties;
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public Iterable<Jedi> findAll() {
        return jediRepository.findAll();
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public Jedi index() {
        Jedi yoda = Jedi.builder()
                .id(UUIDs.timeBased())
                .name(properties.getProfile().getJedi())
                .planet(properties.getProfile().getNode())
                .creationDate(new Date())
                .build();
        return jediRepository.save(yoda);
    }
}