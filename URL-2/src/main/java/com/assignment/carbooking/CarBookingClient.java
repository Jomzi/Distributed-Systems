package com.assignment.carbooking;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CarBookingClient
{

	public static void main(String[] args) throws NotBoundException
	{
		try 
		{
			// Gettting ther registryR	
			Registry registry = LocateRegistry.getRegistry(null);
			
			// Looking up registry for remote object
			CarBookingServiceInterface stub = (CarBookingServiceInterface) registry.lookup("databaseService");
			
			// Remote methods
			stub.getBooking(1);				
		}
		
		catch (RemoteException e) 
		{
			System.out.println("Client Exception: " + e);
		}
	}
	
}
