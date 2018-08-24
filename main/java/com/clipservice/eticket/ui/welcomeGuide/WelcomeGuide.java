package com.clipservice.eticket.ui.welcomeGuide;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.ui.main.MainActivity;
import com.clipservice.eticket.ui.splash.SplashActivity;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.firebase.iid.FirebaseInstanceId;
import com.prolificinteractive.materialcalendarview.WeekPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeGuide extends AppCompatActivity {
    private String phone_id;
    private String phone_number;
    private String phone_number_device;
    private String pushKey;
    private String mackineKey;
    private String phone1;
    private String phone2;
    private String phone3;
    private String encrypt_mackineKey;
    private ACache mCache;
    private Boolean isCheckVersion = false;
    private ViewPager viewPager;
    private Button welcomeGuideBtn;
    private List<View> list;
    private static final String TAG = "welcomeGuide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guide2);
        intView();
        initViewPager();
//        checkVerison();
//        requestReadPhoneStatePermission();
//        getPhoneInfo();
    }

    private void initViewPager() {
        list = new ArrayList<View>();
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.splash_1);
        list.add(iv);
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.splash_2);
        list.add(iv1);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.splash_3);
        list.add(iv2);
        viewPager.setAdapter(new welcomePagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    welcomeGuideBtn.setVisibility(View.VISIBLE);
                }else{
                    welcomeGuideBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        welcomeGuideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeGuide.this,MainActivity.class));
            }
        });
    }

    private void intView() {
        viewPager = (ViewPager)findViewById(R.id.welcome_pager);
        welcomeGuideBtn = (Button)findViewById(R.id.welcome_guide_btn);
    }

    class welcomePagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }
    }

}
