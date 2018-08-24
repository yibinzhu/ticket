package com.clipservice.eticket.ui.ticket.ticketPayment;

public class PrepaidModel {
    private String enc_playNum;
    private String seatType;
    private String enc_priceGrp;
    private int num;

    public PrepaidModel(String enc_priceGrp,String enc_playNum, String seatType, int num) {
        this.enc_playNum = enc_playNum;
        this.seatType = seatType;
        this.num = num;
        this.enc_priceGrp = enc_priceGrp;
    }

    public String getEnc_priceGrp() {
        return enc_priceGrp;
    }

    public String getEnc_playNum() {
        return enc_playNum;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getNum() {
        return num;
    }

    public void setEnc_playNum(String enc_playNum) {
        this.enc_playNum = enc_playNum;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setEnc_priceGrp(String enc_priceGrp) {
        this.enc_priceGrp = enc_priceGrp;
    }
}
