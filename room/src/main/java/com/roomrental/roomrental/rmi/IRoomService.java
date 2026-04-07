package com.roomrental.roomrental.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeMap;


public interface IRoomService extends Remote {
    
    TreeMap<Integer, Boolean> getReservationStatus() throws RemoteException;
    
    boolean reserveRoom(int roomId) throws RemoteException;
}