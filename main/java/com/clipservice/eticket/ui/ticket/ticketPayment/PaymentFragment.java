package com.clipservice.eticket.ui.ticket.ticketPayment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.beans.SeatDataModel;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.ui.common.MonthYearPicker;
import com.clipservice.eticket.ui.main.MainActivity;
import com.clipservice.eticket.utils.SharedUtils;
import com.clipservice.eticket.widget.Counter;
import com.clipservice.eticket.widget.TicketBookingInfo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    private View mView;
    private EditText bankNum;
    private Button btnPayment;
    private TextView totalPrice;
    private TextView playTime;
    private TextView ticketTitle;
    private TextView playAddress;
    private LinearLayout bankDate;
    private TextView bankDateText;
    private MonthYearPicker myp;
    private String bankMonth_data;
    private String bankYear_data;
    private String enc_priceGrp;
    private String total_price;
    private String ticketTitle_data ;
    private String playAddress_data ;
    private String playTime_data ;
    private String saleStartdate_year ;
    private String saleStartdate_time ;
    private String saleEnddate_year ;
    private String saleEnddate_time ;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String enc_sequence;
    private String seatData = "";
    private String seatData_format;
    private String bankNum_data;
    private String userName_data;
    private String halbu;
    private String halbu_code;
    private String phone1_3;
    private String phone1;
    private String phone2;
    private String phone3;
    private ACache mCache;
    private Spinner paySpinner;
    private String postImg;
    private String enc_orderNum;
    private String userOrderNum;
    private  String errMsgm;
    private int ticketCounter = 0;
    private ArrayList<Counter> ticketBookingInfo;
    private ArrayList<PrepaidTicketModel> prepaidTicketModels;
    private List<SeatDataModel> seatTypeList;
    private final static String TAG = "paymentFragment";

    private String sId = "0154baf0b7924c38bd0a75e9d87aa843";
    private String enc_storeNum = "h8Fp1IwONBMBAw/EP0iyKg==";
    private String id = "YpyjyegkV96Uzj1N/anKw==";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_payment, container, false);
        initView();
        init();
        return mView;
    }

    private void init() {
        initTicketData();
        setClickEvents();
    }

    private void initTicketData() {

        SharedPreferences userInfo = getActivity().getSharedPreferences(GlobalValues.FILE_NAME,0);
        phone1 = userInfo.getString("phone1",null);
        phone2 = userInfo.getString("phone2",null);
        phone3 = userInfo.getString("phone3",null);

        Bundle bundleObject = getArguments();
        ticketBookingInfo = (ArrayList<Counter>) bundleObject.getSerializable("ticketBookingInfo");
        total_price = getArguments().getString("totalPrice");
        ticketTitle_data = getArguments().getString("ticketTitle");
        playAddress_data = getArguments().getString("playAddress");
        playTime_data = getArguments().getString("playTime");
        saleStartdate_year = getArguments().getString("saleStartdate_year");
        saleStartdate_time = getArguments().getString("saleStartdate_time");
        saleEnddate_year = getArguments().getString("saleEnddate_year");
        saleEnddate_time = getArguments().getString("saleEnddate_time");
        enc_priceGrp = getArguments().getString("enc_priceGrp");
        enc_playNum = getArguments().getString("enc_playNum");
        enc_playSaleNum = getArguments().getString("enc_playSaleNum");
        enc_sequence = getArguments().getString("enc_sequence");
        postImg = getArguments().getString("postImg");
        Log.d(TAG,"total price is =>"+total_price);

        LinearLayout bookingInfoSection = (LinearLayout)mView.findViewById(R.id.booinginfo_section);
        //티켓 예매 정보 동적으로 layout에 사입
        for(int i = 0;i<ticketBookingInfo.size();i++){
            Counter counterObj = ticketBookingInfo.get(i);
            String ticketNum = counterObj.getTicketNum();
            Log.d(TAG,"ticketNum is=>"+ticketNum);
            String seatType = counterObj.getSeatType();
            Log.d(TAG,"seatType is=>"+seatType);

            seatTypeList = new ArrayList<>();
            if(ticketNum!=null){
                if(Integer.parseInt(ticketNum)>0){
                    TicketBookingInfo  bookingInfo = new  TicketBookingInfo (getContext());
                    bookingInfo.setSeatType(seatType);
                    bookingInfo.setTicketNum(ticketNum);
                    bookingInfoSection.addView(bookingInfo);
                    seatData = seatData + seatType+"_"+ticketNum+"|";
                    Log.d(TAG,"159/seatType instert to list");
                    ticketCounter++;
                    seatTypeList.add(new SeatDataModel(seatType,ticketNum));
                }
            }

        }
        Log.d(TAG,"165/seatTypeList size is=>"+ seatTypeList.size());
        formatSeatData(seatData);

        //format price of ticket
        NumberFormat nf = NumberFormat.getInstance();
        String total_price_format = nf.format(Integer.parseInt(total_price));
        totalPrice.setText(total_price_format);
        ticketTitle.setText(ticketTitle_data);
        playTime.setText(saleStartdate_year.replace("-",".")+"  "+saleStartdate_time);
        playAddress.setText(playAddress_data);
    }

    private void setClickEvents() {
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bankNum.getText().length()==0){
                    Toast.makeText(getContext(), "카드번호입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankNum.getText().length()<16){
                    Toast.makeText(getContext(), "정확한입카드번호력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankDateText.getText().length()==0){
                    Toast.makeText(getContext(), "유효기간입력하세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    bankNum_data = bankNum.getText().toString();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                    // set title
                    alertDialogBuilder.setTitle("알림");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("결제하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    ticketOdering();
                                }
                            })
                            .setNegativeButton("취소",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            }
        });

        //날짜선택 처리
        bankDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myp = new MonthYearPicker(getActivity());
                myp.build(new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bankDateText.setText(myp.getSelectedMonthName() + " / " + myp.getSelectedYear());
                        bankMonth_data = String.valueOf(myp.getSelectedMonthName());
                        bankYear_data = String.valueOf(myp.getSelectedYear());
                    }
                }, null);
                myp.show();
            }
        });
    }

    private void formatSeatData(String seatData) {
        seatData_format = seatData.substring(0,seatData.length()-1);
        Log.d(TAG,"seatData_format is=>"+seatData_format);
    }

    //티켓 주문요청 처리
    private void ticketOdering(){
        Log.d(TAG,"enc_playNum is =>"+ enc_playNum);
        Log.d(TAG,"enc_playSaleNum is =>"+enc_playSaleNum);
        Log.d(TAG,"enc_sequence is =>"+enc_sequence);
        Log.d(TAG,"enc_priceGrp is =>"+enc_priceGrp);
        Log.d(TAG,"seatData is =>"+seatData_format);
        Log.d(TAG,"name is =>"+"");
        Log.d(TAG,"phone1 is =>"+phone1);
        Log.d(TAG,"phone2 is =>"+phone2);
        Log.d(TAG,"phone3 is =>"+phone3);
        Log.d(TAG,"price is =>"+String.valueOf(total_price));
        Log.d(TAG,"cardNum is =>"+bankNum_data);
        Log.d(TAG,"cardYY is =>"+bankYear_data.substring(2,4));
        Log.d(TAG,"cardMM is =>"+bankMonth_data);
        HashMap<String,String> map = new HashMap<>();
        map.put("enc_playNum", enc_playNum);
        map.put("enc_playSaleNum", enc_playSaleNum);
        map.put("enc_sequence", enc_sequence);
        map.put("enc_priceGrp", enc_priceGrp);
        map.put("seatData", seatData_format);
        map.put("sId", sId);
        map.put("enc_storeNum",GlobalValues.ENC_STORENUM);
        map.put("id",id);
        map.put("name", "");
        map.put("phone1",phone1 );
        map.put("phone2",phone2 );
        map.put("phone3",phone3 );
        map.put("email1", "" );
        map.put("email2", "" );
        map.put("price", String.valueOf(total_price));
        map.put("cardNum", bankNum_data);
        map.put("cardYY", bankYear_data.substring(2,4));
        map.put("cardMM", bankMonth_data);
        map.put("halbu", "00");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TicketPaymentApi api = retrofit.create(TicketPaymentApi.class);
        Call<PaymentResponseModel> call = api.ticketOdering(map);
        call.enqueue(new Callback<PaymentResponseModel>() {
            @Override
            public void onResponse(Call<PaymentResponseModel> call,retrofit2.Response<PaymentResponseModel> response) {
                PaymentResponseModel responseModel = response.body();
                enc_orderNum = responseModel.getEnc_orderNum();
                userOrderNum = responseModel.getUserOrderNum();
                errMsgm =responseModel.getErrMsg();

                Log.d(TAG,"PaymentResponseModel=>"+responseModel);
                setOderingResponse(responseModel);
            }
            @Override
            public void onFailure(Call<PaymentResponseModel> call, Throwable t) {
                Log.e(TAG,"PaymentResponseModel failed"+t);
            }
        });
    }

    private void setOderingResponse(PaymentResponseModel responseModel) {
            String result = responseModel.getResult();
            if (result.equals("00")){
                successDialog();
                SharedUtils.putStorageBoolean(getActivity(),false);//make value false so that insert data to database again when open the main activity
                SharedUtils.putPublishBoolean(getActivity(),true);//make value false so that republish tickets
//                insertToCache();
//                insertToSharedPreferences();
//                insertToDB();
            }else{
                switch (result){
                    case "10":
                        Toast.makeText(getContext(), "결제금액 검증값 오류", Toast.LENGTH_LONG).show();
                        break;
                    case "20":
                        Toast.makeText(getContext(), "결제 실패", Toast.LENGTH_LONG).show();
                        break;
                    case "21":
                        Toast.makeText(getContext(), "결제 실패 - 결제API호출 오류", Toast.LENGTH_LONG).show();
                        break;
                }
            }
    }

    private void successDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        // set title
        alertDialogBuilder.setTitle("알림");
        // set dialog message
        alertDialogBuilder
                .setMessage("결제성공했습니다")
                .setCancelable(false)
                .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    private void initView() {
        bankNum = (EditText)mView.findViewById(R.id.bank_num);
        btnPayment = (Button)mView.findViewById(R.id.btn_payment);
        totalPrice = (TextView)mView.findViewById(R.id.price_total);
        bankDate = (LinearLayout)mView.findViewById(R.id.bank_date);
        bankDateText = (TextView)mView.findViewById(R.id.bank_date_text);
        playTime = (TextView)mView.findViewById(R.id.play_time);
        ticketTitle = (TextView)mView.findViewById(R.id.ticket_title);
        playAddress = (TextView)mView.findViewById(R.id.play_address);
    }

}
