package com.clipservice.eticket.ui.ticket.ticketBookingList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.clipservice.eticket.R;
import com.clipservice.eticket.models.BookingListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clip-771 on 2018-02-02.
 */

public class BookingListAdapter extends BaseAdapter {
    private Context context;
    private List<BookingListModel> items;

    public BookingListAdapter(Context context, List<BookingListModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        viewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_ticket_booking_list_item, viewGroup,false);
            viewHolder = new viewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder)convertView.getTag();
        }
        BookingListModel currentItem = (BookingListModel) getItem(position);
        viewHolder myViewHolder = new viewHolder(convertView);
        myViewHolder.playTitle.setText(currentItem.getPlayTitle());
        myViewHolder.genres.setText(currentItem.getGenres());
        Picasso.with(context)
            .load(currentItem.getPostImg())
            .placeholder(R.drawable.ic_image_background)
            .into(myViewHolder.postImg);

        return convertView;
    }

}

