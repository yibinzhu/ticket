package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

/**
 * Created by clip-771 on 2018-03-07.
 */

public class SequenceListModel {
    private String sequence;
    private String enc_sequence;
    private String playDateTime;
    private String sequenceText;

    public SequenceListModel(String sequence,String playdatetime,String sequenceText,String enc_sequence){
        this.sequence = sequence;
        this.enc_sequence = enc_sequence;
        this.playDateTime = playdatetime;
        this.sequenceText = sequenceText;
    }
    public void setSequence(){
        this.sequence = sequence;
    }

    public void setEnc_sequence(){
        this.enc_sequence = enc_sequence;
    }

    public void setSequenceText(){
        this.sequenceText = sequenceText;
    }

    public void setPlayDateTime(String playDateTime) {
        this.playDateTime = playDateTime;
    }

    public String getSequence(){
        return sequence;
    }

    public String getEnc_sequence(){
        return enc_sequence;
    }

    public String getSequenceText(){
        return sequenceText;
    }

    public String getPlayDateTime() {
        return playDateTime;
    }
}
