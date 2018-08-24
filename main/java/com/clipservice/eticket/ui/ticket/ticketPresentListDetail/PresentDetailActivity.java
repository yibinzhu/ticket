package com.clipservice.eticket.ui.ticket.ticketPresentListDetail;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clipservice.eticket.R;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.controllers.BaseActivity;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.MainTicketListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.TicketDataModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.ui.ticket.ticketPresentList.TicketPresentListActivity;
import com.clipservice.eticket.widget.CustomEditText;
import com.clipservice.eticket.widget.DrawableClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PresentDetailActivity extends BaseActivity implements NumberPickDialog.NumberPickerListener,SeatTypeInfo.BtnIdLisener {

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
    private Button selectNum;
    private String ticketKey;
    private int BtnId;
    private View mView;
    LinearLayout seatType_layout;
    private FragmentManager fragmentManager;
    private int CODE_PICK_CONTACTS = 001;
    private Map<String,ArrayList<HashMap<String,String>>> seatTypeMap;
    private final String TAG = "presentDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_detail);
        init();
    }
    private void init() {
        fragmentManager = getSupportFragmentManager();
        initView();
        initData();
        getDataFromTicketPresentList();
        setSeatInfo();
        setEditorEvent();
        setBtnEvent();
    }

    private void setSeatInfo() {
        //set user ticket seat information
        ArrayList<HashMap<String, String>> list = GlobalValues.seatTypeMap.get(ticketKey);
            for(int i = 0 ;i<list.size();i++ ){
                Log.d(TAG,"list size is "+list.size());
                Log.d(TAG,"list  is "+list);
                int index = 0;
            for(String key:list.get(i).keySet()){
                SeatTypeInfo seatTypeInfo = new SeatTypeInfo(this,fragmentManager);
                String seatType_ = key;
                String count = list.get(i).get(key);
                seatTypeInfo.setSeatType(seatType_);
                seatTypeInfo.setTotalNum(Integer.parseInt(count));
                int seatIndex = Integer.parseInt("600"+index);

                seatTypeInfo.setId(seatIndex);
                seatTypeInfo.setSelectBtnId(seatIndex);
                seatTypeInfo.setPickerTag(String.valueOf(seatIndex));
                seatType_layout.addView(seatTypeInfo);
                index++;
            }


        }

    }

    private void initView() {
        customEditText = (CustomEditText)findViewById(R.id.custom_editor);
        btnSend = (TextView)findViewById(R.id.btn_send);
        selectNum = (Button)findViewById(R.id.select_num);
        totalNum = (TextView)findViewById(R.id.total_num);
        seatType_layout = (LinearLayout)findViewById(R.id.seat_container);
    }

    private void initData() {
        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        enc_memberNum = userInfo.getString("enc_memberNum",null);
        phone = userInfo.getString("phone",null);
        seatTypeMap = GlobalValues.seatTypeMap;
    }
    private void getDataFromTicketPresentList() {
        ticketKey = getIntent().getStringExtra("ticketKey");
        Log.d(TAG,"157/ticket key is "+ticketKey);
    }

    private void setEditorEvent() {

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

    private void setBtnEvent() {

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSendTicket();
            }
        });
    }

    private void checkSendTicket() {
        String phone_sendTo = customEditText.getText().toString();
        if(phone_sendTo.length() == 0){
            Toast.makeText(this, "전화번호 입력하세요", Toast.LENGTH_SHORT).show();
        }else if(phone_sendTo.length() > 11){
            Toast.makeText(this, "정확한 전화번호 입력하세요", Toast.LENGTH_SHORT).show();
        }else{
            int count  = seatType_layout.getChildCount();
            Log.d(TAG,"child count is"+count);
            boolean flag = true;
            HashMap<String,Integer>sendTciketMap = new HashMap<>();
            for(int i = 0;i<count;i++){
                View view = seatType_layout.getChildAt(i);
                if(view instanceof SeatTypeInfo){
                    int total_num = ((SeatTypeInfo) view).getTotalNum();
                    String select_num = ((SeatTypeInfo) view).getSelectNum();
                    String seat_type = ((SeatTypeInfo) view).getSeatType();

                    if(Integer.parseInt(select_num)>total_num){
                        Toast.makeText(this, "보유 티켓매수 초과했습니다", Toast.LENGTH_SHORT).show();
                        flag = false;
                        break;
                    }else {
                        sendTciketMap.put(seat_type,Integer.parseInt(select_num));
                    }
                }
            }
            if(flag){
                formatSendList(sendTciketMap);
            }

        }
    }

    private void formatSendList(HashMap<String,Integer>sendTciketMap) {
        //search some type ticket from ticket list
        ArrayList<TicketDataModel> ticketDataModelArrayList = new ArrayList<>();
        for(String key:sendTciketMap.keySet()){
            int seatType_count = sendTciketMap.get(key);
                for(int i = 0;i<seatType_count;i++){

                    ArrayList<UserTicketListModel> list = GlobalValues.ticketGrpMap.get(ticketKey);
                    for(Iterator iter = list.iterator();iter.hasNext();){
                        UserTicketListModel userTicketListModel =(UserTicketListModel)iter.next();
                        String seatType = userTicketListModel.getSeatType();
                        if (seatType.equals(key)){
                            String enc_ticketNum = userTicketListModel.getEnc_ticketNum();
                            String enc_validKey = userTicketListModel.getEnc_validKey();

                            //add value to tickte data list
                            ticketDataModelArrayList.add(new TicketDataModel(enc_ticketNum,enc_validKey));

                            //delete element from user ticket list if fond some value of seat type
                            iter.remove();
                        }
                    }

                }
        }

        String phone_sendTo = customEditText.getText().toString();
        Log.d(TAG,"phone_sendTo is =>"+phone_sendTo);
        sendTicket(phone_sendTo,ticketDataModelArrayList);
    }

    private void sendTicket(String phone_sendTo,ArrayList<TicketDataModel> ticketData) {
        Log.d(TAG,"208/ticketData is =>"+ticketData);
        Log.d(TAG,"209/ticketData size is =>"+ticketData.size());
        String ticketData_ = super.format_ticketData(ticketData);
        super.sendTicket(ticketData_,enc_memberNum,phone_sendTo);
    }

    @Override
    public void applayNum(int oldNum, int newNum) {
        Log.d(TAG,"137/newNum is"+newNum);
        int resID = getResources().getIdentifier(String.valueOf(BtnId), "id", String.valueOf(this));
        Log.d(TAG,"137/BtnId is"+BtnId);
        Log.d(TAG,"137/resID  is"+resID);
        SeatTypeInfo seatTypeInfo_ = (SeatTypeInfo)findViewById(resID);
        seatTypeInfo_.setSelectNum(newNum);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case (001):
                    ContactInfo userInfo = ContactInfo.getPickContact(this,data.getData());
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

    @Override
    public void getId(int id) {
        Log.d(TAG,"getid is run=>"+id);
        BtnId = id;
    }

    @Override
    public void setPublishingResponse(PublishingResponseModel publishingResponseModel_) {

    }

    @Override
    public void renderMainPageView(ArrayList<MainTicketListModel> mainTicketListModelList, ArrayList<BottomTicketListModel> bottomTicketListModelArrayList) {

    }

    @Override
    public void setRequestTicketingRes(ArrayList<TicketingResItemModel> ticketingResItemModelArrayList) {

    }

    @Override
    public void setEntranceRes(EntranceResModel entranceResModel) {

    }

    //선물 하기 반하값 처리
    @Override
    public void setSendTicketRes(String str) {
        Log.d(TAG,"send ticket response list is=>"+str);
        if(str == null){
            successDialog("선물하기 시폐했습니다",0);
        }else {
            successDialog("선물하기 성공했습니다",1);
        }
    }

    private void successDialog(String msg,int flag_) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PresentDetailActivity.this,R.style.AlertDialogCustom);
        // set title
        alertDialogBuilder.setTitle("알림");
        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent = new Intent(PresentDetailActivity.this,TicketPresentListActivity.class);
                        switch (flag_){
                            case 0:// 실페 처리
                                startActivity(intent);
                                finish();
                                break;
                            case 1://성공 처리
                                startActivity(intent);
                                finish();
                                break;
                        }
                    }
                });
        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialogBuilder.show();
    }
}
