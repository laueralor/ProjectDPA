package com.roomrental.roomrental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {
    public enum ReunionType {
        CUMPLEANOS, BODA, CONCIERTO, EMPRESA
    }
    @Id
    private String id;
    private String name;
    private int capacity;
    private double pricePerHour;
    private boolean isReserved;
    private ReunionType reunionType;

    public Room() {}

    public Room(String name, int capacity, double pricePerHour, boolean isReserved, ReunionType reunionType) {
        this.name = name;
        this.capacity = capacity;
        this.pricePerHour = pricePerHour;
        this.isReserved = isReserved;
        this.reunionType = reunionType;
    }

    // Getters y Setters
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

    public boolean isReserved() { return isReserved; }
    public void setReserved(boolean isReserved) { this.isReserved = isReserved; }

    public ReunionType getReunionType() { return reunionType; }
    public void setTipoFiesta(ReunionType reunionType) { this.reunionType = reunionType; }
}