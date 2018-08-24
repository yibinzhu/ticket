package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

import android.view.View;
import android.widget.TextView;
import com.clipservice.eticket.R;

/**
 * Created by clip-771 on 2018-03-07.
 */

public class SequenceListViewHolder {
    public TextView sequence;
    public TextView enc_sequence;
    public TextView playdate;
    public TextView playtime;
    public TextView playdatetime;
    public TextView sequenceText;
    public TextView selectMsg;
    public SequenceListViewHolder(View convertView){
        playdate = (TextView)convertView.findViewById(R.id.data_time);
        playtime = (TextView)convertView.findViewById(R.id.play_time);
        selectMsg = (TextView)convertView.findViewById(R.id.select_msg);
    }

}
