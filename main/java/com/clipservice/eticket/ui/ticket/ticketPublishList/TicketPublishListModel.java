package com.clipservice.eticket.ui.ticket.ticketPublishList;

/**
 * Created by clip-771 on 2018-03-12.
 */

public class TicketPublishListModel {
    String enc_ticketNum;
    String enc_validKey;
    String isGift;
    String sequenceText;
    String seatType;
    String playTitle;

    public TicketPublishListModel(String enc_ticketNum,String enc_validKey,String isGift,String playTitle,String sequenceText,String seatType){
        this.enc_ticketNum = enc_ticketNum;
        this.enc_validKey = enc_validKey;
        this.isGift = isGift;
        this.playTitle = playTitle;
        this.sequenceText = sequenceText;
        this.sequenceText = seatType;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getSequenceText() {
        return sequenceText;
    }

    public String getEnc_validKey() {
        return enc_validKey;
    }

    public String getEnc_ticketNum() {
        return enc_ticketNum;
    }

    public String getIsGift() {
        return isGift;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setSequenceText(String sequenceText) {
        this.sequenceText = sequenceText;
    }

    public void setEnc_validKey(String enc_validKey) {
        this.enc_validKey = enc_validKey;
    }

    public void setEnc_ticketNum(String enc_ticketNum) {
        this.enc_ticketNum = enc_ticketNum;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setIsGift(String isGift) {
        this.isGift = isGift;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

}
