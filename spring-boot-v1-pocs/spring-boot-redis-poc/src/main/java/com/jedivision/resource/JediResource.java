package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jedi")
public class JediResource {

    private final JediService jediService;

    @Autowired
    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @RequestMapping(value = "/put/{key}", method = RequestMethod.GET)
    public List<String> put(@PathVariable("key") String key) {
        Jedi jedi = Jedi.builder()
                .fullName("Yoda")
                .age(35)
                .force(500)
                .build();
        return jediService.put(key, jedi);
    }

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public Jedi get(@PathVariable("key") String key) {
        return jediService.get(key);
    }
}
