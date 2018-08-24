package com.clipservice.eticket.models;

public class AdBannerResponseModel {
    private int bannerType;
    private int actionType;
    private String bannerData;
    private String img;
    private String detailImg;

    public AdBannerResponseModel(int bannerType, int actionType, String bannerData, String img, String detailImg) {
        this.bannerType = bannerType;
        this.actionType = actionType;
        this.bannerData = bannerData;
        this.img = img;
        this.detailImg = detailImg;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public String getImg() {
        return img;
    }

    public int getBannerType() {
        return bannerType;
    }

    public int getActionType() {
        return actionType;
    }

    public  String getBannerData() {
        return bannerData;
    }

    public void setBannerType(int bannerType) {
        this.bannerType = bannerType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public void setBannerData(String bannerData) {
        this.bannerData = bannerData;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public class BannerData{
        private String enc_playNum;
        private String enc_playSaleNum;

        public BannerData(String enc_playNum, String enc_playSaleNum) {
            this.enc_playNum = enc_playNum;
            this.enc_playSaleNum = enc_playSaleNum;
        }

        public String getEnc_playNum() {
            return enc_playNum;
        }

        public String getEnc_playSaleNum() {
            return enc_playSaleNum;
        }

        public void setEnc_playNum(String enc_playNum) {
            this.enc_playNum = enc_playNum;
        }

        public void setEnc_playSaleNum(String enc_playSaleNum) {
            this.enc_playSaleNum = enc_playSaleNum;
        }
    }

}
