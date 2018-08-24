package com.clipservice.eticket.ui.ticket.ticketBookingDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clipservice.eticket.R;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.models.BookingListModel;
import com.clipservice.eticket.ui.ticket.ticketPayment.PaymentFragment;
import com.clipservice.eticket.widget.Counter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingdetailFragment extends Fragment {
    private LinearLayout counterSection;
    private List<SequenceListModel> sequenceArrayList;
    private List<PlaySaleInfoModel> playSaleInfoModelList;
    private ArrayList<Counter> counterList;
    private String enc_priceGrp;
    private int totalPrice;
    private String playNum;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String playTitle;
    private String theaterGrpName;
    private String genres;
    private String contentData;
    private String saleStartdate;
    private String saleEnddate;
    private String theaterName;
    private String lat;
    private String lng;
    private String postImg;
    private String encoded_nc_playNum;
    private String encoded_enc_playSaleNum;
    private String saleStartdate_year;
    private String saleStartdate_time;
    private String saleEnddate_year;
    private String saleEnddate_time;
    private String enc_sequence;
    private String playdatetime;
    private Button btnBooking;
    private ImageView btnAbout;
    private ImageView btnPrice;
    private  ImageView bgPostImg;
    private TextView ticketTitle;
    private TextView playAddress;
    private TextView ticketPrice;
    private TextView playTime;
    private View mView;
    private ExpandableRelativeLayout expandableLayoutAbout;
    private Fragment paymentFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private static final String TAG = "bookingdetailfragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_bookingdetail, container, false);
        initView();
        init();
        return mView;
    }

    private void init() {
        initTicketInfo();
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice = 0;
                counterList = new ArrayList();
                for(int k = 0;k<playSaleInfoModelList.size();k++){
                    String counterId = "500"+k;
                    //get Counter view id from for loop
                    int resID = getResources().getIdentifier(counterId, "id", String.valueOf(getActivity()));
                    Counter counterCls = (Counter) mView.findViewById(resID);
                    counterList.add(counterCls);
                    totalPrice = totalPrice + counterCls.getTotal();
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("ticketBookingInfo",counterList);
                bundle.putString("totalPrice", String.valueOf(totalPrice));
                bundle.putString("ticketTitle",playTitle);
                bundle.putString("playAddress",theaterGrpName);
                bundle.putString("saleStartdate_year",saleStartdate_year);
                bundle.putString("saleStartdate_time",saleStartdate_time);
                bundle.putString("saleEnddate_year",saleEnddate_year);
                bundle.putString("saleEnddate_time",saleEnddate_time);
                bundle.putString("enc_playNum",enc_playNum);
                bundle.putString("enc_playSaleNum",enc_playSaleNum);
                bundle.putString("enc_sequence", enc_sequence);
                bundle.putString("enc_priceGrp", enc_priceGrp);
                bundle.putString("postImg", postImg);
                // navigate to next page
                if (totalPrice > 0) {
                    paymentFragment = new PaymentFragment();
                    paymentFragment.setArguments(bundle);
                    fragmentManager = getFragmentManager();
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container,paymentFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    Toast.makeText(getContext(), "티켓을 선택하세요!", Toast.LENGTH_SHORT).show();
                }
            }
            });

    }

    private void imageRatateAni(int fromDegrees, int toDegrees, ImageView imageView) {
        RotateAnimation rotateAnimation=new RotateAnimation(
                fromDegrees,  toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        imageView.startAnimation(rotateAnimation);
    }

    private void initView() {
        counterSection = (LinearLayout)mView.findViewById(R.id.counter_section);
        ticketTitle = (TextView)mView.findViewById(R.id.ticket_title);
        playAddress = (TextView)mView.findViewById(R.id.ticket_address);
        playTime = (TextView)mView.findViewById(R.id.play_time);
        ticketPrice = (TextView)mView.findViewById(R.id.ticket_price);
        bgPostImg = (ImageView)mView.findViewById(R.id.ticket_postImg);
        btnBooking = (Button)mView. findViewById(R.id.btn_booking);
    }

    private void initTicketInfo() {
//        String ticketInfoJson = getArguments().getString("infoFromMain");
        ACache mCache = ACache.get(getActivity());
        String ticketInfoJson  = mCache.getAsString("salePlayInfo");
        BookingListModel ticketInfo = new Gson().fromJson(ticketInfoJson,BookingListModel.class);
        playNum = ticketInfo.getPlayNum();
        enc_playNum = ticketInfo.getEnc_playNum();
        enc_playSaleNum = ticketInfo.getEnc_playSaleNum();
        playTitle = ticketInfo.getPlayTitle();
        theaterGrpName = ticketInfo.getTheaterGrpName();
        genres = ticketInfo.getGenres();
        contentData = ticketInfo.getContentData();
        saleStartdate = ticketInfo.getSaleStartdate();
        saleEnddate = ticketInfo.getSaleEnddate();
        theaterName = ticketInfo.getTheaterName();
        lat = ticketInfo.getLat();
        lng = ticketInfo.getLng();
        postImg = ticketInfo.getPostImg();
        int index = saleStartdate.indexOf("T");
        saleStartdate_year = saleStartdate.substring(0,index);
        saleStartdate_time = saleStartdate.substring(index+1);
        saleEnddate_year = saleStartdate.substring(0,index-1);
        saleEnddate_time = saleStartdate.substring(index+1);
        getSequenceList(enc_playNum,enc_playSaleNum);
        Log.d(TAG,"ticket info  playNum->"+ playNum );
        Log.d(TAG,"ticket info  enc_playNum ->"+ enc_playNum );
        Log.d(TAG,"ticket info  enc_playSaleNum->"+ enc_playSaleNum);
        Log.d(TAG,"ticket info playTitle->"+ playTitle);
        Log.d(TAG,"ticket info psaleStartdate_year->"+ saleStartdate_year);

        ticketTitle.setText(playTitle);
        playAddress.setText(theaterName);
        playTime.setText(playdatetime);
        Picasso.with(getContext())
                .load(postImg)
                .placeholder(R.drawable.ic_image_background)
                .into(bgPostImg);
    }

    private void getSequenceList(String enc_playNum,String enc_playSaleNum){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookingDetailApi bookingDetailApi = retrofit.create(BookingDetailApi.class);
        Call<List<SequenceListModel>> call = bookingDetailApi.getSequenceList(Const.AUTHKEY,enc_playNum,enc_playSaleNum);
        call.enqueue(new Callback<List<SequenceListModel>>() {
            @Override
            public void onResponse(Call<List<SequenceListModel>> call, retrofit2.Response<List<SequenceListModel>> response) {
                sequenceArrayList = response.body();
                Log.d(TAG,"sequenceArrayList=>"+sequenceArrayList);
                setSequenceListData(sequenceArrayList);
            }

            @Override
            public void onFailure(Call<List<SequenceListModel>> call, Throwable t) {

            }
        });
    }

    private void setSequenceListData(List<SequenceListModel> SequenceArrayList) {
        int sequenceListSize = SequenceArrayList.size();
        if(sequenceListSize <= 1){
            if(sequenceListSize != 0 ){
                playdatetime =  SequenceArrayList.get(0).getPlayDateTime();
              enc_sequence = SequenceArrayList.get(0).getEnc_sequence();
                Log.d(TAG," enc_sequence is"+enc_sequence);
              getPlaySaleInfo(enc_sequence);
            }else{
                Log.e(TAG,"sequencelist size is null");
            }
        }else {
            Log.e(TAG,"sequencelist size is =0");
        }
    }

    private void getPlaySaleInfo(String enc_sequence){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookingDetailApi bookingDetailApi = retrofit.create(BookingDetailApi.class);
        Call<Object> call = bookingDetailApi.getPlaySaleInfo(Const.AUTHKEY,enc_playNum,enc_playSaleNum,enc_sequence);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                String playSaleInfo = new Gson().toJson(response.body());
                setPlaySaleInfo(playSaleInfo);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void setPlaySaleInfo(String response) {
        playSaleInfoModelList = new ArrayList<PlaySaleInfoModel>();
        try {
            JSONObject SeatType = new JSONObject(response);
            JSONArray item = SeatType.getJSONArray("seatTypes");
            for(int i= 0; i<item.length(); i++){
                if(item != null){
                    String item_seatType = item.getJSONObject(i).getString("seatType");
                    int item_sort = item.getJSONObject(i).getInt("sort");
                    int item_ticket_price = item.getJSONObject(i).getInt("price");
                    enc_priceGrp = item.getJSONObject(i).getString("enc_priceGrp");
                    Log.d(TAG,"item_enc_priceGrp is=>"+enc_priceGrp);
                    playSaleInfoModelList.add(new PlaySaleInfoModel(item_seatType, item_sort, item_ticket_price,enc_priceGrp));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //set ticket selling counter
        for(int j = 0;j<playSaleInfoModelList.size();j++){
            int counterName = Integer.parseInt("500"+j);
            int ticketPrice = playSaleInfoModelList.get(j).getPrice();
            String seatType = playSaleInfoModelList.get(j).getSeatType();
            Counter counter = new Counter(getContext(),ticketPrice,seatType);
            counter.setPrice(ticketPrice);
            counter.setSeatType(seatType);
            counter.setId(counterName);
            counter.setPlayTitle(playTitle);
            int id  = counter.getId();
            counterSection.addView(counter);
        }
    }


}
