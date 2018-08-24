package com.clipservice.eticket.ui.ticket.ticketPresentList;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.clipservice.eticket.R;
import com.clipservice.eticket.controllers.BaseActivity;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.MainTicketListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.TicketingResItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clip-771 on 2018-03-09.
 */

public class TicketPresentListActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private TicketPresentListFragment ticketPresentListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_present_list);
        ticketPresentListFragment = new TicketPresentListFragment();
        fragmentManager = getFragmentManager();
        ft = getFragmentManager().beginTransaction();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container,ticketPresentListFragment);
        ft.commit();
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

    @Override
    public void setSendTicketRes(String str) {

    }

}
