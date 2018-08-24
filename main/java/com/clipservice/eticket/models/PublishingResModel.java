package com.clipservice.eticket.models;

public class PublishingResModel {
    private String failTicketNums;

    public PublishingResModel(String failTicketNums) {
        this.failTicketNums = failTicketNums;
    }

    public String getFailTicketNums() {
        return failTicketNums;
    }

    public void setFailTicketNums(String failTicketNums) {
        this.failTicketNums = failTicketNums;
    }
}
