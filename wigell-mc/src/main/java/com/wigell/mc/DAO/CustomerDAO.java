package com.wigell.mc.DAO;

import com.wigell.mc.DTO.BookingRequest;
import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Entity.Bookings;
import com.wigell.mc.Entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDAO {

    @PersistenceContext
    private EntityManager em;

    private static final double SEK_TO_GBP = 0.075;



    public Bookings createBookings(BookingRequest dto) {

        Customer customer = em.find(Customer.class, dto.getCustomerId());
        Bike bike = em.find(Bike.class, dto.getBikeId());

        if (customer == null) {
            throw new RuntimeException("Customer not found with id " + dto.getCustomerId());
        }
        if (bike == null) {
            throw new RuntimeException("Bike not found with id " + dto.getBikeId());
        }

        int priceSEK = dto.getPriceSEK();
        int priceGB = (int) Math.round(priceSEK * SEK_TO_GBP);

        // Create and link entities
        Bookings booking = new Bookings();
        booking.setTime(dto.getTime());
        booking.setPriceSEK(priceSEK);
        booking.setPriceGB(priceGB);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setCustomer(customer);
        booking.setBike(bike);
        booking.setStatus(dto.getStatus());


        em.persist(booking);
        return booking;
    }



    public List<Bike> getBikeByAvailability(LocalDate from, LocalDate to){
        String jpql = """
                select i from Bike i where not exists (
                select b from Bookings b where b.bike = i
                and b.startDate <= :toDate and b.endDate >= :fromDate
                )
                """;

        return em.createQuery(jpql, Bike.class)
                .setParameter("fromDate", from)
                .setParameter("toDate", to)
                .getResultList();


    }

    public List<Bookings> getBookingsByCustomer(Long customerId) {
        return em.createQuery(
                        "SELECT b FROM Bookings b WHERE b.customer.id = :customerId", Bookings.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }




    public Bookings updateBookingPatch(Long bookingId, Map<String, Object> updates) {
        Bookings booking = em.find(Bookings.class, bookingId);
        if (booking == null) {
            return null;
        }


        if (updates.containsKey("bikeId")) {
            Long bikeId = ((Number) updates.get("bikeId")).longValue();
            Bike bike = em.find(Bike.class, bikeId);
            if (bike != null) {
                booking.setBike(bike);
            }
        }


        if (updates.containsKey("startDate")) {
            booking.setStartDate(LocalDate.parse((String) updates.get("startDate")));
        }

        if (updates.containsKey("endDate")) {
            booking.setEndDate(LocalDate.parse((String) updates.get("endDate")));
        }

        return em.merge(booking);
    }


}
