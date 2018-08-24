package com.clipservice.eticket.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.clipservice.eticket.R;

/**
 * Created by clip-771 on 2018-02-09.
 */

public class TicktItemView extends LinearLayout {
    private View mView;

    public TicktItemView(Context context) {
        super(context);
        mView = LayoutInflater.from(context).inflate(R.layout.item_ticket_view,this,true);
        init();
    }
    public TicktItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(R.layout.item_ticket_view,this,true);
        init();
    }
    private void init() {
    }

}
