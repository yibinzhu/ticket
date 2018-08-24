package com.clipservice.eticket.ui.ticket.myTicketDetail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clipservice.eticket.R;

public class SeatTypeLayout extends RelativeLayout {
    private TextView seatType_tv;
    private TextView counter_tv;
    private String seatType;
    private String counter;
    private View mView;

    public SeatTypeLayout(Context context) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.activity_seat_type_layout,this,true);
        initView();
    }

    public SeatTypeLayout(Context context, AttributeSet attrs, String seatType, String counter) {
        super(context, attrs);
        this.seatType = seatType;
        this.counter = counter;
    }

    public SeatTypeLayout(Context context, AttributeSet attrs, int defStyleAttr, String seatType, String counter) {
        super(context, attrs, defStyleAttr);
        this.seatType = seatType;
        this.counter = counter;
    }

    public SeatTypeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, String seatType, String counter) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.seatType = seatType;
        this.counter = counter;
    }

    private void initView() {
        seatType_tv = (TextView)mView.findViewById(R.id.seat_type);
        counter_tv = (TextView)mView.findViewById(R.id.counter);
    }

    public String getCounter() {
        return counter;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setCounter(String counter) {
        this.counter = counter;
        counter_tv.setText(counter);
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
        seatType_tv.setText(seatType);
    }
}
