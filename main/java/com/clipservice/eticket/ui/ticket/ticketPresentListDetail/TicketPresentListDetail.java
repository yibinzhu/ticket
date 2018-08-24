package com.clipservice.eticket.ui.ticket.ticketPresentListDetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.widget.CustomEditText;
import com.clipservice.eticket.widget.DrawableClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clip-771 on 2018-03-09.
 */

public class TicketPresentListDetail extends Fragment {
    private String playTitle;
    private String sequenceText;
    private String seatType;
    private String discountNum;
    private String discountName;
    private String publishDate;
    private String statusName;
    private String ticketImg;
    private String phone;
    private String enc_memberNum;
    private CustomEditText customEditText;
    private TextView btnSend;
    private TextView totalNum;
    private Button sendNum;
    private View mView;
    private int CODE_PICK_CONTACTS = 001;
    private String TAG = "TicketPresentListDetail";
    private Map<String,ArrayList<HashMap<String,String>>> seatTypeMap;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ticket_present_list_detail, container,false);
        init();
        return mView;
    }

    private void init() {
        initView();
        initData();
        getDataFromTicketPresentList();
        setEditorEvent();
        setBtnEvent();
    }

    private void initData() {
        final GlobalValues globalValues = (GlobalValues)getActivity().getApplication();
        seatTypeMap = globalValues.getSeatTypeMap();
        phone = globalValues.getPhone();
        enc_memberNum =globalValues.getEnc_memberNum();
    }

    private void setBtnEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total_num = String.valueOf(totalNum.getText());
                String send_num = String.valueOf(sendNum.getText());
                if(Integer.parseInt(send_num)>Integer.parseInt(send_num)){

                }else{
                    Toast.makeText(getActivity(), "총매수 초과됩니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setEditorEvent() {
        Button btnTest = (Button)mView.findViewById(R.id.btn_test);
        btnTest.setOnClickListener((View v)->{
//            NumberPickDialog numberPickDialog = new NumberPickDialog();
//            numberPickDialog.onCreateDialog();

        });

        customEditText.setDrawableClickListener(new DrawableClickListener() {

            public void onClick(DrawablePosition target) {
                switch (target) {
                    case RIGHT:
                        Log.d(TAG,"icon is clicked");
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(intent, CODE_PICK_CONTACTS);
                        break;

                    default:
                        break;
                }
            }

        });
    }

    private void initView() {
        customEditText = (CustomEditText)mView.findViewById(R.id.custom_editor);
        btnSend = (TextView)mView.findViewById(R.id.btn_send);

        totalNum = (TextView)mView.findViewById(R.id.total_num);
        LinearLayout seatType_layout = (LinearLayout)mView.findViewById(R.id.seat_container);
    }

    private void getDataFromTicketPresentList() {
        Bundle bundleObject = getArguments();
        String ticketKey = bundleObject.getString("ticketKey");
        Log.d(TAG,"157/ticket key is "+ticketKey);

    }

    private void SendTicket(String enc_ticketNum, String enc_validKey, String enc_memberNum, String phoneNum) {
        String url = Const.API_SENDTICKET;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG,"TicketPresentListDetail send ticket response is "+ response);
                        Log.i(TAG,"enc_memberNum is "+enc_memberNum);
                        if(response == "SUCCESS") {
                            Toast.makeText(getActivity(), "선물 하기 성공 했습니다", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "선물 하기 실페 했습니다", Toast.LENGTH_SHORT).show();
                        }
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
                params.put("enc_ticketNum", enc_ticketNum);
                params.put("enc_validKey", enc_validKey);
                params.put("enc_memberNum", enc_memberNum);
                params.put("phone", phoneNum);
                return params;
            }
        };
        queue.add(postRequest);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case (001):
                    ContactInfo userInfo = ContactInfo.getPickContact(getActivity(),data.getData());
                    String phone  = userInfo.getPhone();
                    setPhoneNum(userInfo);
                    break;
            }
        }
    }

    private void setPhoneNum(ContactInfo userInfo) {
        String phoneNum = userInfo.getPhone();
        customEditText.setText(phoneNum, TextView.BufferType.EDITABLE);
    }

}
