package com.clipservice.eticket.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.ui.main.MainActivity;
import com.clipservice.eticket.utils.SharedUtils;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.firebase.iid.FirebaseInstanceId;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clip-771 on 2018-02-27.
 */

public class WelcomeStart extends Activity{
    private String phone_id;
    private String phone_number;
    private String pushKey;
    private String phone1;
    private String phone2;
    private String phone3;
    private String encrypt_mackineKey;
    private ACache mCache;
    private Boolean isCheckVersion = false;
    private final static String TAG = "welcomeStrat";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_start);
        mCache = ACache.get(this);
        init();
    }

    /**
    * app update library :https://github.com/javiersantos/AppUpdater
     * json response format :
     * {
     "latestVersion": "1.2.2",
     "latestVersionCode": 10,
     "url": "https://github.com/javiersantos/AppUpdater/releases",
     "releaseNotes": [
     "- First evolution",
     "- Second evolution",
     "- Bug fixes"
     ]
     }
     *
    * */
    private void checkVerison() {
        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater
                .setDisplay(Display.DIALOG)
                .setCancelable(false)
                .setUpdateFrom(UpdateFrom.JSON)
                .setUpdateJSON("https://github.com/javiersantos/AppUpdater/wiki/UpdateFrom.JSON")//check version api address
                .setTitleOnUpdateAvailable("Update available")
                .setContentOnUpdateAvailable("Check out the latest version available of my app!")
                .setTitleOnUpdateNotAvailable("Update not available")
                .setContentOnUpdateNotAvailable("No update available. Check for updates again later!")
                .setButtonUpdate("업데이트")
                .setButtonUpdateClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(WelcomeStart.this, "업데이트 페이지 가겠습니다", Toast.LENGTH_SHORT).show();
                    }
                })
	            .setButtonDismiss("나중에")
                .setButtonDismissClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(WelcomeStart.this, "취소 버튼 누렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
	            .setButtonDoNotShowAgain("업데이트 안함")
                .setButtonDoNotShowAgainClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        appUpdater.stop();
                    }
                })
	            .setIcon(R.drawable.ic_action_plus) // Notification icon
                .setCancelable(true); // Dialog could not be dismissable

        appUpdater.start();

    }
    private void getPhoneInfo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestReadPhoneStatePermission();
            return;
        }
        phone_id = tm.getDeviceId();
        phone_number = tm.getLine1Number();
        pushKey = FirebaseInstanceId.getInstance().getToken();

        if(phone_number != null) {
            phone1 = "0"+phone_number.substring(3,5);
            phone2 = phone_number.substring(5,9);
            phone3 = phone_number.substring(9,13);

            mCache.put("phone1",phone1);
            mCache.put("phone2",phone2);
            mCache.put("phone3",phone3);
        } else {
            Toast.makeText(this, "기기정보 읽을수 없습니다", Toast.LENGTH_SHORT).show();
            Log.i("welcomeGuideAct","phone num is null");
        }

        String mackineKey = phone1+phone2+phone3+"|"+phone_id;
        encrypt_mackineKey = encryptSHA512(mackineKey);
        Log.i("welcomeGuideAct","phone_id is=>"+phone_id);
        Log.i("welcomeGuideAct","phone num is=>"+phone_number);
        Log.i("welcomeGuideAct","phone1 num is=>"+phone1);
        Log.i("welcomeGuideAct","phone2 num is=>"+phone2);
        Log.i("welcomeGuideAct","phone3 num is=>"+phone3);
        Log.i("welcomeGuideAct"," encrypt_mackineKey is=>"+encrypt_mackineKey);
        Log.i("welcomeGuideAct"," pushKey is=>"+pushKey);
        Thread getAuthKey = new Thread(){
            public void run(){
//                getAuthKey(phone1,phone2,phone3,encrypt_mackineKey,pushKey);
            }
        };
        getAuthKey.start();
    }

    private void getAuthKey(String phone1,String phone2,String phone3,String encrypt_mackineKey,String pushKey) {
        String URL_GETAUTHKEY = Const.API_SETUSER;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL_GETAUTHKEY,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.e("welcomGuideAct","GETAUTHKEY response is=>"+response);
                        setAuthKey(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
//                params.put("machineKey", encrypt_mackineKey);
//                params.put("phone1", "0"+phone1);
//                params.put("phone2", phone2);
//                params.put("phone3", phone3);
//                params.put("pushKey", pushKey);
                params.put("machineKey", "HV2ZXTrPDKKbRXxxCcuTH/AqHGYSQ0QXPX4kwpfOa3drT9/LIhzS8JyAHRwiffe/oOVwnKOUVNjXWavgaXTXUg==");
                params.put("phone1", "010");
                params.put("phone2","6792");
                params.put("phone3", "2727");
                params.put("pushKey", pushKey);
                params.put("os","android");
                params.put("authKey","dowksj");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void setAuthKey(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            String enc_authKey = obj.getString("enc_authKey");
            String enc_memberNum = obj.getString("enc_memberNum");
            mCache.put("enc_authKey ",enc_authKey );
            mCache.put("enc_memberNum",enc_memberNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final static String encryptSHA512(String target) {
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(target.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : sh.digest()) sb.append(Integer.toHexString(0xff & b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(this)
                    .setTitle("Permission Request")
                    .setMessage("")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(WelcomeStart.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    0);
                        }
                    })
                    .setIcon(R.drawable.back1)
                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    0);
        }
    }

    private void init(){
        if(SharedUtils.getWelcomeBoolean(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
        }else {
//            startActivity(new Intent(WelcomeStart.this,WelcomeGuideAct.class));
            SharedUtils.putWelcomeBoolean(getBaseContext(),true);
            InitApp initApp = new InitApp();
            initApp.execute();

        }
    }

    class InitApp extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            getPhoneInfo();
            checkVerison();
            initPermissions();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(WelcomeStart.this, "init finished", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(WelcomeStart.this, WelcomeGuideAct.class));
            finish();
        }
    }
    private void initPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(WelcomeStart.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(WelcomeStart.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

}
