package com.clipservice.eticket.ui.ticket.ticketPublishList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clip-771 on 2018-03-12.
 */

public class TicketPublishListActivity extends Activity{
    private ListView publishList;
    private ArrayList itemsArrayList;
    private String item_enc_ticketNum;
    private String item_enc_validKey;
    private ACache mCache;
    private String enc_memberNum;
    private final static String TAG = "PublishListActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_publish_list);
        initView();
    }

    private void initView() {
        publishList = (ListView)findViewById(R.id.publish_list);
        getPublishReadyList();
        init();
    }
    public void init(){
        mCache = ACache.get(this);
        enc_memberNum= mCache.getAsString("enc_memberNum");
        if(enc_memberNum!=null){
            Log.d(TAG,"get enc_memberNum from cache is:" + enc_memberNum);
        }else {
            Log.d(TAG,"get enc_memberNum from cache is null");
        }
        publishList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ticketInfo = new Gson().toJson(itemsArrayList.get(i));
                Log.d(TAG,"ticketInfo is:"+ ticketInfo );
                    try {
                        JSONObject item  = new JSONObject(ticketInfo);
                        item_enc_ticketNum = item.getString("enc_ticketNum");
                        item_enc_validKey = item.getString("enc_validKey");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TicketPublishListActivity.this);
                alertDialogBuilder.setTitle("티켓 수령");

                alertDialogBuilder
                        .setMessage("티켓 수령 하시겠스니까?")
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 프로그램을 종료한다
                                        requestPublishing(item_enc_ticketNum,item_enc_validKey);
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void requestPublishing(String enc_ticketNum,String enc_validKey) {
        String URL = Const.API_REQUESTPUBLISHING;
        Log.d(TAG, "requestPublishing url is ->" + URL);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            Toast.makeText(TicketPublishListActivity.this, "티켓리스트 가겠습니다"
                                    , Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "requestPublishing response is ->" + response);
                        }else{
                            Log.d(TAG, "requestPublishing response is null");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(TicketPublishListActivity.this, "티켓 발행 시페"
                                , Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"http request error:"+error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("enc_ticketNum", enc_ticketNum);
                params.put("enc_validKey", enc_validKey);
                params.put("enc_memberNum", enc_memberNum);
                Log.d(TAG,"http request enc_ticketNum:"+enc_ticketNum);
                Log.d(TAG,"http request enc_validKey:"+enc_validKey);
                Log.d(TAG,"http request enc_memberNum:"+enc_memberNum);

//                params.put("enc_ticketNum", "EsCh+BCh6ogDRTgIpDXyBQ==");
//                params.put("enc_validKey", "EsCh+BCh6ogDRTgIpDXyBQ==");
//                params.put("enc_memberNum", "PVBgTmWct2TnSKpicvHrCw==");
                params.put("authKey", Const.AUTHKEY);
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void getPublishReadyList() {
        String enc_memberNum = "PVBgTmWct2TnSKpicvHrCw%3D%3D";
        String phone = "01025877871";
        String URL = Const.API_GETPUBLISHREADYLIST+"?authKey=dowksj&enc_memberNum="+enc_memberNum+"&phone="+phone;

        final ProgressDialog asyncDialog = new ProgressDialog(this);
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("Loading...");
        asyncDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,"getPublishReadyList response is"+response);
                        setTicketData(response);
                        asyncDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"http request error:"+error);
                Toast.makeText(TicketPublishListActivity.this, "인터넷을 확인 하세요!", Toast.LENGTH_SHORT).show();
                asyncDialog.dismiss();
            }
        });
        queue.add(stringRequest);
    }

    private void setTicketData(String response) {
        itemsArrayList =new ArrayList< com.clipservice.eticket.ui.ticket.ticketPublishList.TicketPublishListModel>();
        try {
            JSONArray item  = new JSONArray(response);
            for(int i= 0;i<item.length();i++){
                if(item!=null){
                    String item_enc_ticketNum = item.getJSONObject(i).getString("enc_ticketNum");
                    String item_enc_validKey = item.getJSONObject(i).getString("enc_validKey");
                    String item_isGift = item.getJSONObject(i).getString("isGift");
                    String item_playTitle = item.getJSONObject(i).getString("playTitle");
                    String item_sequenceText = item.getJSONObject(i).getString("sequenceText");
                    String item_seatType = item.getJSONObject(i).getString("seatType");

                    itemsArrayList.add(new com.clipservice.eticket.ui.ticket.ticketPublishList.TicketPublishListModel(item_enc_ticketNum,item_enc_validKey,item_isGift,item_playTitle,item_sequenceText,item_seatType));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"item list is "+itemsArrayList);
        TicketPublishListAdapter adapter = new  TicketPublishListAdapter(this,itemsArrayList);

        publishList.setAdapter(adapter);
    }

}
