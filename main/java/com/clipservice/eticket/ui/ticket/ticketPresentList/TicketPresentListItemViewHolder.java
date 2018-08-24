package com.clipservice.eticket.ui.ticket.ticketPresentList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.clipservice.eticket.R;

/**
 * Created by clip-771 on 2018-03-08.
 */

public class TicketPresentListItemViewHolder {
    ImageView ticketImg;
    TextView ticketTitle;
    TextView playTime;
    TextView ticketType;
    TextView ticketNum;
    TextView ticketCounter;
    TextView ticketMark;
    public TicketPresentListItemViewHolder(View view){
        ticketImg = (ImageView)view.findViewById(R.id.ticket_postImg);
        ticketTitle = (TextView)view.findViewById(R.id.ticket_title);
        playTime = (TextView)view.findViewById(R.id.play_time);
//        ticketType = (TextView)view.findViewById(R.id.ticket_type);
        ticketNum = (TextView)view.findViewById(R.id.ticket_counter);
        ticketCounter = (TextView)view.findViewById(R.id.ticket_counter);
        ticketMark  = (TextView)view.findViewById(R.id.ticket_mark);
    }

}
