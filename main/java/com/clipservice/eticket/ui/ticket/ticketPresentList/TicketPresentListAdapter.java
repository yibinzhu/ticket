package com.clipservice.eticket.ui.ticket.ticketPresentList;

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.models.PresentTicketModel;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by clip-771 on 2018-03-08.
 */

public class TicketPresentListAdapter extends BaseAdapter {
    private Context context;
    private List<PresentTicketModel> items;

    public TicketPresentListAdapter(Context context,List<PresentTicketModel> items){
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
        TicketPresentListItemViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.fragment_ticket_present_list_item,viewGroup,false);
            viewHolder = new TicketPresentListItemViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (TicketPresentListItemViewHolder) view.getTag();
        }
        PresentTicketModel currentItem = ( PresentTicketModel) getItem(i);
        viewHolder.ticketTitle.setText(currentItem.getPlayTitle());
        int status_ = currentItem.getStatus();
        switch (status_){
            case 4097:
                viewHolder.ticketMark.setText("선물하기");
                break;
            case 8193:
                viewHolder.ticketMark.setText("선물하기");
                break;
            case 16385:
                viewHolder.ticketMark.setText("입장완료");
                break;
            case 32770:
                viewHolder.ticketMark.setText("회수하기");
                break;
        }
        String palyTime_string = currentItem.getSequence();
//        int index = palyTime_string.indexOf("T");
//        String playTime_day = palyTime_string.substring(0,index-1);
//        String playTime_time = palyTime_string.substring(index+1,index+9);
//        String playTime_format = playTime_day +" "+playTime_time;
        String ticket_counter = currentItem.getCounter();
        viewHolder.playTime.setText(palyTime_string);
//        viewHolder.ticketType.setText("일반석");
        viewHolder.ticketCounter.setText(String.valueOf(ticket_counter));
        Picasso.with(context).load(currentItem.getTicketImg()).placeholder(R.drawable.ic_image_background).into(viewHolder.ticketImg);
        return view;
    }

}
