package com.wigell.mc.DAO;

import com.wigell.mc.Entity.Bike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BikeDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Bike> getAllBikes() {
        return em.createQuery("select i from Bike i", Bike.class)
                .getResultList();
    }

    public Bike getBikeById(int id) {
        return em.createQuery("select i from Bike i where i.id = :id", Bike.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Bike createBike(Bike bike) {
        em.merge(bike);
        return bike;
    }

    public Bike updateBikes(Long id, Bike bikeBody) {
        Bike existingBike = em.find(Bike.class, id);
        existingBike.setBike_name(bikeBody.getBike_name());
        return existingBike;
    }

    public boolean deleteBikes(Long id) {
        Bike existingBike = em.find(Bike.class, id);
        em.remove(existingBike);
        return true;
    }
}
