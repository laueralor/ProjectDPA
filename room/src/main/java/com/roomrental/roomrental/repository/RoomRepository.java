package com.roomrental.roomrental.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.roomrental.roomrental.model.Room;
import com.roomrental.roomrental.model.Room.ReunionType;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByTipoFiesta(ReunionType reunionType);

    List<Room> findByNameContaining(String name);

    List<Room> findByIsReserved(boolean isReserved);
}