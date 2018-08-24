package com.clipservice.eticket.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clipservice.eticket.R;

public class TicketBookingInfo extends LinearLayout {
    private String seatType;
    private String ticketNum;
    private View mView;
    private TextView seatType_txt;
    private TextView ticketNum_txt;

    public TicketBookingInfo(Context context) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_ticketbookinginfo,this,true);
        initView();
    }

    public TicketBookingInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_ticketbookinginfo,this,true);
        initView();
    }

    public TicketBookingInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_ticketbookinginfo,this,true);
        initView();
    }

    public String getSeatType() {
        return seatType;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
        seatType_txt.setText(seatType);
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
        ticketNum_txt.setText(ticketNum);
    }

    public void initView(){
        seatType_txt = (TextView)mView.findViewById(R.id.seat_type);
        ticketNum_txt = (TextView)mView.findViewById(R.id.counter);
    }

}
