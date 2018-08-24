package com.clipservice.eticket.models;

public class SetUserModel {
    private String enc_authKey;
    private String enc_memberNum;

    public SetUserModel(String enc_authKey, String enc_memberNum) {
        this.enc_authKey = enc_authKey;
        this.enc_memberNum = enc_memberNum;
    }

    public String getEnc_authKey() {
        return enc_authKey;
    }

    public String getEnc_memberNum() {
        return enc_memberNum;
    }

    public void setEnc_authKey(String enc_authKey) {
        this.enc_authKey = enc_authKey;
    }

    public void setEnc_memberNum(String enc_memberNum) {
        this.enc_memberNum = enc_memberNum;
    }

}
