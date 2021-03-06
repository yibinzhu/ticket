package com.clipservice.eticket.models;

/**
 * Created by clip-771 on 2018-03-08.
 */

public class TicketPresentListModel {
    private String enc_ticketNum;
    private String enc_validKey;
    private String enc_orderNum;
    private String enc_printGrp;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String playTitle;
    private String enc_sequence;
    private String sequenceText;
    private String seatType;
    private String enc_seatNum;
    private String discountNum;
    private String discountName;
    private String publishDate;
    private String status;
    private String statusName;
    private String ticketImg;

    public TicketPresentListModel(String enc_ticketNum, String enc_validKey, String enc_orderNum, String enc_printGrp, String enc_playNum, String enc_playSaleNum, String playTitle, String enc_sequence, String sequenceText, String seatType, String enc_seatNum, String discountNum, String discountName, String publishDate, String status, String statusName, String ticketImg) {
        this.enc_ticketNum = enc_ticketNum;
        this.enc_validKey = enc_validKey;
        this.enc_orderNum = enc_orderNum;
        this.enc_printGrp = enc_printGrp;
        this.enc_playNum = enc_playNum;
        this.enc_playSaleNum = enc_playSaleNum;
        this.playTitle = playTitle;
        this.enc_sequence = enc_sequence;
        this.sequenceText = sequenceText;
        this.seatType = seatType;
        this.enc_seatNum = enc_seatNum;
        this.discountNum = discountNum;
        this.discountName = discountName;
        this.publishDate = publishDate;
        this.status = status;
        this.statusName = statusName;
        this.ticketImg = ticketImg;
    }

    public String getEnc_ticketNum() {
        return enc_ticketNum;
    }

    public String getEnc_validKey() {
        return enc_validKey;
    }

    public String getEnc_orderNum() {
        return enc_orderNum;
    }

    public String getEnc_printGrp() {
        return enc_printGrp;
    }

    public String getEnc_playNum() {
        return enc_playNum;
    }

    public String getEnc_playSaleNum() {
        return enc_playSaleNum;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public String getEnc_sequence() {
        return enc_sequence;
    }

    public String getSequenceText() {
        return sequenceText;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getEnc_seatNum() {
        return enc_seatNum;
    }

    public String getDiscountNum() {
        return discountNum;
    }

    public String getDiscountName() {
        return discountName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getTicketImg() {
        return ticketImg;
    }

    public void setEnc_ticketNum(String enc_ticketNum) {
        this.enc_ticketNum = enc_ticketNum;
    }

    public void setEnc_validKey(String enc_validKey) {
        this.enc_validKey = enc_validKey;
    }

    public void setEnc_orderNum(String enc_orderNum) {
        this.enc_orderNum = enc_orderNum;
    }

    public void setEnc_printGrp(String enc_printGrp) {
        this.enc_printGrp = enc_printGrp;
    }

    public void setEnc_playNum(String enc_playNum) {
        this.enc_playNum = enc_playNum;
    }

    public void setEnc_playSaleNum(String enc_playSaleNum) {
        this.enc_playSaleNum = enc_playSaleNum;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public void setEnc_sequence(String enc_sequence) {
        this.enc_sequence = enc_sequence;
    }

    public void setSequenceText(String sequenceText) {
        this.sequenceText = sequenceText;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setEnc_seatNum(String enc_seatNum) {
        this.enc_seatNum = enc_seatNum;
    }

    public void setDiscountNum(String discountNum) {
        this.discountNum = discountNum;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setTicketImg(String ticketImg) {
        this.ticketImg = ticketImg;
    }
}
