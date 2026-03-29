package com.roomrental.roomrental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.roomrental.roomrental.model.Room;
import com.roomrental.roomrental.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        try {
            Room _room = roomRepository.save(new Room(
                    room.getName(),
                    room.getCapacity(),
                    room.getPricePerHour(),
                    false,
                    room.getReunionType()
            ));
            return new ResponseEntity<>(_room, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms(@RequestParam(required = false) String name) {
        try {
            List<Room> rooms = new ArrayList<Room>();

            if (name == null)
                roomRepository.findAll().forEach(rooms::add);
            else
                roomRepository.findByNameContaining(name).forEach(rooms::add);

            if (rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") String id) {
        Optional<Room> roomData = roomRepository.findById(id);

        if (roomData.isPresent()) {
            return new ResponseEntity<>(roomData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") String id, @RequestBody Room room) {
        Optional<Room> roomData = roomRepository.findById(id);

        if (roomData.isPresent()) {
            Room _room = roomData.get();
            _room.setName(room.getName());
            _room.setCapacity(room.getCapacity());
            _room.setPricePerHour(room.getPricePerHour());
            _room.setReserved(room.isReserved());
            _room.setTipoFiesta(room.getReunionType());
            return new ResponseEntity<>(roomRepository.save(_room), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("id") String id) {
        try {
            roomRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rooms")
    public ResponseEntity<HttpStatus> deleteAllRooms() {
        try {
            roomRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}