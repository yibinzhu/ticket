package com.clipservice.eticket.ui.ticket.requestTiceketing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RequestTicketingActivity extends AppCompatActivity {
    private EditText ticketingNum;
    private Button btnRequest;
    private String enc_authKey;
    private ACache mCache;
    private static final String TAG="RequestTicketingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ticketing);
        initView();
        init();
    }

    private void init() {
        mCache = ACache.get(this);
        enc_authKey = mCache.getAsString("enc_authKey");

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ticketingNum.getText().length()!=0){
                    String ticketing_num = String.valueOf(ticketingNum.getText());
                    requestTicketing(ticketing_num);
                }

            }
        });
    }

    private void requestTicketing(String ticketing_num) {
        String url = Const.API_ENTRANCE+"?enc_authKey="+enc_authKey;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RequestTicketingActivity.this, "response is =>" + response
                                , Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "requestTicketing response is ->" + response);
                        // response
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
                params.put("enc_ticketNums", "['ABg9MTMnZHPyY/vUSK02Sw==','L+FHpqD105fMbh4f4ZFIAw==','AMrEr9+TjRBExC5KzPX/rQ==']");
                params.put("enc_memberNum", "PVBgTmWct2TnSKpicvHrCw==");
                params.put("authKey", "dowksj");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void  initView() {
        ticketingNum = (EditText)findViewById(R.id.ticketing_num);
        btnRequest = (Button)findViewById(R.id.btn_request);
    }

}
