package com.clipservice.eticket.ui.ticket.ticketBookingList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.clipservice.eticket.R;
import com.clipservice.eticket.common.APIs;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.models.BookingListModel;
import com.clipservice.eticket.ui.ticket.ticketBookingDetail.BookingdetailActivity;
import com.google.gson.Gson;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clip-771 on 2018-02-02.
 */

public class BookingListFragment extends Fragment {
    private ListView itemsListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<BookingListModel> salePlayLists;
    private ACache mACache;
    private String enc_storeNum;
    private View mView;
    private int pageSize = 15;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean paging = true;
    private static final String TAG = "BookingListFragment";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView  = inflater.inflate(R.layout.fragment_ticket_booking_list,null);
        init();
        return mView;
    }

    private void init() {
        itemsListView  = (ListView)mView.findViewById(R.id.listView);
        getSalePlayList();

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),BookingdetailActivity.class);
                intent.putExtra("ticketInfo",new Gson().toJson(salePlayLists.get(i)));
                startActivity(intent);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 0;
                previousTotal = 0;
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getSalePlayList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIs api = retrofit.create(APIs.class);

        Call<List<BookingListModel>> call = api.getSalePlayList(GlobalValues.ENC_STORENUM);

        call.enqueue(new Callback<List<BookingListModel>>() {
            @Override
            public void onResponse(Call<List<BookingListModel>> call, retrofit2.Response<List<BookingListModel>> response) {
                salePlayLists = response.body();
//                Log.d(TAG,"sale play list is =>"+salePlayLists);
//                for(BookingListModel item :salePlayLists){
//                    Log.d("playTitle",item.getPlayTitle());
//                    Log.d("saleStartdate",""+item.getSaleStartdate());
//                }
                final BookingListAdapter adapter = new BookingListAdapter(getActivity(), salePlayLists);
                Log.i(TAG,"itmeArrayList is=>"+salePlayLists);
                itemsListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BookingListModel>> call, Throwable t) {
                Log.d(TAG,"getSalePlayList failed");
            }
        });
    }


}
