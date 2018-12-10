package com.assignment.carbooking;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// Booking Service
// This class serves as the gateway for our booking client to 
public class CarBookingService
{
	public static void main(String[] args) throws Exception
	{
		// Create instance of bookingService implementation
		CarBookingServiceImpl bookingServiceImpl = new CarBookingServiceImpl();
		
		// Export the implementation
		System.setProperty("java.rmi.server.hostname","192.168.137.1");


		CarBookingServiceInterface stub = (CarBookingServiceInterface) UnicastRemoteObject.exportObject(bookingServiceImpl, 0);
		
		// Start the RMI Registry 
		LocateRegistry.createRegistry(1099);
		
		// Bind the bookingService object to the registry 
		try
		{
			Naming.rebind("databaseService", stub);
			System.out.println("Server started...");
		}
		catch(Exception e)
		{
			System.out.println("Server error: " + e);
		}
	}
	
	
	
}
