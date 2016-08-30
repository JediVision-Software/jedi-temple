package com.jedivision.resource;

import com.jedivision.service.JediService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/index")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {
    private final JediService jediService;

    public IndexResource(JediService jediService) {
        this.jediService = jediService;
    }

    @GET
    @UnitOfWork
    public List<String> index() {
        jediService.indexJedi();
        return Collections.singletonList("OK");
    }
}
