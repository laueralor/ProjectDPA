package com.roomrental.roomrental.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.TreeMap;


public class RoomService extends UnicastRemoteObject implements IRoomService {
    private TreeMap<Integer, Boolean> reservations;

    protected RoomService() throws RemoteException {
        super();
        this.reservations = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            reservations.put(i, false);
        }
    }

    @Override
    public synchronized TreeMap<Integer, Boolean> getReservationStatus() throws RemoteException {
        return new TreeMap<>(reservations);
    }

    @Override
    public synchronized boolean reserveRoom(int roomId) throws RemoteException {
        if (reservations.containsKey(roomId) && !reservations.get(roomId)) {
            reservations.put(roomId, true);
            System.out.println("Room " + roomId + " reserved great line RMI.");
            return true;
        }
        System.out.println("Failed try to reserve the room " + roomId);
        return false;
    }
}