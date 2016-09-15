package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JediResource {

    @Autowired
    private JediRepository jediRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public List<String> index() {
        Jedi jedi1 = Jedi.builder().fullName("Yoda").build();
        Jedi jedi2 = Jedi.builder().fullName("Vader").build();
        jediRepository.save(Arrays.asList(jedi1, jedi2));
        return Collections.singletonList("YES");
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Jedi> findAll() {
        return jediRepository.findAll();
    }
}
