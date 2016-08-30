package com.jedivision.resource;

import com.jedivision.entity.Equipment;
import com.jedivision.exception.ApplicationException;
import com.jedivision.service.EquipmentService;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/equipment")
@Produces(MediaType.APPLICATION_JSON)
public class EquipmentResource {
    private final EquipmentService equipmentService;

    public EquipmentResource(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GET
    @UnitOfWork
    public List<Equipment> findAll() {
        return equipmentService.findAllEquipments();
    }

    @GET
    @Path("/{equipmentId}")
    @UnitOfWork
    public Equipment findOne(@PathParam("equipmentId") IntParam equipmentId) throws ApplicationException {
        return equipmentService.findEquipmentById(equipmentId.get());
    }

    @POST
    @UnitOfWork
    public Equipment create(Equipment equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @PUT
    @Path("/{equipmentId}")
    @UnitOfWork
    public Equipment update(@PathParam("equipmentId") IntParam equipmentId, Equipment equipment) {
        return equipmentService.updateEquipment(equipmentId.get(), equipment);
    }

    @DELETE
    @Path("/{equipmentId}")
    @UnitOfWork
    public void delete(@PathParam("equipmentId") IntParam equipmentId) throws ApplicationException {
        equipmentService.deleteEquipment(equipmentId.get());
    }
}
