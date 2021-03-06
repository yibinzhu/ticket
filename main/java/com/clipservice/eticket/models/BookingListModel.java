package com.clipservice.eticket.models;

/**
 * Created by clip-771 on 2018-03-07.
 */

public class BookingListModel {
    private String playNum;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String playTitle;
    private String theaterGrpName;
    private String genres;
    private String contentData;
    private String saleStartdate;
    private String saleEnddate;
    private String theaterName;
    private String lat;
    private String lng;
    private String posterImg;

    public BookingListModel(String playNum, String enc_playNum, String enc_playSaleNum, String playTitle, String theaterGrpName, String genres, String contentData, String saleStartdate, String saleEnddate, String theaterName, String lat, String lng, String posterImg){
        this.playNum = playNum ;
        this.enc_playNum = enc_playNum;
        this.enc_playSaleNum = enc_playSaleNum;
        this.playTitle = playTitle;
        this.theaterGrpName = theaterGrpName;
        this.genres = genres;
        this.contentData = contentData ;
        this.saleStartdate = saleStartdate;
        this.saleEnddate = saleEnddate;
        this.theaterName = theaterName;
        this.lat = lat;
        this.lng = lng;
        this.posterImg = posterImg;
    }
    public void setPlayNum(String playNum){
        this.playNum = playNum;
    }

    public void setEnc_playNum(String enc_playNum){
        this.enc_playNum = enc_playNum;
    }

    public void setEnc_playSaleNum(String enc_playSaleNum){
        this.enc_playSaleNum = enc_playSaleNum;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public void setTheaterGrpName(String theaterGrpName) {
        this.theaterGrpName = theaterGrpName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public void setPlayTitle(String playTitle){
        this.playTitle = playTitle;
    }

    public void setGenres(String genres){
        this.genres = genres;
    }

    public void setSaleStartdate(String saleStartdate){
        this.saleStartdate = saleStartdate;
    }

    public void setSaleEnddate(String saleEnddate){
        this.saleStartdate = saleEnddate;
    }

    public void setLat(String lat){
        this.lat = lat;
    }

    public void setLng(String lng){
        this.lng = lng;
    }

    public void setPostImg(String postImg){
        this.posterImg = postImg;
    }

    public String getPlayNum(){
        return playNum;
    }

    public String getEnc_playNum(){
        return enc_playNum;
    }

    public String getEnc_playSaleNum(){
        return enc_playSaleNum;
    }

    public String getPlayTitle(){
        return playTitle;
    }

    public String getGenres(){
        return genres;
    }

    public String getSaleStartdate(){
        return saleStartdate;
    }

    public String getSaleEnddate(){
        return saleEnddate;
    }

    public String getLat(){
        return lat;
    }

    public String getLng(){
        return lng;
    }

    public String getPostImg(){
        return posterImg;
    }

    public String getContentData() {
        return contentData;
    }

    public String getTheaterGrpName() {
        return theaterGrpName;
    }

    public String getTheaterName() {
        return theaterName;
    }

}
