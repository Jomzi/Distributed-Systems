package com.assignment.carbooking;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.assignment.carbooking.server.CarBookingRMIClient;

import carbooking.assignment.com.model.BookingTimeFrame;
import carbooking.assignment.com.model.Car;
import carbooking.assignment.com.model.Cars;


@Path("car")
public class CarAvailabilityCheckServiceImpl implements CarAvailabilityCheckService {
    @Context
    private ServletContext servletContext;

    @Override
    public Response getCar(String id) {
        Car c = CarBookingRMIClient.getInstance(servletContext).getCar(id);
        if (c != null) {
            return Response.status(200).entity(c).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response isCarAvailable(String id, long from, long to, String bid) {
        BookingTimeFrame bt = new BookingTimeFrame();
        bt.setBookingTimeFrom(from);
        bt.setBookingTimeTo(to);

        if (CarBookingRMIClient.getInstance(servletContext).isCarAvailable(id, bt, bid)) {
            return Response.status(200).build();
        }
        return Response.status(204).build();
    }

    @Override
    public Response getCars() {
        List<Car> list = CarBookingRMIClient.getInstance(servletContext).getCars();
        Cars cars = new Cars();
        cars.setCars(list);
        if (list.size() != 0) {
            return Response.status(200).entity(cars).build();
        }
        return Response.status(204).build();
    }
}
