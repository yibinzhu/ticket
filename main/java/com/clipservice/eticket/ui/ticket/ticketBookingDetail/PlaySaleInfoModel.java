package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

/**
 * Created by clip-771 on 2018-03-08.
 */

public class PlaySaleInfoModel {
    private int sort;
    private int price;
    private String seatType;
    private String enc_priceGr;

    public PlaySaleInfoModel(String seatType, int sort, int price, String enc_priceGr){
        this.seatType = seatType;
        this.sort = sort;
        this.price = price;
        this.enc_priceGr = enc_priceGr;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getSort() {
        return sort;
    }

    public int getPrice() {
        return price;
    }

    public String getEnc_priceGr() {
        return enc_priceGr;
    }

}
