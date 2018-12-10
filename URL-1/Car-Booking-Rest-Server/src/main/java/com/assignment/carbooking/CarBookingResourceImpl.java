package com.assignment.carbooking;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.assignment.carbooking.server.CarBookingRMIClient;

import carbooking.assignment.com.model.Booking;
import carbooking.assignment.com.model.Bookingmessage;


@Path("booking")
public class CarBookingResourceImpl implements CarBookingResource {
    @Context
    private ServletContext servletContext;

    @Override
    public Response getBooking(String id) {
        Booking c = CarBookingRMIClient.getInstance(servletContext).getBooking(id);
        if (c != null) {
            return Response.status(200).entity(c).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response addBooking(Bookingmessage bs) {
        if (bs.getBooking() == null ||
                bs.getBooking().getCar() == null ||
                bs.getBooking().getBookingTimeFrame() == null ||
                bs.getBooking().getPerson() == null) {
            return Response.status(400).build();
        }
        Booking c = bs.getBooking();
        //Check if the car is available
        if (CarBookingRMIClient.getInstance(servletContext).isCarAvailable(
                c.getCar().getId(),
                c.getBookingTimeFrame(),
                c.getId())
        ) {
            c.setId(UUID.randomUUID().toString());
            c.getPerson().setId(UUID.randomUUID().toString());
            if (CarBookingRMIClient.getInstance(servletContext).addBooking(c)) {
                bs.setBooking(c);
                bs.setMessage("ok");
                return Response.status(200).entity(bs).build();
            }
        } else {
            bs.setBooking(c);
            bs.setMessage("carnotavailable");
            return Response.status(200).entity(bs).build();
        }
        bs.setBooking(null);
        bs.setMessage("couldnotaddduetounknownerror");
        return Response.status(200).entity(bs).build();
    }

    @Override
    public Response changeBooking(Bookingmessage bs) {
        if (bs.getBooking() == null ||
                bs.getBooking().getCar() == null ||
                bs.getBooking().getBookingTimeFrame() == null ||
                bs.getBooking().getPerson() == null) {
            return Response.status(400).build();
        }
        Booking c = bs.getBooking();
        //Check if the car is available
        if (CarBookingRMIClient.getInstance(servletContext).isCarAvailable(
                c.getCar().getId(),
                c.getBookingTimeFrame(),
                c.getId())
        ) {
            if (CarBookingRMIClient.getInstance(servletContext).changeBooking(c)) {
                bs.setBooking(c);
                bs.setMessage("ok");
                return Response.status(200).entity(bs).build();
            }
        } else {
            bs.setBooking(c);
            bs.setMessage("carnotavailable");
            return Response.status(200).entity(bs).build();
        }
        return Response.status(409).entity(bs).build();
    }

    @Override
    public Response deleteBooking(String id) {
        if (CarBookingRMIClient.getInstance(servletContext).deleteBooking(id)) {
            return Response.status(200).build();
        }
        return Response.status(204).build();
    }

}
