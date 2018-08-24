package com.clipservice.eticket.ui.login;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clipservice.eticket.R;
import com.clipservice.eticket.ui.login.kakao.SampleLoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    View mView;
    RadioGroup radioGroup;
    Button btnLogin;
    RadioButton radioKakao;
    RadioButton radioNaver;
    RadioButton radioFacebook;
    FrameLayout frameLayoutKaokao;
    FrameLayout frameLayoutNaver;
    FrameLayout frameLayoutFacebook;

    private static final String TAG = "loginFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        init();
        return mView;
    }

    private void init(){
        initView();
        frameLayoutKaokao.setOnClickListener((View v)->{
            radioKakao.setChecked(true);
            radioNaver.setChecked(false);
            radioFacebook.setChecked(false);
        });

        frameLayoutNaver.setOnClickListener((View v)->{
            radioKakao.setChecked(false);
            radioNaver.setChecked(true);
            radioFacebook.setChecked(false);
        });

        frameLayoutFacebook.setOnClickListener((View v)->{
            radioKakao.setChecked(false);
            radioNaver.setChecked(false);
            radioFacebook.setChecked(true);
        });

        btnLogin.setOnClickListener((View v)->{
            if(radioKakao.isChecked()){
                Log.d(TAG,"you clicked kakao");
                Intent intent = new Intent(getActivity(),SampleLoginActivity.class);
                startActivity(intent);

            }
            if(radioNaver.isChecked()){
                Log.d(TAG,"you clicked naver");
            }
            if(radioFacebook.isChecked()){
                Log.d(TAG,"you clicked facebook");
            }

        });
    }

    private void initView() {
        radioGroup = (RadioGroup)mView.findViewById(R.id.radio_group_login);
        radioKakao = (RadioButton)mView.findViewById(R.id.radio_kaotalk);
        radioNaver = (RadioButton)mView.findViewById(R.id.radio_naver);
        radioFacebook = (RadioButton)mView.findViewById(R.id.radio_facebook);
        btnLogin  = (Button)mView.findViewById(R.id.btn_login);
        frameLayoutKaokao = (FrameLayout)mView.findViewById(R.id.framelayout_kakao);
        frameLayoutNaver = (FrameLayout)mView.findViewById(R.id.framelayout_naver);
        frameLayoutFacebook = (FrameLayout)mView.findViewById(R.id.framelayout_facebook);
    }
}
