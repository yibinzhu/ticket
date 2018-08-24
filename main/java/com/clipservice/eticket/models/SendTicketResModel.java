package com.clipservice.eticket.models;

import java.util.ArrayList;

public class SendTicketResModel {
    private ArrayList<String> failTicketNums;

    public SendTicketResModel(ArrayList<String> failTicketNums) {
        this.failTicketNums = failTicketNums;
    }

    public ArrayList<String> getFailTicketNums() {
        return failTicketNums;
    }

    public void setFailTicketNums(ArrayList<String> failTicketNums) {
        this.failTicketNums = failTicketNums;
    }
}
