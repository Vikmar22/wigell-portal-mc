package com.wigell.mc.DAO;

import com.wigell.mc.Controller.BookingController;
import com.wigell.mc.DTO.BookingRequest;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class BookingDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(BookingDAO.class);

    public List<Bookings> getBookings() {
        return em.createQuery("select b from Bookings b", Bookings.class)
                .getResultList();

    }

    public List<Bookings> getBookingsById(int id) {
        return em.createQuery("select b from Bookings b where b.id = :id", Bookings.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Bookings> getBookingsByCustomerId(Long customerId) {
        return em.createQuery("SELECT b FROM Bookings b WHERE b.customer.id = :customerId", Bookings.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }


    public Bookings updateBookings(Long id, Bookings bookingBody) {

        Bookings existingBooking = em.find(Bookings.class, id);


        if (existingBooking == null) {
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }


        existingBooking.setTime(bookingBody.getTime());
        existingBooking.setPriceSEK(bookingBody.getPriceSEK());
        existingBooking.setPriceGB(bookingBody.getPriceGB());
        existingBooking.setStartDate(bookingBody.getStartDate());
        existingBooking.setEndDate(bookingBody.getEndDate());


        if (bookingBody.getBike() != null) {
            existingBooking.setBike(bookingBody.getBike());
        }



        return existingBooking;
    }

    public boolean deleteBooking(Long id) {
        Bookings existingBooking = em.find(Bookings.class, id);
        em.remove(existingBooking);
        return true;
    }

    public Bookings updateBookingStatusPatch(Long bookingId, Map<String, Object> updates) {
        Bookings booking = em.find(Bookings.class, bookingId);
        if (booking == null) {
            return null;
        }

        if (updates.containsKey("status")) {
            booking.setStatus((String) updates.get("status"));
        }

        em.merge(booking);
        return booking;
    }
}
