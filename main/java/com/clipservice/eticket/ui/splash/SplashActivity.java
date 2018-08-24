package com.clipservice.eticket.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.clipservice.eticket.R;

import com.clipservice.eticket.ui.main.MainActivity;
import com.clipservice.eticket.ui.welcomeGuide.WelcomeGuide;
import com.clipservice.eticket.utils.SharedUtils;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private final static String TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer = new Timer();
        timer.schedule(new Task(),3000);
    }

    class Task extends TimerTask {
        @Override
        public void run() {
//             if(SharedUtils.getWelcomeBoolean(getBaseContext())) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this, WelcomeGuide.class));
//                    SharedUtils.putWelcomeBoolean(getBaseContext(),true);
//                }
                finish();
        }
    }


}
