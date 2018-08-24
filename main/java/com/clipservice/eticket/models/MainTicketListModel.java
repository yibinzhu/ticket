package com.clipservice.eticket.models;
//Type:0 -> 보유티켓,1 -> 광고
//childListId: 해당하는 리스트중의 index

public class MainTicketListModel {
    private String ticketTitle;
    private int childListId;
    private String posterImg;
    private String Type;
    private String key;

    public MainTicketListModel(String ticketTitle, int childListId, String posterImg, String type, String key) {
        this.ticketTitle = ticketTitle;
        this.childListId = childListId;
        this.posterImg = posterImg;
        Type = type;
        this.key = key;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public int getChildListId() {
        return childListId;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public String getType() {
        return Type;
    }

    public String getKey() {
        return key;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public void setChildListId(int childListId) {
        this.childListId = childListId;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
