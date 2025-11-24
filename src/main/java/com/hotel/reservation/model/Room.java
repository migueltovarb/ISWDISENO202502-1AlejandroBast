package com.hotel.reservation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String number;
    private String type; // e.g., "Single", "Double", "Suite"
    private double price;
    private String description;
    private String imageUrl;
    private boolean available = true;
}
