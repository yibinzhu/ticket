package com.clipservice.eticket.beans;

public class SeatDataModel {
    private String seatType;
    private String counter;

    public SeatDataModel(String seatType, String counter) {
        this.seatType = seatType;
        this.counter = counter;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getCounter() {
        return counter;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

}
