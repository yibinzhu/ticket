package com.clipservice.eticket.models;

public class BannerModel {
    private String bannerType;
    private String title;
    private BannerData bannerData;

    public BannerModel(String bannerType, String title, BannerData bannerData) {
        this.bannerType = bannerType;
        this.title = title;
        this.bannerData = bannerData;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public void setTitl(String titl) {
        this.title = titl;
    }

    public void setBannerData(BannerData bannerData) {
        this.bannerData = bannerData;
    }

    public String getBannerType() {
        return bannerType;
    }

    public String getTitl() {
        return title;
    }

    public BannerData getBannerData() {
        return bannerData;
    }
    class BannerData{
        private String bannerType;
        private String actionType;
        private String bannerData;
        private String img;
        private String detailImg;

        public BannerData(String bannerType, String actionType, String bannerData, String img, String detailImg) {
            this.bannerType = bannerType;
            this.actionType = actionType;
            this.bannerData = bannerData;
            this.img = img;
            this.detailImg = detailImg;
        }

        public String getBannerType() {
            return bannerType;
        }

        public String getActionType() {
            return actionType;
        }

        public String getBannerData() {
            return bannerData;
        }

        public String getImg() {
            return img;
        }

        public String getDetailImg() {
            return detailImg;
        }

        public void setBannerType(String bannerType) {
            this.bannerType = bannerType;
        }

        public void setActionType(String actionType) {
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
    }
}
