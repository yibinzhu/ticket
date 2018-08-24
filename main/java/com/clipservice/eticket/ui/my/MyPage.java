package com.clipservice.eticket.ui.my;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.clipservice.eticket.R;
import com.clipservice.eticket.ui.ticket.ticketPresentList.TicketPresentListActivity;

public class MyPage extends Fragment {
    private View mView;
    android.widget.LinearLayout tabTicketBook;
    android.widget.LinearLayout tabGift;
    android.widget.LinearLayout tabParking;
    android.widget.LinearLayout tabSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_page, container, false);
        init();
        return mView;
    }

    private void init(){
        tabTicketBook = (android.widget.LinearLayout)mView.findViewById(R.id.tab_ticket_book);
        tabGift = (android.widget.LinearLayout)mView.findViewById(R.id.tab_gift);
        tabParking = (android.widget.LinearLayout)mView.findViewById(R.id.tab_parking);
        tabSetting = (android.widget.LinearLayout)mView.findViewById(R.id.tab_setting);

        tabTicketBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = startActivity(new Intent(MyPage.this, TicketPresentListActivity.class));
            }
        });
        tabGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),TicketPresentListActivity.class);
                startActivity(intent);
            }
        });
        tabParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = startActivity(new Intent(MyPage.this, TicketPresentListActivity.class));
            }
        });
        tabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = startActivity(new Intent(MyPage.this, TicketPresentListActivity.class));
            }
        });
    }
}
