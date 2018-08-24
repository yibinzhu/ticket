package com.clipservice.eticket.models;

public class TicketingResItemModel {
    private String enc_ticketNum;
    private String enc_validKey;
    private String result;

    public TicketingResItemModel(String enc_ticketNum, String enc_validKey, String result) {
        this.enc_ticketNum = enc_ticketNum;
        this.enc_validKey = enc_validKey;
        this.result = result;
    }

    public String getEnc_ticketNum() {
        return enc_ticketNum;
    }

    public String getEnc_validKey() {
        return enc_validKey;
    }

    public String getResult() {
        return result;
    }

    public void setEnc_ticketNum(String enc_ticketNum) {
        this.enc_ticketNum = enc_ticketNum;
    }

    public void setEnc_validKey(String enc_validKey) {
        this.enc_validKey = enc_validKey;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
