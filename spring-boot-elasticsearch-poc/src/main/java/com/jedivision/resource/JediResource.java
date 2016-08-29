package com.jedivision.resource;

import com.jedivision.document.Jedi;
import com.jedivision.document.Rank;
import com.jedivision.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/elastic/jedi")
public class JediResource {

    private final JediService jediService;

    @Autowired
    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public List<String> index() {
        jediService.index();
        jediService.saveJedis();
        return Collections.singletonList("OK");
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Jedi> findAll() {
        return jediService.findAll();
    }

    @RequestMapping(value = "/findAll/sorted", method = RequestMethod.GET)
    public Iterable<Jedi> findAllSortedByAge() {
        return jediService.findAllSortedByForceByAge();
    }

    @RequestMapping(value = "/findByForceBetween/{begin}/{end}", method = RequestMethod.GET)
    public List<Jedi> findByForceBetween(@PathVariable("begin") Long begin,
                                         @PathVariable("end") Long end) {
        return jediService.findByForceBetween(begin, end);
    }

    @RequestMapping(value = "/findByRankOrderByAge/{rank}", method = RequestMethod.GET)
    public List<Jedi> findByRankOrderByAge(@PathVariable("rank") Rank rank) {
        return jediService.findByRankOrderByAge(rank);
    }

    @RequestMapping(value = "/findByForceGreaterThanAndRankIs/{force}/{rank}", method = RequestMethod.GET)
    public List<Jedi> findByForceGreaterThanAndRankIs(@PathVariable("force") Long force,
                                                      @PathVariable("rank") Rank rank) {
        return jediService.findByForceGreaterThanAndRankIs(force, rank);
    }

    @RequestMapping(value = "/countByForceLessThan/{force}", method = RequestMethod.GET)
    public List<Long> countByForceLessThan(@PathVariable("force") Long force) {
        long quantity = jediService.countByForceLessThan(force);
        return Collections.singletonList(quantity);
    }
}
