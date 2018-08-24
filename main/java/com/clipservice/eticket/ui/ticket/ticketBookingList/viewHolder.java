package com.clipservice.eticket.ui.ticket.ticketBookingList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clipservice.eticket.R;

/**
 * Created by clip-771 on 2018-02-02.
 */

public class viewHolder {
    public TextView playTitle;
    public TextView genres;
    public ImageView postImg;

    public viewHolder(View convertView) {
        playTitle = (TextView)convertView.findViewById(R.id.ticket_playTitle);
        genres = (TextView)convertView.findViewById(R.id.ticket_genres);
        postImg = (ImageView)convertView.findViewById(R.id.ticket_postImg);
    }

}
