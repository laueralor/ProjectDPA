package com.roomrental.roomrental.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class RoomServerMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            RoomService service = new RoomService();
            
            Naming.rebind("//localhost/RoomService", service);
            
            System.out.println(">>> Server RMI of Room Reserve ready.");
            System.out.println(">>> Registrado como: '//localhost/RoomService'");
            
        } catch (Exception e) {
            System.err.println("Error starting the server RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}