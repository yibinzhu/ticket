package com.clipservice.eticket.models;

import java.util.List;

public class PublishingResponseModel {
    private List<String> list;

    public PublishingResponseModel(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
