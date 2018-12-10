package com.assignment.carbooking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CarBookingServiceInterface extends Remote
{
	// Declare booking methods
	public List<CarBooking> getBookings() throws RemoteException;
	
	public CarBooking getBooking(int orderID) throws RemoteException;
	
	public void createBooking(CarBooking booking) throws RemoteException;

}
