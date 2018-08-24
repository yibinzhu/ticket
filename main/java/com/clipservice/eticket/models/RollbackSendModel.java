package com.clipservice.eticket.models;

import java.util.ArrayList;

//선물한 티켓 취소하기(이미 상대가 등록한 경우 처리불가)선물
public class RollbackSendModel {
    private ArrayList<TicketStatus> failList;

    private class TicketStatus{
        private String enc_ticketNum;
        private String resultCode;
        private String errMsg;

        public TicketStatus(String enc_ticketNum, String resultCode, String errMsg) {
            this.enc_ticketNum = enc_ticketNum;
            this.resultCode = resultCode;
            this.errMsg = errMsg;
        }

        public String getEnc_ticketNum() {
            return enc_ticketNum;
        }

        public String getResultCode() {
            return resultCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setEnc_ticketNum(String enc_ticketNum) {
            this.enc_ticketNum = enc_ticketNum;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }
}
