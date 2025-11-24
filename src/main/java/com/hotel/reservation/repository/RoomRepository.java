package com.hotel.reservation.repository;

import com.hotel.reservation.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
