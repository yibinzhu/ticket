package com.clipservice.eticket.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clipservice.eticket.R;

/**
 * Created by clip-352 on 2018-03-13.
 */

public class MsgFragment extends Fragment {
    private View rootView;
    private String sendMsg;
    public static final int INDEX = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_msg, container, false);

//        ((MainActivity)rootView.getContext()).setControlListener(new MainActivity.ControlListener() {
//            @Override
//            public void onRefreshControl(String str) {
//                if (canvasView != null) {
//                    canvasView.SetData(layW, actualHeight, str);
//                }
//            }
//        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}