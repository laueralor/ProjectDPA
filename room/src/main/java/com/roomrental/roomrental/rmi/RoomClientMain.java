package com.roomrental.roomrental.rmi;

import java.rmi.Naming;
import java.util.TreeMap;


public class RoomClientMain {
    public static void main(String[] args) {
        try {
            IRoomService roomService = (IRoomService) Naming.lookup("//localhost/RoomService");

            System.out.println("--- Connected to the RMI Room Server ---");

            TreeMap<Integer, Boolean> status = roomService.getReservationStatus();
            System.out.println("Initial State of the rooms: " + status);

            int[] roomsToBook = {5, 7};
            for (int roomId : roomsToBook) {
                boolean success = roomService.reserveRoom(roomId);
                String result = success ? "GREAT" : "FAIL (occupated)";
                System.out.println("Room Reservation Request " + roomId + ": " + result);
            }

            TreeMap<Integer, Boolean> finalStatus = roomService.getReservationStatus();
            System.out.println("Final State of the rooms: " + finalStatus);

        } catch (Exception e) {
            System.err.println("RMI client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}