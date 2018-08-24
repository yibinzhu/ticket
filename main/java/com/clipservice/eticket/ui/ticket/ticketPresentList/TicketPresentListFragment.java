package com.clipservice.eticket.ui.ticket.ticketPresentList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.common.APIs;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.common.database.DatabaseHelper;
import com.clipservice.eticket.common.database.DbConfig;
import com.clipservice.eticket.common.database.DbManger;
import com.clipservice.eticket.controllers.BaseActivity;
import com.clipservice.eticket.models.PresentTicketModel;
import com.clipservice.eticket.models.PublishReadyListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.ui.main.MainActivity;

import com.clipservice.eticket.ui.ticket.ticketPresentListDetail.PresentDetailActivity;
import com.clipservice.eticket.widget.Counter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketPresentListFragment extends Fragment {
    private View mView;
    private ListView ticketPresentList;
    private Intent intent;
    private String enc_memberNum;
    private String phone;
    private String reqDatasString="";
    private List<ReqDatasModel> reqDataList;
    private List<PublishReadyListModel> publishReadyListModelList;
    private List<UserTicketListModel> userTicketListModelList;
    private List<Counter> prepaidList;
    private ProgressDialog asyncDialog;
    private DatabaseHelper helper;
    private ACache mCache;
    private List<PrepaidTicketModel> prepaidTicketModels;

    private Map<String,ArrayList<UserTicketListModel>> ticketGrpMap;
    private List<PresentTicketModel>presentTicketModelList;

    private final static String TAG = "PresentListFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ticket_present_list,container,false);
        showDialogBar();
        init();
        return mView;
    }

    private void init() {
        initView();
        final GlobalValues globalValues = (GlobalValues)getActivity().getApplication();
        ticketGrpMap = globalValues.getTicketGrpMap();
        Log.d(TAG,"ticketGrpMap is=>"+ticketGrpMap);

        presentTicketModelList = formatPresentList(ticketGrpMap);
        setListData(presentTicketModelList);
    }

    private List<PresentTicketModel> formatPresentList(Map<String,ArrayList<UserTicketListModel>> ticketGrpMap) {
        ArrayList<PresentTicketModel> presentTicketModels = new ArrayList<>();
        for(String key:ticketGrpMap.keySet()){
            presentTicketModels.add(new PresentTicketModel(ticketGrpMap.get(key).get(0).getPlayTitle(),ticketGrpMap.get(key).get(0).getPublishDate(),ticketGrpMap.get(key).get(0).getSequenceText(),String.valueOf(ticketGrpMap.get(key).size()),ticketGrpMap.get(key).get(0).getStatus(),key,ticketGrpMap.get(key).get(0).getTicketImg()));
        }
        return presentTicketModels;
    }

    private void setListData(List<PresentTicketModel>presentTicketModelList) {
        TicketPresentListAdapter adapter = new TicketPresentListAdapter(getActivity(),presentTicketModelList);
        Log.d(TAG,"presentTicketModelList is =>"+presentTicketModelList);
        ticketPresentList.setAdapter(adapter);
        asyncDialog.dismiss();
        ticketPresentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bundle bundle = new Bundle();
////                bundle.putSerializable("ticketInfo",new Gson().toJson(presentTicketModelList.get(i)));
//                bundle.putString("ticketKey",presentTicketModelList.get(i).getKey());
//                TicketPresentListDetail ticketPresentListDetail = new TicketPresentListDetail();
//                ticketPresentListDetail.setArguments(bundle);
//
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                fragmentManager = getFragmentManager();
//                ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_container,ticketPresentListDetail);
//                ft.addToBackStack(null);
//                ft.commit();
                Intent intent = new Intent(getActivity(),PresentDetailActivity.class);
                intent.putExtra("ticketKey",presentTicketModelList.get(i).getKey());
                startActivity(intent);
            }
        });
    }

    private void showNotice() {
        LinearLayout noTicketNotice = (LinearLayout)mView.findViewById(R.id.no_ticket_notice);
        noTicketNotice.setVisibility(View.VISIBLE);
        setBookingBtn();
    }

    private void setBookingBtn() {
        Button btnBooking = (Button)mView.findViewById(R.id.btn_booking);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void requestPublishing(String reqDatas) {
        HashMap<String,String> map = new HashMap<>();
        map.put("authKey",Const.AUTHKEY);
        map.put("enc_memberNum",enc_memberNum);
        map.put("reqData",reqDatas);
        Log.d(TAG,"reqData is=>"+reqDatas);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<PublishingResponseModel> call = api.requestPublishing(map);
        call.enqueue(new Callback<PublishingResponseModel>() {
            @Override
            public void onResponse(Call<PublishingResponseModel> call, retrofit2.Response<PublishingResponseModel> response) {
                GlobalValues.publishingResponseModel = response.body();
                Log.d(TAG,"티켓 발행 요청/publishingResponseMode lList is =>"+GlobalValues.publishingResponseModel);
            }
            @Override
            public void onFailure(Call<PublishingResponseModel> call, Throwable t) {
                Log.e(TAG,"티켓 발행 요청/requestPublishing failed"+t);
            }
        });
    }

    private void initData() {
        SharedPreferences ct_storage = getActivity().getSharedPreferences(Const.SP_NAME,0);
        enc_memberNum = ct_storage.getString("enc_memberNum","null");
        phone = ct_storage.getString("phone","null");
        Log.d(TAG,"enc_memberNum is =>"+ enc_memberNum);
        Log.d(TAG," phone is =>"+  phone);

    }

    private void initView() {
        ticketPresentList = (ListView)mView.findViewById(R.id.ticket_present_list);
    }

    private void showDialogBar() {
        asyncDialog = new ProgressDialog(getActivity());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        asyncDialog.show();
    }

}
