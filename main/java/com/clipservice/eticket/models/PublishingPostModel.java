package com.clipservice.eticket.models;

public class PublishingPostModel {
    private String authKey;
    private String enc_memberNum;
    private String reqDatas;

    public PublishingPostModel(String authKey, String enc_memberNum, String reqDatas) {
        this.authKey = authKey;
        this.enc_memberNum = enc_memberNum;
        this.reqDatas = reqDatas;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getEnc_memberNum() {
        return enc_memberNum;
    }

    public String getReqDatas() {
        return reqDatas;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public void setEnc_memberNum(String enc_memberNum) {
        this.enc_memberNum = enc_memberNum;
    }

    public void setReqDatas(String reqDatas) {
        this.reqDatas = reqDatas;
    }

}
