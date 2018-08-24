package com.clipservice.eticket.ui.ticket.ticketPayment;

public class OderPostModel {
    private String enc_playNum;
    private String enc_playSaleNum;
    private String enc_sequence;
    private String enc_priceGrp;
    private String seatData;
    private String sId;
    private String enc_storeNum;
    private String id;
    private String name;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email1;
    private String email2;
    private String price;
    private String cardNum;
    private String cardYY;
    private String cardMM;
    private String halbu;
    private String authKey;

    public OderPostModel(String enc_playNum, String enc_playSaleNum, String enc_sequence, String enc_priceGrp, String seatData, String sId, String enc_storeNum, String id, String name, String phone1, String phone2, String phone3, String email1, String email2, String price, String cardNum, String cardYY, String cardMM, String halbu, String authKey) {
        this.enc_playNum = enc_playNum;
        this.enc_playSaleNum = enc_playSaleNum;
        this.enc_sequence = enc_sequence;
        this.enc_priceGrp = enc_priceGrp;
        this.seatData = seatData;
        this.sId = sId;
        this.enc_storeNum = enc_storeNum;
        this.id = id;
        this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email1 = email1;
        this.email2 = email2;
        this.price = price;
        this.cardNum = cardNum;
        this.cardYY = cardYY;
        this.cardMM = cardMM;
        this.halbu = halbu;
        this.authKey = authKey;
    }

    public String getEnc_playNum() {
        return enc_playNum;
    }

    public String getEnc_playSaleNum() {
        return enc_playSaleNum;
    }

    public String getEnc_sequence() {
        return enc_sequence;
    }

    public String getEnc_priceGrp() {
        return enc_priceGrp;
    }

    public String getSeatData() {
        return seatData;
    }

    public String getsId() {
        return sId;
    }

    public String getEnc_storeNum() {
        return enc_storeNum;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getPrice() {
        return price;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCardYY() {
        return cardYY;
    }

    public String getCardMM() {
        return cardMM;
    }

    public String getHalbu() {
        return halbu;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setEnc_playNum(String enc_playNum) {
        this.enc_playNum = enc_playNum;
    }

    public void setEnc_playSaleNum(String enc_playSaleNum) {
        this.enc_playSaleNum = enc_playSaleNum;
    }

    public void setEnc_sequence(String enc_sequence) {
        this.enc_sequence = enc_sequence;
    }

    public void setEnc_priceGrp(String enc_priceGrp) {
        this.enc_priceGrp = enc_priceGrp;
    }

    public void setSeatData(String seatData) {
        this.seatData = seatData;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public void setEnc_storeNum(String enc_storeNum) {
        this.enc_storeNum = enc_storeNum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardYY(String cardYY) {
        this.cardYY = cardYY;
    }

    public void setCardMM(String cardMM) {
        this.cardMM = cardMM;
    }

    public void setHalbu(String halbu) {
        this.halbu = halbu;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
