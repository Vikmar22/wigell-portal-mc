package com.wigell.mc.Service;

import com.wigell.mc.DAO.AdminDAO;
import com.wigell.mc.DAO.BikeDAO;
import com.wigell.mc.DAO.BookingDAO;
import com.wigell.mc.DAO.CustomerDAO;
import com.wigell.mc.DTO.BookingRequest;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;
    private final CustomerDAO customerDAO;
    private final BikeDAO bikeDAO;
    private final AdminDAO adminDAO;

    public BookingService(BookingDAO bookingDAO, CustomerDAO customerDAO, BikeDAO bikeDAO, AdminDAO adminDAO) {
        this.bookingDAO = bookingDAO;
        this.customerDAO = customerDAO;
        this.bikeDAO = bikeDAO;
        this.adminDAO = adminDAO;
    }

    @Transactional
    public List<Bookings> getBookings() {return bookingDAO.getBookings();}

    @Transactional
    public List<Bookings> getBookingById(int id) { return bookingDAO.getBookingsById(id); }

    public List<Bookings> getBookingsByCustomerId(Long customerId) {
        return bookingDAO.getBookingsByCustomerId(customerId);
    }




    @Transactional
    public Bookings updateBookings(Long id, Bookings bookings) {
        Bookings updatedBookings = bookingDAO.updateBookings(id, bookings);
        return updatedBookings;
    }

    @Transactional
    public boolean deleteBooking(Long id) {return bookingDAO.deleteBooking(id);}

    @Transactional
    public Bookings updateBookingStatusPatch(Long bookingId, Map<String, Object> updates) {
        return bookingDAO.updateBookingStatusPatch(bookingId, updates);
    }
}


