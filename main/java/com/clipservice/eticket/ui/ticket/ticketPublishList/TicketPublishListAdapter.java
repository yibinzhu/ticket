package com.clipservice.eticket.ui.ticket.ticketPublishList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.clipservice.eticket.R;

import java.util.ArrayList;

/**
 * Created by clip-771 on 2018-03-12.
 */

public class TicketPublishListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<TicketPublishListModel> items;

    public TicketPublishListAdapter(Context context,ArrayList<TicketPublishListModel> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TicketPublishListHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_ticket_publish_list_item, viewGroup,false);
            viewHolder = new TicketPublishListHolder (view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (TicketPublishListHolder )view.getTag();
        }
        TicketPublishListModel currentItem = ( TicketPublishListModel) getItem(i);
        TicketPublishListHolder myViewHolder = new TicketPublishListHolder (view);
        myViewHolder.playTitle.setText(currentItem.getPlayTitle());
        myViewHolder.sequenceText.setText(currentItem.getSequenceText());
        myViewHolder.seatType.setText(currentItem.getSeatType());
        return view;
    }

}
