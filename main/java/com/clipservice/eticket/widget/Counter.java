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

import java.text.NumberFormat;

/**
 * Created by clip-771 on 2018-02-07.
 * Counter of ticket booking detail
 */

public class Counter extends LinearLayout{
    private View mView;
    private TextView num_counter;
    private TextView priceTotal;
    private TextView ticketPrice;
    private TextView seat_type ;
    private Button btn_plus;
    private Button btn_minus;
    private int ticket_price;
    private int total;
    private String seatType;
    private String ticketNum;
    private String Scounter;
    private String playTitle;

    public Counter(Context context) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_counter,this,true);
        init();
    }

    public Counter(Context context, int ticket_price, String seatType) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.widget_counter,this,true);
        this.ticket_price = ticket_price;
        this.seatType = seatType;
        initView();
        init();

    }

    private void initView() {
        btn_plus = (Button)mView.findViewById(R.id.btn_plus);
        btn_minus = (Button)mView.findViewById(R.id.btn_minus);
        num_counter = (TextView) mView.findViewById(R.id.num_counter);
        priceTotal = (TextView)mView.findViewById(R.id.price_total);
        ticketPrice = (TextView)mView.findViewById(R.id.ticket_price);
        seat_type = (TextView)mView.findViewById(R.id.seatType);
    }

    private void init() {
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scounter = num_counter.getText().toString();
                int Icounter = Integer.parseInt(Scounter);
                if(Icounter >= 0) {
                    Icounter ++;
                }
                refreshResult(Icounter);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scounter = num_counter.getText().toString();
                int Icounter = Integer.parseInt(Scounter);
                if(Icounter > 1) {
                    Icounter --;
                } else {
                    Icounter = 0;
                }
                refreshResult(Icounter);
            }
        });
    }

    private void refreshResult(int Icounter) {
        num_counter.setText(Icounter+"");
        this.ticketNum = String.valueOf(Icounter);
        total = ticket_price*Icounter;
        NumberFormat nf = NumberFormat.getInstance();
        String price_format = nf.format(total);
//        priceTotal.setText(price_format);
    }

    public String getTicketNum(){
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public void setPrice(int price) {
        this.ticket_price = price;
        NumberFormat nf = NumberFormat.getInstance();
        String price_format = nf.format(price);
        ticketPrice.setText(price_format);
    }

    public int getTicket_price() {
        return ticket_price;
    }

    public int getTotal(){
        return total;
    }

    public String getSeatType(){
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
        seat_type.setText(seatType);
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }
}
