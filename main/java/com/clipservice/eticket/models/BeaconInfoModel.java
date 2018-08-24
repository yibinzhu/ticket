package com.clipservice.eticket.models;

public class BeaconInfoModel {
    private String enc_theaterNum;
    private int beaconType;
    private String machineId;
    private int distance;
    private String appleId;

    public BeaconInfoModel(String enc_theaterNum, int beaconType, String machineId, int distance,String appleId) {
        this.enc_theaterNum = enc_theaterNum;
        this.beaconType = beaconType;
        this.machineId = machineId;
        this.distance = distance;
        this.appleId = appleId;
    }

    public String getAppleId() {
        return appleId;
    }

    public String getEnc_theaterNum() {
        return enc_theaterNum;
    }

    public int getBeaconType() {
        return beaconType;
    }

    public String getMachineId() {
        return machineId;
    }

    public int getDistance() {
        return distance;
    }

    public void setEnc_theaterNum(String enc_theaterNum) {
        this.enc_theaterNum = enc_theaterNum;
    }

    public void setBeaconType(int beaconType) {
        this.beaconType = beaconType;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }
}
