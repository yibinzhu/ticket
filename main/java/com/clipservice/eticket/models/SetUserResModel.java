package com.clipservice.eticket.models;

public class SetUserResModel {
    private String enc_authKey;
    private String enc_memberNum;
    private String checkKey;

    public SetUserResModel(String enc_authKey, String enc_memberNum, String checkKey) {
        this.enc_authKey = enc_authKey;
        this.enc_memberNum = enc_memberNum;
        this.checkKey = checkKey;
    }

    public String getEnc_authKey() {
        return enc_authKey;
    }

    public String getEnc_memberNum() {
        return enc_memberNum;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setEnc_authKey(String enc_authKey) {
        this.enc_authKey = enc_authKey;
    }

    public void setEnc_memberNum(String enc_memberNum) {
        this.enc_memberNum = enc_memberNum;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }
}
