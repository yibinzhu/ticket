package com.clipservice.eticket.common;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.beans.SeatDataModel;
import com.clipservice.eticket.models.AdBannerResponseModel;
import com.clipservice.eticket.models.BannerModel;
import com.clipservice.eticket.models.BookingListModel;
import com.clipservice.eticket.models.GrpTicketModel;
import com.clipservice.eticket.models.PlayModel;
import com.clipservice.eticket.models.PublishReadyListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.TicketDataModel;
import com.clipservice.eticket.models.TicketModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.models.BeaconInfoModel;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.MainTicketListModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalValues extends Application {
    public boolean FLAG = false;
    public boolean viewLock = false;
    public int bleRefreshTime;
    public static final String ENC_STORENUM = "h8Fp1IwONBMBAw/EP0iyKg==";
    public static List<BannerModel> bannerModelList;
    public static List<PlayModel> playModelList;
    public static List<TicketModel> ticketModelList;
    public static List<TicketDataModel> reqDataList;
    public static List<PublishReadyListModel> publishReadyListModelList;
    public static PublishingResponseModel publishingResponseModel;
    public static List<PrepaidTicketModel> prepaidList;
    public static ArrayList<String> bannerimageList;
    public static List<BeaconInfoModel> beaconInfoModels;

    public static List<BookingListModel> salePlayLists;//티켓 예매 리스트
    public static List<UserTicketListModel> userTicketListModelList;//보유티켓리스트
    public static List<AdBannerResponseModel> adBannerResponseModelList;//광고 API 반납 리스트
    public static ArrayList<MainTicketListModel> mainTicketListModelList;//조합된 메인 이메지 리스트
    public static List<BottomTicketListModel> bottomTicketListModelArrayList;//하단에 조합된 광고 하고 예매 이메지 리스트
    public static List<SeatDataModel> seatDataModels; //좌석정보 리스트
    public static List<Map<String, String>> mapList;//티켓 주문 리스트
    public static ArrayList<GrpTicketModel> ticketGrpList;// 그룹잉된 티켓리스트
    public static  Map<String, ArrayList<UserTicketListModel>> ticketGrpMap;
    public static  Map<String, ArrayList<HashMap<String, String>>> seatTypeMap;
    public static  Map<String, GrpTicketModel> groupingMap;

    public static String Accuracy;
    public static String setAccuracy;
    public static String BeaconProximityUuid_s = "24DDF4118CF1440C87CDE368DAF9C93E";
    public static String BeaconMajor_s = "501";
    public static String BeaconMinor_1 = "9404";
    public static String BeaconMinor_2 = "9403";
    public static Boolean RequestTicketing = false;
    public static Boolean Entrance = false;
    public static Boolean isPrinted = false;
    public static String phone1;
    public static String phone2;
    public static String phone3;
    public static String phone = phone1 + phone2 + phone3;
    public static String deviceId;
    public static String enc_memberNum;
    public static String pushKey;
    public static String enc_authKey;
    public static String reqData;
    public static Queue<String> mQueue;
    public static String TAG = "GlobalValues";
    private static final String TYPE_MY = "0"; //보유티켓
    private static final String TYPE_AD = "1"; //광고
    private static final String TYPE_BK = "2"; //예매
    public static Boolean renderDataReady =false;
    public static final String FILE_NAME = "cloudTicket";//SharedPreferences

    public static void setRenderDataReady(Boolean renderDataReady) {
        GlobalValues.renderDataReady = renderDataReady;
    }

    public static Boolean getRenderDataReady() {
        return renderDataReady;
    }

    public void setTicketGrpMap(Map<String, ArrayList<UserTicketListModel>> ticketGrpMap) {
        this.ticketGrpMap = ticketGrpMap;
    }

    public void setSeatTypeMap(Map<String, ArrayList<HashMap<String, String>>> seatTypeMap) {
        this.seatTypeMap = seatTypeMap;
    }

    public void setGroupingMap(Map<String, GrpTicketModel> groupingMap) {
        this.groupingMap = groupingMap;
    }

    public Map<String, ArrayList<UserTicketListModel>> getTicketGrpMap() {
        return ticketGrpMap;
    }

    public Map<String, ArrayList<HashMap<String, String>>> getSeatTypeMap() {
        return seatTypeMap;
    }

    public Map<String, GrpTicketModel> getGroupingMap() {
        return groupingMap;
    }

    public boolean isFLAG() {
        return FLAG;
    }

    public boolean isViewLock() {
        return viewLock;
    }
    public String getEncStorenum() {
        return ENC_STORENUM;
    }

    public List<BannerModel> getBannerModelList() {
        return bannerModelList;
    }

    public List<PlayModel> getPlayModelList() {
        return playModelList;
    }

    public List<TicketModel> getTicketModelList() {
        return ticketModelList;
    }

    public List<TicketDataModel> getReqDataList() {
        return reqDataList;
    }

    public List<PublishReadyListModel> getPublishReadyListModelList() {
        return publishReadyListModelList;
    }

    public PublishingResponseModel getPublishingResponseModel() {
        return publishingResponseModel;
    }

    public List<PrepaidTicketModel> getPrepaidList() {
        return prepaidList;
    }

    public ArrayList<String> getBannerimageList() {
        return bannerimageList;
    }

    public List<BeaconInfoModel> getBeaconInfoModels() {
        return beaconInfoModels;
    }

    public List<BookingListModel> getSalePlayLists() {
        return salePlayLists;
    }

    public List<UserTicketListModel> getUserTicketListModelList() {
        return userTicketListModelList;
    }

    public List<AdBannerResponseModel> getAdBannerResponseModelList() {
        return adBannerResponseModelList;
    }

    public ArrayList<MainTicketListModel> getMainTicketListModelList() {
        return mainTicketListModelList;
    }

    public List<BottomTicketListModel> getBottomTicketListModelArrayList() {
        return bottomTicketListModelArrayList;
    }

    public List<SeatDataModel> getSeatDataModels() {
        return seatDataModels;
    }

    public List<Map<String, String>> getMapList() {
        return mapList;
    }

    public ArrayList<GrpTicketModel> getTicketGrpList() {
        return ticketGrpList;
    }

    public String getAccuracy() {
        return Accuracy;
    }

    public String getSetAccuracy() {
        return setAccuracy;
    }

    public String getBeaconProximityUuid_s() {
        return BeaconProximityUuid_s;
    }

    public String getBeaconMajor_s() {
        return BeaconMajor_s;
    }

    public String getBeaconMinor_1() {
        return BeaconMinor_1;
    }

    public String getBeaconMinor_2() {
        return BeaconMinor_2;
    }

    public Boolean getRequestTicketing() {
        return RequestTicketing;
    }

    public Boolean getEntrance() {
        return Entrance;
    }

    public boolean isIsPrinted() {
        return isPrinted;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public String getPhone() {
        return phone;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getEnc_memberNum() {
        return enc_memberNum;
    }

    public String getPushKey() {
        return pushKey;
    }

    public String getEnc_authKey() {
        return enc_authKey;
    }

    public String getReqData() {
        return reqData;
    }

    public Queue<String> getmQueue() {
        return mQueue;
    }

    public int getBleRefreshTime() {
        return bleRefreshTime;
    }

    public void setFLAG(boolean FLAG) {
        this.FLAG = FLAG;
    }

    public void setViewLock(boolean viewLock) {
        this.viewLock = viewLock;
    }

    public void setBleRefreshTime(int bleRefreshTime) {
        this.bleRefreshTime = bleRefreshTime;
    }

    public void setBannerModelList(List<BannerModel> bannerModelList) {
        this.bannerModelList = bannerModelList;
    }

    public void setPlayModelList(List<PlayModel> playModelList) {
        this.playModelList = playModelList;
    }

    public void setTicketModelList(List<TicketModel> ticketModelList) {
        this.ticketModelList = ticketModelList;
    }

    public void setReqDataList(List<TicketDataModel> reqDataList) {
        this.reqDataList = reqDataList;
    }

    public void setPublishReadyListModelList(List<PublishReadyListModel> publishReadyListModelList) {
        this.publishReadyListModelList = publishReadyListModelList;
    }

    public void setPublishingResponseModel(PublishingResponseModel publishingResponseModel) {
        this.publishingResponseModel = publishingResponseModel;
    }

    public void setPrepaidList(List<PrepaidTicketModel> prepaidList) {
        this.prepaidList = prepaidList;
    }

    public void setBannerimageList(ArrayList<String> bannerimageList) {
        this.bannerimageList = bannerimageList;
    }

    public static void setBeaconInfoModels(List<BeaconInfoModel> beaconInfoModels) {
        GlobalValues.beaconInfoModels = beaconInfoModels;
    }

    public void setSalePlayLists(List<BookingListModel> salePlayLists) {
        this.salePlayLists = salePlayLists;
    }

    public void setUserTicketListModelList(List<UserTicketListModel> userTicketListModelList) {
        this.userTicketListModelList = userTicketListModelList;
    }

    public void setAdBannerResponseModelList(List<AdBannerResponseModel> adBannerResponseModelList) {
        this.adBannerResponseModelList = adBannerResponseModelList;
    }

    public void setMainTicketListModelList(ArrayList<MainTicketListModel> mainTicketListModelList) {
        this.mainTicketListModelList = mainTicketListModelList;
    }

    public void setBottomTicketListModelArrayList(List<BottomTicketListModel> bottomTicketListModelArrayList) {
        this.bottomTicketListModelArrayList = bottomTicketListModelArrayList;
    }

    public void setSeatDataModels(List<SeatDataModel> seatDataModels) {
        this.seatDataModels = seatDataModels;
    }

    public void setMapList(List<Map<String, String>> mapList) {
        this.mapList = mapList;
    }

    public void setTicketGrpList(ArrayList<GrpTicketModel> ticketGrpList) {
        this.ticketGrpList = ticketGrpList;
    }

    public void setAccuracy(String accuracy) {
        this.Accuracy = accuracy;
    }

    public void setSetAccuracy(String setAccuracy) {
        this.setAccuracy = setAccuracy;
    }

    public void setBeaconProximityUuid_s(String beaconProximityUuid_s) {
        this.BeaconProximityUuid_s = beaconProximityUuid_s;
    }

    public void setBeaconMajor_s(String beaconMajor_s) {
        this.BeaconMajor_s = beaconMajor_s;
    }

    public void setBeaconMinor_1(String beaconMinor_1) {
        this.BeaconMinor_1 = beaconMinor_1;
    }

    public void setBeaconMinor_2(String beaconMinor_2) {
        this.BeaconMinor_2 = beaconMinor_2;
    }

    public void setRequestTicketing(Boolean requestTicketing) {
        this.RequestTicketing = requestTicketing;
    }

    public void setEntrance(Boolean entrance) {
        this.Entrance = entrance;
    }

    public void setIsPrinted(boolean isPrinted) {
        this.isPrinted = isPrinted;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setEnc_memberNum(String enc_memberNum) {
        this.enc_memberNum = enc_memberNum;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public void setEnc_authKey(String enc_authKey) {
        this.enc_authKey = enc_authKey;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public void setmQueue(Queue<String> mQueue) {
        this.mQueue = mQueue;
    }

    public void getUserTicketList() {
        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        String enc_memberNum_ = userInfo.getString("enc_memberNum",null);
        Log.d(TAG, "428/getUserTicketList() enc_memberNum is=>" + enc_memberNum_);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<List<UserTicketListModel>> call = api.getUserTicketList(enc_memberNum_);

        call.enqueue(new Callback<List<UserTicketListModel>>() {
            @Override
            public void onResponse(Call<List<UserTicketListModel>> call, retrofit2.Response<List<UserTicketListModel>> response) {
                List<UserTicketListModel> userTicketListModelList_ = response.body();
                userTicketListModelList = userTicketListModelList_;
                Log.d(TAG,"441/userTicketListModelList is => "+userTicketListModelList);
                Log.d(TAG,"442/userTicketListModelList is => "+userTicketListModelList_);
                groupingTicket(userTicketListModelList_);
                getGroupingList(userTicketListModelList_);
            }

            @Override
            public void onFailure(Call<List<UserTicketListModel>> call, Throwable t) {
                Log.e(TAG, "getUserTicketLis failed");
            }
        });
    }

    //티켓 그굽잉 처리
    public void groupingTicket(List<UserTicketListModel> userTicketListModelList) {
        if(userTicketListModelList == null){
            return;
        }
        Map<String, ArrayList<UserTicketListModel>> map = new HashMap<>();
        for (int i = 0; i < userTicketListModelList.size(); i++) {
            String enc_ticketNum = userTicketListModelList.get(i).getEnc_ticketNum();
            String enc_validKey = userTicketListModelList.get(i).getEnc_validKey();
            String enc_orderNum = userTicketListModelList.get(i).getEnc_orderNum();
            String enc_printGrp = userTicketListModelList.get(i).getEnc_printGrp();
            String enc_theaterNum = userTicketListModelList.get(i).getEnc_theaterNum();
            String enc_playNum = userTicketListModelList.get(i).getEnc_playNum();
            String enc_playSaleNum = userTicketListModelList.get(i).getEnc_playSaleNum();
            String playTitle = userTicketListModelList.get(i).getPlayTitle();
            String enc_sequence = userTicketListModelList.get(i).getEnc_sequence();
            String sequenceText = userTicketListModelList.get(i).getSequenceText();
            String seatType = userTicketListModelList.get(i).getSeatType();
            String enc_seatNum = userTicketListModelList.get(i).getEnc_seatNum();
            int discountNum = userTicketListModelList.get(i).getDiscountNum();
            String discountName = userTicketListModelList.get(i).getDiscountName();
            String publishDate = userTicketListModelList.get(i).getPublishDate();
            int status = userTicketListModelList.get(i).getStatus();
            String statusName = userTicketListModelList.get(i).getStatusName();
            String ticketImg = userTicketListModelList.get(i).getTicketImg();
            String sharedTo = userTicketListModelList.get(i).getSharedTo();
            String key = enc_playNum + enc_sequence + String.valueOf(status);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(new UserTicketListModel(enc_ticketNum, enc_validKey, enc_orderNum, enc_printGrp, enc_theaterNum, enc_playNum, enc_playSaleNum, playTitle, enc_sequence, sequenceText, seatType, enc_seatNum, discountNum, discountName, publishDate, status, statusName, ticketImg, sharedTo));
        }
        GlobalValues.ticketGrpMap = map;

        statSeatType(map);
    }

    //런더링 그룹잉 처리
    public void getGroupingList(List<UserTicketListModel> userTicketListModelList) {
        if(userTicketListModelList== null){
            return;
        }
        Map<String, GrpTicketModel> map = new HashMap<>();
        for (int i = 0; i < userTicketListModelList.size(); i++) {
            String enc_playNum = userTicketListModelList.get(i).getEnc_playNum();
            String playTitle = userTicketListModelList.get(i).getPlayTitle();
            String enc_sequence = userTicketListModelList.get(i).getEnc_sequence();
            String enc_theaterNum = userTicketListModelList.get(i).getEnc_theaterNum();
            int status = userTicketListModelList.get(i).getStatus();
            String ticketImg = userTicketListModelList.get(i).getTicketImg();
            String key = enc_playNum + enc_sequence + String.valueOf(status);
            if (!map.containsKey(key)) {
                map.put(key, new GrpTicketModel(playTitle, enc_playNum, enc_sequence, enc_theaterNum, status, key, ticketImg));
            }
        }
        GlobalValues.groupingMap = map;


        getMainBannerList();
        addToMainList(map);
    }

    //좌석 정보 크룹잉 처리
    public void statSeatType(Map<String, ArrayList<UserTicketListModel>> map) {
        if(map == null){
            return;
        }
        HashMap<String, ArrayList<HashMap<String, String>>> seatTypeMap_ = new HashMap<>();
        for (String key : map.keySet()) {
            if (!seatTypeMap_.containsKey(key)) {
                seatTypeMap_.put(key, new ArrayList<>());
            }
            ArrayList<HashMap<String, String>> seatTypeList = new ArrayList<>();
            HashMap<String, String> seatType = new HashMap<>();
            for (UserTicketListModel userTicketListModel : map.get(key)) {
                if (!seatType.containsKey(userTicketListModel.getSeatType())) {
                    seatType.put(userTicketListModel.getSeatType(), "1");
                } else {
                    String old_counter = seatType.get(userTicketListModel.getSeatType());
                    int counter_int = Integer.parseInt(old_counter);
                    seatType.put(userTicketListModel.getSeatType(), String.valueOf(counter_int + 1));
                }
            }
            seatTypeList.add(seatType);
            Log.d(TAG, "376/seatTypeLIst is => " + seatTypeList);
            seatTypeMap_.put(key, seatTypeList);
        }
        GlobalValues.seatTypeMap = seatTypeMap_;
    }

    public void getPublishReadyList(String enc_memberNum_,String phone_) {
        Log.d(TAG, "getPublishReadyList enc_memberNum =>" + enc_memberNum_);
        Log.d(TAG, "getPublishReadyList phone =>" + phone_);
        reqDataList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<List<PublishReadyListModel>> call = api.getPublishReadyList(enc_memberNum_,phone_);
        call.enqueue(new Callback<List<PublishReadyListModel>>() {
            @Override
            public void onResponse(Call<List<PublishReadyListModel>> call, retrofit2.Response<List<PublishReadyListModel>> response) {
                List<PublishReadyListModel> publishReadyListModelList_ = response.body();

//                GlobalValues.setPublishReadyListModelList(publishReadyListModelList_);

                publishReadyListModelList = publishReadyListModelList_;
                Log.d(TAG,"558/publishReadyListModelList is =>"+ publishReadyListModelList_);
                Log.d(TAG,"559/publishReadyListModelList is =>"+ publishReadyListModelList);
                String resData = fomatQesData_publish(publishReadyListModelList);
                requestPublishing(resData,enc_memberNum_);
            }

            @Override
            public void onFailure(Call<List<PublishReadyListModel>> call, Throwable t) {
                Log.e(TAG, "getPublishReadyList failed" + t);
            }
        });
    }

    public static void requestPublishing(String reqDatas, String enc_memberNum) {
        if(reqDatas == null){
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("enc_memberNum", enc_memberNum);
        map.put("reqData", reqDatas);
        Log.d(TAG, "reqData is=>" + reqDatas);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<PublishingResponseModel> call = api.requestPublishing(map);
        call.enqueue(new Callback<PublishingResponseModel>() {
            @Override
            public void onResponse(Call<PublishingResponseModel> call, retrofit2.Response<PublishingResponseModel> response) {
                PublishingResponseModel publishingResponseModel_ = response.body();
                publishingResponseModel = publishingResponseModel_;
                Log.d(TAG,"publishingResponseModel is =》"+publishingResponseModel_);
            }

            @Override
            public void onFailure(Call<PublishingResponseModel> call, Throwable t) {
                Log.e(TAG, "티켓 발행 요청/requestPublishing failed" + t);
            }
        });
    }

    public String getTodayTheaterNum() {
        String enc_theaterNum = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        for (GrpTicketModel grpTicketModel : groupingMap.values()) {
            String sequnce = grpTicketModel.getEnc_sequence();
            if (dateNowStr.equals(sequnce)) {
                enc_theaterNum = grpTicketModel.getEnc_theaterNum();
            }
        }
        return enc_theaterNum;
    }

    public List<BeaconInfoModel> getTodayBeacon(String enc_theaterNum) {
        List<BeaconInfoModel> list = new ArrayList<>();
        for (BeaconInfoModel item : beaconInfoModels) {
            String enc_theaterNum_ = item.getEnc_theaterNum();
            if (enc_theaterNum.equals(enc_theaterNum_)) {
                list.add(item);
            }
        }
        return list;
    }

    public List<BeaconInfoModel> checkMyticket(String enc_theaterNum) {
        List<BeaconInfoModel> list = new ArrayList<>();
        for (BeaconInfoModel beaconInfoModel : beaconInfoModels) {
            String enc_theaterNum_ = beaconInfoModel.getEnc_theaterNum();
            if (enc_theaterNum_.equals(enc_theaterNum)) {
                list.add(beaconInfoModel);
            }
        }
        return list;
    }

    public static String fomatQesData_publish(List<PublishReadyListModel> publishReadyListModelList_){
        Log.d(TAG,"631/publishReadyListModelList is = >"+publishReadyListModelList_);
        String reqDatasString = "";
        String resData_ = "";

        if(publishReadyListModelList_ == null){
            for(PublishReadyListModel publishReadyListModel:publishReadyListModelList_){
                reqDatasString = reqDatasString +"{\"enc_ticketNum\":\""+publishReadyListModel.getEnc_ticketNum()+"\","+"\"enc_validKey\":\""+publishReadyListModel.getEnc_validKey()+"\"},";
            }
            String reqDatasString_format = reqDatasString.substring(0,reqDatasString.length()-1);
            resData_= "["+reqDatasString_format+"]";
            return null;
        }
        return resData_;
    }

    public static String fomatQesData_ticket(List<UserTicketListModel> list){
        List<TicketDataModel> reqDataList = new ArrayList<>();
        for(UserTicketListModel userTicketListModel:list){
            String enc_ticketNum = userTicketListModel.getEnc_ticketNum();
            String enc_validKey = userTicketListModel.getEnc_validKey();
            reqDataList.add(new TicketDataModel(enc_ticketNum,enc_validKey));
        }
        String resData = reqDataList.toString();
        return resData;
    }

    //보유티켓을 메인 런더링 리스트 에 추가
    public void addToMainList(Map<String,GrpTicketModel> groupingMap) {
        String TYPE_MY = "0";
        Log.d(TAG,"groupingMap is"+groupingMap);

        mainTicketListModelList = new ArrayList<>();

        if(groupingMap == null){
            return;
        }
        for(GrpTicketModel grpTicketModel:groupingMap.values()){
            mainTicketListModelList.add(new MainTicketListModel(grpTicketModel.getPlayTitle(),0,grpTicketModel.getImg(),TYPE_MY,grpTicketModel.getKey()));
        }

    }

    public void getMainBannerList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIs api = retrofit.create(APIs.class);

        Call<List<AdBannerResponseModel>> call = api.getMainBannerList();

        call.enqueue(new Callback<List<AdBannerResponseModel>>() {
            @Override
            public void onResponse(Call<List<AdBannerResponseModel>> call, retrofit2.Response<List<AdBannerResponseModel>> response) {
                adBannerResponseModelList = response.body();
                Log.d(TAG,"adBannerModelList is=>"+adBannerResponseModelList);


                mainTicketListModelList = new ArrayList<>();
                bottomTicketListModelArrayList = new ArrayList<>();
                bannerimageList = new ArrayList<>();

                for(int i =0 ; i < adBannerResponseModelList.size() ; i++){
                    int bannerType = adBannerResponseModelList.get(i).getBannerType();
                    Log.d(TAG,"bannertype is => "+bannerType);
                    if(bannerType == 1){
                        //AppMainList
                        Log.d(TAG,"bannertype is 01");
                        mainTicketListModelList.add(new MainTicketListModel("",i,adBannerResponseModelList.get(i).getImg(),TYPE_AD,""));
                    }else{
                        //AppMainBottom
                        Log.d(TAG,"bannertype is 02");
                        bottomTicketListModelArrayList.add(new BottomTicketListModel(i,TYPE_AD,adBannerResponseModelList.get(i).getImg()));
                    }
                }

                for(int i = 0; i<bottomTicketListModelArrayList.size();i++){
                    bannerimageList.add(bottomTicketListModelArrayList.get(i).getImg());
                }

                GlobalValues.renderDataReady = true;

                Log.d(TAG,"mainTicketListModelList =>"+mainTicketListModelList);
                Log.d(TAG,"bottomTicketListModelArrayList =>"+bottomTicketListModelArrayList);
                Log.d(TAG,"bannerimageList bannerimageList =>"+bannerimageList);


            }

            @Override
            public void onFailure(Call<List<AdBannerResponseModel>> call, Throwable t) {
                Log.e(TAG,"getMainBannerList failed =>"+t);
            }
        });
    }

}
