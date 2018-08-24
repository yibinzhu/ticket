package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.clipservice.eticket.R;

import java.util.ArrayList;

/**
 * Created by clip-771 on 2018-03-07.
 */

public class SequenceListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SequenceListModel> items;

    public SequenceListAdapter(Context context,ArrayList<SequenceListModel> items){
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
        SequenceListViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sequence_list, viewGroup,false);
            viewHolder = new SequenceListViewHolder (view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SequenceListViewHolder )view.getTag();
        }
        SequenceListModel currentItem = (SequenceListModel) getItem(i);
        SequenceListViewHolder myViewHolder = new SequenceListViewHolder (view);
        return view;
    }

}
