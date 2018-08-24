package com.clipservice.eticket.ui.adDetail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.clipservice.eticket.R;

public class AdDetailActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private static final String TAG = "AdDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);

        Intent intent = getIntent();
        String adType = intent.getStringExtra("adType");
        Bundle bundle = new Bundle();
        switch (adType){
            case "0"://url
                String url = intent.getStringExtra("url");
                Log.d(TAG,"url is"+url);
                bundle.putString("url",url);
                AdUrlFragment adUrlFragment = new AdUrlFragment();
                adUrlFragment.setArguments(bundle);
                fragmentManager = getFragmentManager();
                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container,adUrlFragment);
                ft.commit();
                break;

            case "1"://img
                String imgUrl = intent.getStringExtra("detailImg");
                bundle.putString("imgUrl",imgUrl);
                Log.d(TAG,"imgUrl is"+imgUrl);
                AdImgFragment adImgFragment = new AdImgFragment();
                adImgFragment.setArguments(bundle);
                fragmentManager = getFragmentManager();
                ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container,adImgFragment);
                ft.commit();
                break;
        }
    }

}
