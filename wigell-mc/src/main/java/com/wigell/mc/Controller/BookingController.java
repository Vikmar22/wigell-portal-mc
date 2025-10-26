package com.wigell.mc.Controller;


import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1")
public class BookingController {
    private final BookingService bookingService;
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


   @GetMapping("/bookings")
   public List<Bookings> getBookings() {
        log.info("Retrieved all bookings");
        return bookingService.getBookings();
    }

    @GetMapping("/bookings/{id}")
    public List<Bookings> getBookings(@PathVariable("id") int id) {
        bookingService.getBookingById(id);
        log.info("Retrieved booking {}", id);
        return bookingService.getBookingById(id);
    }



    @PutMapping("/bookings/{id}")
    public ResponseEntity<Bookings> updateBookings(@PathVariable("id") Long id, @RequestBody Bookings bookings) {
        log.info("Received PUT /bookings/{} with body: {}", id, bookings);
        Bookings updated = bookingService.updateBookings(id, bookings);
        log.info("Updated booking {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Bike> deleteBooking(@PathVariable("id") Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        log.info("Deleted booking {}", deleted);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/bookings/{bookingId}")
    public ResponseEntity<Bookings> updateBookingStatus(
            @PathVariable("bookingId") Long bookingId,
            @RequestBody Map<String, Object> updates) {

        Bookings updated = bookingService.updateBookingStatusPatch(bookingId, updates);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        log.info("Updated booking {}", updated);
        return ResponseEntity.ok(updated);
    }
}
