package com.hotel.reservation.controller;

import com.hotel.reservation.model.Reservation;
import com.hotel.reservation.model.User;
import com.hotel.reservation.repository.ReservationRepository;
import com.hotel.reservation.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationController(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/my")
    public List<Reservation> getMyReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return reservationRepository.findByUserId(user.getId());
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        System.out.println("Received reservation request: " + reservation);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Auth: " + auth.getName());
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        reservation.setUserId(user.getId());
        reservation.setStatus("CONFIRMED");
        Reservation saved = reservationRepository.save(reservation);
        System.out.println("Saved reservation: " + saved);
        return saved;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        // In a real app, check for ADMIN role here or in SecurityConfig
        return reservationRepository.findAll();
    }
}
