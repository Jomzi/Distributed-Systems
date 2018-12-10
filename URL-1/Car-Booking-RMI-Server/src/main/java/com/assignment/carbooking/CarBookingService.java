package com.assignment.carbooking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import carbooking.assignment.com.model.Booking;
import carbooking.assignment.com.model.BookingTimeFrame;
import carbooking.assignment.com.model.Car;

public interface CarBookingService extends Remote {
    /**
     * Return a booking bi its id
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public Booking getBooking(String id) throws RemoteException;

    /**
     * Adds a booking to the database
     *
     * @param b
     * @return
     * @throws RemoteException
     */
    public boolean addBooking(Booking b) throws RemoteException;

    /**
     * Changes a booking in the database
     *
     * @param b
     * @return
     * @throws RemoteException
     */
    public boolean changeBooking(Booking b) throws RemoteException;

    /**
     * Returns a list of bookings
     *
     * @return
     * @throws RemoteException
     */
    public ArrayList<Booking> getBookings() throws RemoteException;

    /**
     * Deletes a booking in the database
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public boolean deleteBooking(String id) throws RemoteException;

    /**
     * Adds a car to the database
     *
     * @param c
     * @return
     * @throws RemoteException
     */
    public boolean addCar(Car c) throws RemoteException;

    /**
     * Returns a car by its id
     *
     * @param id
     * @return
     * @throws RemoteException
     */
    public Car getCar(String id) throws RemoteException;

    /**
     * Returns a list of cars
     *
     * @return
     * @throws RemoteException
     */
    public ArrayList<Car> getCars() throws RemoteException;

    /**
     * Checks whether the car is available for a given period or not.
     * If the bookingId empty, it will search every record
     * If the bookingId is empty, it exclude that it from search (a.k.a update)
     *
     * @param carId
     * @param timeFrame
     * @param bookingId
     * @return
     * @throws RemoteException
     */
    public boolean isCarAvailable(String carId, BookingTimeFrame timeFrame, String bookingId) throws RemoteException;
}
