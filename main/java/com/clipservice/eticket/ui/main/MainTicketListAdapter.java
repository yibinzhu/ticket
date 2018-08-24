package com.clipservice.eticket.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.clipservice.eticket.R;
import com.clipservice.eticket.models.UserTicketListModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainTicketListAdapter extends PagerAdapter {
    private Context context;
    private List<UserTicketListModel> mainTicketList;
    private LayoutInflater mlayoutInflater;

    public MainTicketListAdapter(Context context, List<UserTicketListModel> mainTicketList) {
        this.context = context;
        this.mainTicketList = mainTicketList;
        mlayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mainTicketList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mlayoutInflater.inflate(R.layout.item_ticket_card,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView ticketTitle = (TextView)view.findViewById(R.id.ticket_title);
        Picasso.with(context).load(String.valueOf(mainTicketList.get(position).getTicketImg())).placeholder(R.drawable.t1_img_bg).into(imageView);
        String ticketImg = mainTicketList.get(position).getPlayTitle();
        ticketTitle.setText(ticketImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("viewpager","image is clicked");
//                Intent intent =new Intent(context,BookingdetailActivity.class);
//                intent.putExtra("ticketInfo",new Gson().toJson(mainTicketList.get(position)));
//                startActivity(intent);

            }
        });
        container.addView(view);
        return view;
    }

}
