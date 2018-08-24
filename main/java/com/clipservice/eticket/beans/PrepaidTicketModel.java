package com.clipservice.eticket.beans;

import java.util.ArrayList;

public class PrepaidTicketModel {
    private String id_db;
    private String paly_title;
    private String img;
    private String enc_orderNum;
    private String userOrderNum;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String enc_sequence;
    private String enc_priceGrp;
    private String seatData;
    private int ticket_counter;
    private String sId;
    private String enc_storeNum;
    private String id;
    private String phone;

    public PrepaidTicketModel(String id_db,String paly_title, String img, String enc_orderNum, String userOrderNum, String enc_playNum, String enc_playSaleNum, String enc_sequence, String enc_priceGrp, String seatData, int ticket_counter, String sId, String enc_storeNum, String id, String phone) {
        this.paly_title = paly_title;
        this.img = img;
        this.enc_orderNum = enc_orderNum;
        this.userOrderNum = userOrderNum;
        this.enc_playNum = enc_playNum;
        this.enc_playSaleNum = enc_playSaleNum;
        this.enc_sequence = enc_sequence;
        this.enc_priceGrp = enc_priceGrp;
        this.seatData = seatData;
        this.ticket_counter = ticket_counter;
        this.sId = sId;
        this.enc_storeNum = enc_storeNum;
        this.id = id;
        this.phone = phone;
    }

    public String getId_db() {
        return id_db;
    }

    public String getPaly_title() {
        return paly_title;
    }

    public String getImg() {
        return img;
    }

    public String getEnc_orderNum() {
        return enc_orderNum;
    }

    public String getUserOrderNum() {
        return userOrderNum;
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

    public int getTicket_counter() {
        return ticket_counter;
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

    public String getPhone() {
        return phone;
    }

    public void setId_db(String id_db) {
        this.id_db = id_db;
    }

    public void setPaly_title(String paly_title) {
        this.paly_title = paly_title;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setEnc_orderNum(String enc_orderNum) {
        this.enc_orderNum = enc_orderNum;
    }

    public void setUserOrderNum(String userOrderNum) {
        this.userOrderNum = userOrderNum;
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

    public void setTicket_counter(int ticket_counter) {
        this.ticket_counter = ticket_counter;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
