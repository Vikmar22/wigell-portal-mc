package com.wigell.mc.Service;

import com.wigell.mc.DAO.BikeDAO;
import com.wigell.mc.Entity.Bike;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeDAO bikeDAO;

    public BikeService(BikeDAO bikeDAO) {
        this.bikeDAO = bikeDAO;
    }

    @Transactional
    public List<Bike> getAllBikes() {return bikeDAO.getAllBikes(); }

    @Transactional
    public Bike getBikeById(int id) { return bikeDAO.getBikeById(id); }

    @Transactional
    public Bike createBike(Bike bike) { return bikeDAO.createBike(bike); }

    @Transactional
    public Bike updateBike(Long id, Bike bike) {return bikeDAO.updateBikes(id, bike);}

    @Transactional
    public boolean deleteBike(Long id) {return bikeDAO.deleteBikes(id);}
}
