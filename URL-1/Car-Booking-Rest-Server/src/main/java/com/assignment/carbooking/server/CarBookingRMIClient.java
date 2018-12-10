package com.assignment.carbooking.server;

import java.rmi.Naming;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import carbooking.assignment.com.model.Booking;
import carbooking.assignment.com.model.BookingTimeFrame;
import carbooking.assignment.com.model.Car;

/**
 * RMI connector singleton
 */
public class CarBookingRMIClient implements CarBookingService {
    //Encapsulated RMIClient instance
    private static final CarBookingRMIClient rmic = new CarBookingRMIClient();
    private String host = "127.0.0.1";
    private String port = "1099";
    private String serviceName = "BookingRMIService";

    /**
     * Private constructor to block instantiation
     */
    private CarBookingRMIClient() {
    }

    /**
     * Returns the RMIClient
     *
     * @return
     */
    public static CarBookingRMIClient getInstance(ServletContext props) {
        //Set the rmi connection details from context
        rmic.setHost(props.getInitParameter("rmiHost"));
        rmic.setPort(props.getInitParameter("rmiPort"));
        rmic.setServiceName(props.getInitParameter("rmiServiceName"));
        /*props.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
            System.out.println("-------");
        });*/
        return rmic;
    }

    /**
     * Connects to the remote RMI service
     *
     * @return
     * @throws Exception
     */
    private CarBookingService connect() throws Exception {
        return (CarBookingService) Naming.lookup("rmi://" + host + ":" + port + "/" + serviceName);
    }

    /**
     * Invokes a method without parameters
     *
     * @param method
     * @return
     */
    private Object invokeNull(String method) {
        try {
            return CarBookingService.class.getDeclaredMethod(method).invoke(connect());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Invokes a method with one parameter
     *
     * @param method
     * @param a
     * @return
     */
    private Object invokeOne(String method, Object a) {
        try {
            return CarBookingService.class.getDeclaredMethod(method, a.getClass()).invoke(connect(), a);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Invokes a method with two parameters
     *
     * @param method
     * @param a
     * @param b
     * @return
     */
    private Object invokeThree(String method, Object a, Object b, Object c) {
        try {
            return CarBookingService.class.getDeclaredMethod(method, a.getClass(), b.getClass(), c.getClass()).invoke(connect(), a, b, c);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    @Override
    public Booking getBooking(String id) {
        return (Booking) invokeOne("getBooking", id);
    }

    @Override
    public boolean addBooking(Booking b) {
        return (Boolean) invokeOne("addBooking", b);
    }

    @Override
    public boolean changeBooking(Booking b) {
        return (Boolean) invokeOne("changeBooking", b);
    }

    @Override
    public ArrayList<Booking> getBookings() {
        ArrayList<Booking> a = (ArrayList<Booking>) invokeNull("getBookings");
        if (a == null)
            a = new ArrayList<>();

        return a;
    }

    @Override
    public boolean deleteBooking(String id) {
        return (Boolean) invokeOne("deleteBooking", id);
    }

    @Override
    public boolean addCar(Car c) {
        return (Boolean) invokeOne("addCar", c);
    }

    @Override
    public Car getCar(String id) {
        return (Car) invokeOne("getCar", id);
    }

    @Override
    public ArrayList<Car> getCars() {
        ArrayList<Car> a = (ArrayList<Car>) invokeNull("getCars");
        if (a == null)
            a = new ArrayList<>();

        return a;
    }

    @Override
    public boolean isCarAvailable(String carId, BookingTimeFrame timeFrame, String bookingId) {
        return (Boolean) invokeThree("isCarAvailable", carId, timeFrame, bookingId);
    }

    /**
     * Setter for host
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host != null ? host : this.host;
    }

    /**
     * Setter for port
     *
     * @param port
     */
    public void setPort(String port) {
        this.port = port != null ? port : this.port;
    }

    /**
     * setter for serviceName
     *
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName != null ? serviceName : this.serviceName;
    }
}
