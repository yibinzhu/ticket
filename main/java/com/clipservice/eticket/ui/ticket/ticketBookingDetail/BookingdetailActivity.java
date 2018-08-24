package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.clipservice.eticket.R;

public class BookingdetailActivity extends FragmentActivity {

    private Fragment bookingdetailFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    private static final String TAG = "BookingdetialActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking_detail);
        init();
        initFragment();
    }

    private void init() {

    }

    private void initFragment() {
        Intent intent = getIntent();
        String ticketInfo = intent.getStringExtra("infoFromMain");
        Bundle bundle = new Bundle();
        bundle.putString("infoFromMain",ticketInfo);
        Log.d(TAG,"get data from main activity =>"+ticketInfo);

        bookingdetailFragment = new BookingdetailFragment();
        bookingdetailFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container,bookingdetailFragment);
        ft.commit();
    }

}
