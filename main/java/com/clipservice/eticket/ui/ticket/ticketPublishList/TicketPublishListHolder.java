package com.clipservice.eticket.ui.ticket.ticketPublishList;

import android.view.View;
import android.widget.TextView;

import com.clipservice.eticket.R;

/**
 * Created by clip-771 on 2018-03-12.
 */

public class TicketPublishListHolder {
    TextView playTitle;
    TextView sequenceText;
    TextView seatType;

    public TicketPublishListHolder(View view){
        playTitle = (TextView)view.findViewById(R.id.play_title);
        sequenceText = (TextView)view.findViewById(R.id.sequence_text);
        seatType = (TextView)view.findViewById(R.id.seat_type);
    }

}
