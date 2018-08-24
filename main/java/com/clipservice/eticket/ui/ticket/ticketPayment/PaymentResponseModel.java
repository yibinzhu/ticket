package com.clipservice.eticket.ui.ticket.ticketPayment;

public class PaymentResponseModel {
    private String result;
    private String errMsg;
    private String enc_orderNum;
    private String userOrderNum;

    public PaymentResponseModel(String result, String errMsg, String enc_orderNum, String userOrderNum) {
        this.result = result;
        this.errMsg = errMsg;
        this.enc_orderNum = enc_orderNum;
        this.userOrderNum = userOrderNum;
    }

    public String getResult() {
        return result;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getEnc_orderNum() {
        return enc_orderNum;
    }

    public String getUserOrderNum() {
        return userOrderNum;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setEnc_orderNum(String enc_orderNum) {
        this.enc_orderNum = enc_orderNum;
    }

    public void setUserOrderNum(String userOrderNum) {
        this.userOrderNum = userOrderNum;
    }
}
