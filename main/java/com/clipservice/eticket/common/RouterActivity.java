package com.clipservice.eticket.common;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clip-771 on 2018-02-28.
 */

public class RouterActivity extends AppCompatActivity {
    private String enc_ticketNum_url_encode;
    private String enc_validKey_url_encode;
    private String enc_memberNum;
    private final static String TAG = "Routeractivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        getParameter();
    }

    private void getParameter() {
        Uri uri = getIntent().getData();
        if (uri != null) {
            String url = uri.toString();
            Log.e("RouterActivity", "Url : " + url);
            String scheme = uri.getScheme();
            Log.e("RouterActivity", "Scheme : " + scheme);
            String host = uri.getHost();
            Log.e("RouterActivity", "Host : " + host);
            String query = uri.getQuery();
            Log.e("RouterActivity", "Query : " + query);
            String enc_ticketNum = uri.getQueryParameter("enc_ticketNum");
            Log.e("RouterActivity", "enc_ticketNum: " +enc_ticketNum);
            String enc_validKey = uri.getQueryParameter("enc_validKey");
            Log.e("RouterActivity", "enc_validKey : " + enc_validKey);
            RequestPublishing(enc_ticketNum,enc_validKey);
        }
    }

    private void RequestPublishing(String enc_ticketNum,String enc_validKey) {
        String enc_memberNum1 = "PVBgTmWct2TnSKpicvHrCw%3d%3d";
        String enc_ticketNum1="EsCh%2BBCh6ogDRTgIpDXyBQ%3D%3D";
        String enc_validKey1="EsCh%2BBCh6ogDRTgIpDXyBQ%3D%3D";

        try {
            enc_ticketNum_url_encode = URLEncoder.encode(enc_ticketNum, "utf-8");
            enc_validKey_url_encode = URLEncoder.encode(enc_validKey, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("RouterActivity", "RequestPublishing enc_memberNum : " + enc_memberNum1);
        Log.e("RouterActivity", "RequestPublishing enc_ticketNum_url_encode : " +enc_ticketNum1);
        Log.e("RouterActivity", "RequestPublishing enc_validKey_url_encode : " + enc_validKey1);

        String url = "http://api-test.cloudticket.co.kr/Ticket/App/RequestPublishing";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RouterActivity.this, "response is =>" + response
                                , Toast.LENGTH_SHORT).show();
                        Log.e("RouterActivity", "RequestPublishing response is : " + response);
                        // response
                        if (response != null || response.length() > 0) {
                            if(response == "SUCCESS"){
//                                GetUserTicketList();
                            }else {
                                Toast.makeText(RouterActivity.this, "티켓발행실페"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("RouterActivity", "resonse is null");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d(TAG, String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("enc_ticketNum", "EsCh%2BBCh6ogDRTgIpDXyBQ%3D%3D");
                params.put("enc_validKey", "EsCh%2BBCh6ogDRTgIpDXyBQ%3D%3D");
                params.put("enc_memberNum", "PVBgTmWct2TnSKpicvHrCw%3d%3d");
                params.put("authKey", "dowksj");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void GetUserTicketList() {
        String url = "https://api-test.cloudticket.co.kr/Ticket/App/GetUserTicketList";
        final ProgressDialog asyncDialog = new ProgressDialog(getBaseContext());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("티켓을 가져오고 있습니다...");
        asyncDialog.show();
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RouterActivity","RouterActivity response is"+response);

                        asyncDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("main","请求失败"+error);
                Toast.makeText(getBaseContext(), "보유중인 티켓 호출 실페", Toast.LENGTH_SHORT).show();
                asyncDialog.dismiss();
            }
        });
        queue.add(stringRequest);
    }

}
