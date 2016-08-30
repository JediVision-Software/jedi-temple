package com.jedivision.resource;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.JediService;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/jedi")
@Produces(MediaType.APPLICATION_JSON)
public class JediResource {
    private final JediService jediService;

    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @GET
    @UnitOfWork
    public List<Jedi> findAll() {
        return jediService.findAllJedi();
    }

    @GET
    @Path("/{jediId}")
    @UnitOfWork
    public Jedi findOne(@PathParam("jediId") IntParam jediId) throws ApplicationException {
        return jediService.findJediById(jediId.get());
    }

    @POST
    @UnitOfWork
    public Jedi create(Jedi jedi) {
        return jediService.createJedi(jedi);
    }

    @PUT
    @Path("/{jediId}")
    @UnitOfWork
    public Jedi update(@PathParam("jediId") IntParam jediId, Jedi jedi) {
        return jediService.updateJedi(jediId.get(), jedi);
    }

    @DELETE
    @Path("/{jediId}")
    @UnitOfWork
    public void delete(@PathParam("jediId") IntParam jediId) {
        jediService.deleteJedi(jediId.get());
    }

    @POST
    @Path("/{jediId}/equipment/{equipmentId}")
    @UnitOfWork
    public Equipment addEquipment(@PathParam("jediId") IntParam jediId, @PathParam("equipmentId") IntParam equipmentId) {
        return jediService.addEquipmentToJedi(jediId.get(), equipmentId.get());
    }

    @DELETE
    @Path("/{jediId}/equipment")
    @UnitOfWork
    public void removeEquipments(@PathParam("jediId") IntParam jediId) {
        jediService.removeEquipmentsFromJedi(jediId.get());
    }
}
