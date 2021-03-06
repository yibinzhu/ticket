package com.clipservice.eticket.models;

public class TicketDataModel {
    private String enc_ticketNum;
    private String enc_validKey;

    public TicketDataModel(String enc_ticketNum, String enc_validKey) {
        this.enc_ticketNum = enc_ticketNum;
        this.enc_validKey = enc_validKey;
    }

    public String getEnc_ticketNum() {
        return enc_ticketNum;
    }

    public String getEnc_validKey() {
        return enc_validKey;
    }

    public void setEnc_ticketNum(String enc_ticketNum) {
        this.enc_ticketNum = enc_ticketNum;
    }

    public void setEnc_validKey(String enc_validKey) {
        this.enc_validKey = enc_validKey;
    }

}
