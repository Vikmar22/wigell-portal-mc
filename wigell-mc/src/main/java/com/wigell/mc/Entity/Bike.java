package com.wigell.mc.Entity;

import jakarta.persistence.*;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bike_name;

    public Bike() {

    }

    public Bike(Long id, String bike_name) {
        this.id = id;
        this.bike_name = bike_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBike_name() {
        return bike_name;
    }

    public void setBike_name(String bike_name) {
        this.bike_name = bike_name;
    }

    @Override
    public String toString() {
        return "Customer{id=" + id +
                ", bike_name='" + bike_name + '\'' +
                "}";
    }
}
