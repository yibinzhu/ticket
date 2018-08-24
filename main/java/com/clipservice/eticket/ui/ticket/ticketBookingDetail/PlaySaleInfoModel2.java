package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

public class PlaySaleInfoModel2 {
    private SeatTypes seatTypes;

    public static class SeatTypes{
        private int sort;
        private int price;
        private String seatType;
        private String enc_priceGr;

        public SeatTypes(int sort, int price, String seatType, String enc_priceGr) {
            this.sort = sort;
            this.price = price;
            this.seatType = seatType;
            this.enc_priceGr = enc_priceGr;
        }

        public int getSort() {
            return sort;
        }

        public int getPrice() {
            return price;
        }

        public String getSeatType() {
            return seatType;
        }

        public String getEnc_priceGr() {
            return enc_priceGr;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public void setEnc_priceGr(String enc_priceGr) {
            this.enc_priceGr = enc_priceGr;
        }
    }

    public void setSeatTypes(SeatTypes seatTypes) {
        this.seatTypes = seatTypes;
    }

    public SeatTypes getSeatTypes() {
        return seatTypes;
    }

}
