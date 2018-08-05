package com.jedivision.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.singletonList;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api/index")
public class IndexResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<String> index() {
        return singletonList("Cross Origin: Server Value");
    }
}
