package com.clipservice.eticket.models;

public class SetUserPostModel {
    private String machineKey;
    private String phone1;
    private String phone2;
    private String phone3;
    private String pushKey;
    private String os;
    private boolean sendMsg;
    private String authkey;

    public SetUserPostModel(String authkey,String machineKey, String phone1, String phone2, String phone3, String pushKey, String os,boolean sendMsg) {
        this.authkey = authkey;
        this.machineKey = machineKey;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.pushKey = pushKey;
        this.os = os;
        this.sendMsg = sendMsg;
    }

    public boolean getSendMsg(){
        return sendMsg;
    }
    public String getAuthkey() {
        return authkey;
    }

    public String getMachineKey() {
        return machineKey;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public String getPushKey() {
        return pushKey;
    }

    public String getOs() {
        return os;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public void setMachineKey(String machineKey) {
        this.machineKey = machineKey;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setSendMsg(boolean sendMsg) {
        this.sendMsg = sendMsg;
    }
}
