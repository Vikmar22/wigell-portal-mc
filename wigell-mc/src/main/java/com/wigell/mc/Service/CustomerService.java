package com.wigell.mc.Service;

import com.wigell.mc.DAO.BookingDAO;
import com.wigell.mc.DAO.CustomerDAO;
import com.wigell.mc.DTO.BookingRequest;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final  CustomerDAO customerDAO;
    private final BookingDAO bookingDAO;


    public CustomerService(CustomerDAO customerDAO, BookingDAO bookingDAO) {
        this.customerDAO = customerDAO;
        this.bookingDAO = bookingDAO;
    }

    @Transactional
    public Bookings createBookings(BookingRequest dto) {
        return customerDAO.createBookings(dto);
    }

    @Transactional
    public List<Bike> getBikeByAvailability(LocalDate from, LocalDate to){
        return customerDAO.getBikeByAvailability(from, to);
    }

    public List<Bookings> getBookingsByCustomer(Long customerId) {
        return customerDAO.getBookingsByCustomer(customerId);
    }

    @Transactional
    public Bookings updateBookingPatch(Long bookingId, Map<String, Object> updates) {
        return customerDAO.updateBookingPatch(bookingId, updates);
    }


}


