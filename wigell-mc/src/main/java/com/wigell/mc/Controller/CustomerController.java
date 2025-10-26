package com.wigell.mc.Controller;

import com.wigell.mc.DTO.BookingRequest;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/v1/")
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<Bookings> createBooking(@RequestBody BookingRequest request) {
        Bookings createdBooking = customerService.createBookings(request);
        log.info("Created booking {}", createdBooking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Bike>> getBikeByAvailability(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<Bike> available = customerService.getBikeByAvailability(from, to);
        log.info("Available {}", available);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Bookings>> getBookingsByCustomer(
            @RequestParam("customerId") Long customerId) {

        List<Bookings> bookings = customerService.getBookingsByCustomer(customerId);
        log.info("Fetching bookings for customerId={}", customerId);
        return ResponseEntity.ok(bookings);
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<Bookings> updateBookingPatch(
            @PathVariable(name = "bookingId") Long bookingId,
            @RequestBody Map<String, Object> updates) {

        Bookings updated = customerService.updateBookingPatch(bookingId, updates);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


}
