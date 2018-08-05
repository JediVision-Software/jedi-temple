package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.repository.JediMongoRepository;
import com.jedivision.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JediResource {

    @Autowired
    private JediRepository jediRepository;

    @Autowired
    private JediMongoRepository jediMongoRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public List<String> index() {
        Jedi jedi1 = Jedi.builder().fullName("Yoda").age(20).build();
        Jedi jedi2 = Jedi.builder().fullName("Vader").age(30).build();
        Jedi jedi3 = Jedi.builder().fullName("Luke").age(45).build();
        Jedi jedi4 = Jedi.builder().fullName("Rey").age(28).build();
        jediRepository.save(Arrays.asList(jedi1, jedi2, jedi3, jedi4));
        return Collections.singletonList("YES");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete() {
        jediRepository.deleteAll();
    }

    @RequestMapping(value = "/upsert/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void upsert(@PathVariable String id, @RequestParam String newFullName) {
        jediMongoRepository.upsertFullName(id, newFullName);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Jedi> findAll() {
        return jediRepository.findAll();
    }
}
