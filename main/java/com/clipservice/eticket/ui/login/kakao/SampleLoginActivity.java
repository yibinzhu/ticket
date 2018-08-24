package com.clipservice.eticket.ui.login.kakao;

import android.content.Intent;
import android.os.Bundle;
import com.clipservice.eticket.R;
import com.clipservice.eticket.controllers.BaseActivity;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.MainTicketListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample Activity that dose not use fragment for login.
 */
public class SampleLoginActivity extends BaseActivity {
    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_login);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        callback = new SessionCallback();
//        Session.getCurrentSession().addCallback(callback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    @Override
    public void setPublishingResponse(PublishingResponseModel publishingResponseModel_) {

    }

    @Override
    public void renderMainPageView(ArrayList<MainTicketListModel> mainTicketListModelList, ArrayList<BottomTicketListModel> bottomTicketListModelArrayList) {

    }

    @Override
    public void setRequestTicketingRes(ArrayList<TicketingResItemModel> ticketingResItemModelArrayList) {

    }

    @Override
    public void setEntranceRes(EntranceResModel entranceResModel) {

    }

    @Override
    public void setSendTicketRes(String str) {

    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
//            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }
}
