package com.wigell.mc.DTO;

import java.time.LocalDate;

public class BookingRequest {
    private String time;
    private int priceSEK;
    private int priceGB;
    private LocalDate startDate;
    private LocalDate endDate;
    private int bikeId;
    private int customerId;
    private String status;

    public BookingRequest() {}

    public BookingRequest(String time, int priceSEK, int priceGB, LocalDate startDate,  LocalDate endDate, int bikeId, int customerId, String status) {
        this.time = time;
        this.priceSEK = priceSEK;
        this.priceGB = priceGB;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bikeId = bikeId;
        this.customerId = customerId;
        this.status = status;
    }


    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getPriceSEK() { return priceSEK; }
    public void setPriceSEK(int priceSEK) { this.priceSEK = priceSEK; }

    public int getPriceGB() { return priceGB; }
    public void setPriceGB(int priceGB) { this.priceGB = priceGB; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}