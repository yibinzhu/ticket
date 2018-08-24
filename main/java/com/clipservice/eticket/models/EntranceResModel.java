package com.clipservice.eticket.models;

import java.util.Date;

public class EntranceResModel {
    private Boolean result;
    private Date enterdate;

    public EntranceResModel(Boolean result, Date enterdate) {
        this.result = result;
        this.enterdate = enterdate;
    }

    public Boolean getResult() {
        return result;
    }

    public Date getEnterdate() {
        return enterdate;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public void setEnterdate(Date enterdate) {
        this.enterdate = enterdate;
    }

}
