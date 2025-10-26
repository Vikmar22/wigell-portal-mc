package com.wigell.mc.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String time;
    private int priceSEK;
    private int priceGB;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bike_id", referencedColumnName = "id")
    private Bike bike;
    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;
    private String status;


    public Bookings(){

    }

    public Bookings(String time, int priceSEK, int priceGB, LocalDate startDate, LocalDate endDate, String status) {

        this.time = time;
        this.priceSEK = priceSEK;
        this.priceGB = priceGB;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(int priceSEK) {
        this.priceSEK = priceSEK;
    }

    public int getPriceGB() {
        return priceGB;
    }

    public void setPriceGB(int priceGB) {
        this.priceGB = priceGB;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bookings{id=" + id +
                ", time='" + time + '\'' +
                ", priceSEK='" + priceSEK + '\'' +
                ", priceGB='" + priceGB + '\'' +
                ", startDate=" + startDate + '\'' +
                ", endDate=" + endDate + '\'' +
                ", bike=" + bike + '\'' +
                ", customer=" + customer + '\'' +
                ", status='" + status + '\'' +
                "}";
    }
}
