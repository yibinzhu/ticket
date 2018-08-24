package com.clipservice.eticket.models;
/*
* {"RealTicket":1,"DataTicket":2,"Ready":2049,"Published":4097,
* "Accepted":8193,"Entered":16385,"Sharing":32770,"Shared":65538,
* "Cancel":131074,"Expired":262146,"Printed":524290},
* "TicketTypes":{"Play":1,"Coupon":2,"Goods":3,"Invite":4}}
*
* */
public class GrpTicketModel {
    private String playTitle;
    private String enc_playNum;
    private String 	enc_sequence;
    private String enc_theaterNum;
    private int status;
    private String key;
    private String img;

    public GrpTicketModel(String playTitle, String enc_playNum, String enc_sequence, String enc_theaterNum,int status, String key, String img) {
        this.playTitle = playTitle;
        this.enc_playNum = enc_playNum;
        this.enc_sequence = enc_sequence;
        this.status = status;
        this.key = key;
        this.img = img;
    }

    public String getEnc_theaterNum() {
        return enc_theaterNum;
    }

    public void setEnc_theaterNum(String enc_theaterNum) {
        this.enc_theaterNum = enc_theaterNum;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public String getEnc_playNum() {
        return enc_playNum;
    }

    public String getEnc_sequence() {
        return enc_sequence;
    }

    public int getStatus() {
        return status;
    }

    public String getKey() {
        return key;
    }

    public String getImg() {
        return img;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public void setEnc_playNum(String enc_playNum) {
        this.enc_playNum = enc_playNum;
    }

    public void setEnc_sequence(String enc_sequence) {
        this.enc_sequence = enc_sequence;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
