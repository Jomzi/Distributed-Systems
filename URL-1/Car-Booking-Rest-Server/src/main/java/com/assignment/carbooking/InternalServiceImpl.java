package com.assignment.carbooking;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.assignment.carbooking.server.CarBookingRMIClient;

import carbooking.assignment.com.model.Booking;
import carbooking.assignment.com.model.Car;


@Path("admin")
public class InternalServiceImpl implements InternalService {
    @Context
    private ServletContext servletContext;

    @Override
    public Response getCar(Car c) {
        if (c.getId() == null || c.getModel() == null || c.getMake() == null || c.getColor() == null) {
            return Response.status(400).build();
        }
        if (CarBookingRMIClient.getInstance(servletContext).addCar(c)) {
            return Response.status(200).entity(c).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response getBookings() {
        List<Booking> list = CarBookingRMIClient.getInstance(servletContext).getBookings();

        if (list.size() != 0) {
            return Response.status(200).entity(new GenericEntity<List<Booking>>(list) {
            }).build();
        }
        return Response.status(204).build();
    }
}
