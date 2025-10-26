package com.wigell.mc.Controller;

import com.wigell.mc.Entity.Bike;
import com.wigell.mc.Service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/admin/api/v1")
public class BikeController {

    private final BikeService bikeService;
    private static final Logger log = LoggerFactory.getLogger(BikeController.class);

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/bikes")
    public List<Bike> getAllBikes() {
        log.info("Retrieved all bikes");
        return bikeService.getAllBikes();
    }

    @GetMapping("/bikes/{id}")
    public Bike getBikes(@PathVariable("id") int id) {
        bikeService.getBikeById(id);
        log.info("Retrieved bike with id {}", id);
        return bikeService.getBikeById(id);
    }

    @PostMapping("/bikes")
    public ResponseEntity<Bike> createBikes(@RequestBody Bike bike) {
        Bike created = bikeService.createBike(bike);
        log.info("Created bike {}", created);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/bikes/{id}")
    public ResponseEntity<Bike> updateBikes(@PathVariable("id") Long id, @RequestBody Bike bike) {
        Bike updated = bikeService.updateBike(id, bike);
        log.info("Updated bike {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/bikes/{id}")
    public ResponseEntity<Bike> deleteBikes(@PathVariable("id") Long id) {
        boolean deleted = bikeService.deleteBike(id);
        log.info("Deleted bike {}" + deleted);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
