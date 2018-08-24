package com.clipservice.eticket.models;

public class PresentTicketModel {
    private String PlayTitle;
    private String PlayTime;
    private String sequence;
    private String counter;
    private int status;
    private String key;
    private String ticketImg;

    public PresentTicketModel(String playTitle, String playTime, String sequence, String counter, int status, String key, String ticketImg) {
        PlayTitle = playTitle;
        PlayTime = playTime;
        this.sequence = sequence;
        this.counter = counter;
        this.status = status;
        this.key = key;
        this.ticketImg = ticketImg;
    }

    public String getPlayTitle() {
        return PlayTitle;
    }

    public String getPlayTime() {
        return PlayTime;
    }

    public String getSequence() {
        return sequence;
    }

    public String getCounter() {
        return counter;
    }

    public int getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }

    public String getTicketImg() {
        return ticketImg;
    }

    public void setPlayTitle(String playTitle) {
        PlayTitle = playTitle;
    }

    public void setPlayTime(String playTime) {
        PlayTime = playTime;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTicketImg(String ticketImg) {
        this.ticketImg = ticketImg;
    }
}
