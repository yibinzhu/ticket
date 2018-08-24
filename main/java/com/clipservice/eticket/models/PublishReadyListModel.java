package com.clipservice.eticket.models;

public class PublishReadyListModel {
    private String enc_ticketNum;
    private String enc_validKey;
    private String enc_orderNum;
    private String enc_printGrp;
    private String isGift;
    private String playTitle;
    private String sequenceText;
    private String seatType;

    public PublishReadyListModel(String enc_ticketNum, String enc_validKey, String enc_orderNum, String enc_printGrp, String isGift, String playTitle, String sequenceText, String seatType) {
        this.enc_ticketNum = enc_ticketNum;
        this.enc_validKey = enc_validKey;
        this.enc_orderNum = enc_orderNum;
        this.enc_printGrp = enc_printGrp;
        this.isGift = isGift;
        this.playTitle = playTitle;
        this.sequenceText = sequenceText;
        this.seatType = seatType;
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

    public String getIsGift() {
        return isGift;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public String getSequenceText() {
        return sequenceText;
    }

    public String getSeatType() {
        return seatType;
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

    public void setIsGift(String isGift) {
        this.isGift = isGift;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    public void setSequenceText(String sequenceText) {
        this.sequenceText = sequenceText;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

}
