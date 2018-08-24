package com.clipservice.eticket.models;

public class BottomTicketListModel {
    private int childId;
    private String type;
    private String img;

    public BottomTicketListModel(int childId,String type,String img) {
        this.img = img;
        this.childId = childId;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getImg() {
        return img;
    }

    public int getChildId() {
        return childId;
    }

    public String getType() {
        return type;
    }

}
