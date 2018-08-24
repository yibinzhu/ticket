package com.clipservice.eticket.models;

import java.util.ArrayList;
import java.util.HashMap;

public class SeatTypeModel {
    private String key;
    private ArrayList<HashMap<String,String>> list;

    public SeatTypeModel(String key, ArrayList<HashMap<String, String>> list) {
        this.key = key;
        this.list = list;
    }

    public String getKey() {
        return key;
    }

    public ArrayList<HashMap<String, String>> getList() {
        return list;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setList(ArrayList<HashMap<String, String>> list) {
        this.list = list;
    }

}
