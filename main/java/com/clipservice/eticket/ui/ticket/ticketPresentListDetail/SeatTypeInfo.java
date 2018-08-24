package com.clipservice.eticket.ui.ticket.ticketPresentListDetail;

import android.content.Context;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clipservice.eticket.R;
import com.shawnlin.numberpicker.NumberPicker;

public class SeatTypeInfo extends LinearLayout{
    private View mView;
    private TextView totalNum_tv;
    private TextView seatType_tv;
    private String seatType;
    private int num;
    private int selectBtnId;
    private NumberPicker numberPicker;
    private Button selectNum;
    private Context context;
    private FragmentManager fragmentManager;
    private String tag;
    private final String TAG = "SeatTypeInfo";

    public SeatTypeInfo(Context context) {
        super(context);
        this.context = context;
        mView = LayoutInflater.from(context).inflate(R.layout.widget_present_detail_seattype_info,this,true);
        init();
    }

    public SeatTypeInfo(Context context, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
        mView = LayoutInflater.from(context).inflate(R.layout.widget_present_detail_seattype_info,this,true);
        init();
    }

    public SeatTypeInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mView = LayoutInflater.from(context).inflate(R.layout.widget_present_detail_seattype_info,this,true);
        init();
    }

    private void init(){
        initView();
        setClickEvents();
    }

    private void setClickEvents() {
        selectNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"62/view id is"+view.getId());
                try {
                    BtnIdLisener btnIdLisener = (BtnIdLisener)context;
                    btnIdLisener.getId(view.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NumberPickDialog numberPickDialog = new NumberPickDialog();
                numberPickDialog.show(fragmentManager,tag);
            }
        });

    }

    private void initView() {
        totalNum_tv = (TextView)mView.findViewById(R.id.total_num);
        selectNum = (Button)mView.findViewById(R.id.select_num);
        seatType_tv = (TextView)mView.findViewById(R.id.seat_type);
    }

    public void setTotalNum(int num){
        this.num = num;
        totalNum_tv.setText(String.valueOf(num));
    }

    public int getTotalNum(){
        return this.num;
    }

    public String getSelectNum(){
        String selectNum_ = (String) selectNum.getText();
        return selectNum_;
    }

    public void setSelectNum(int num){
        selectNum.setText(String.valueOf(num));
    }
    public void setSeatType(String seatType){
        this.seatType = seatType;
        seatType_tv.setText(seatType);
    }
    public String getSeatType(){
        return seatType;
    }

    public void setSelectBtnId(int id){
        this.selectBtnId = id;
        selectNum.setId(id);
    }
    public int getSelectBtnId(){
        return selectBtnId;
    }

    public void setPickerTag(String tag){
        this.tag = tag;
    }

    public interface BtnIdLisener{
        void getId(int id);
    }
}
