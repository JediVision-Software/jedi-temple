package com.jedivision.resource;

import com.jedivision.entity.Jedi;
import com.jedivision.entity.Rank;
import com.jedivision.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jedi")
public class JediResource {
    // Service
    private final JediService jediService;

    @Autowired
    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @RequestMapping(value = "/{jediId}", method = RequestMethod.GET)
    public Jedi findById(@PathVariable("jediId") Integer jediId) {
        return jediService.findOne(jediId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Jedi> findAll() {
        return jediService.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Jedi> findByRank(@RequestParam("rank") String rank) {
        return jediService.findByRank(Rank.valueOf(rank.toUpperCase()));
    }
}
