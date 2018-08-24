package com.clipservice.eticket.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.beans.SeatDataModel;
import com.clipservice.eticket.common.APIs;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.models.AdBannerResponseModel;
import com.clipservice.eticket.models.BannerModel;
import com.clipservice.eticket.models.BeaconInfoModel;
import com.clipservice.eticket.models.BookingListModel;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.GrpTicketModel;
import com.clipservice.eticket.models.MainTicketListModel;
import com.clipservice.eticket.models.PlayModel;
import com.clipservice.eticket.models.PublishReadyListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.RollbackSendModel;
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.TicketDataModel;
import com.clipservice.eticket.models.SetUserResModel;
import com.clipservice.eticket.models.TicketModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.ui.common.RecoActivity;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.firebase.iid.FirebaseInstanceId;
import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseActivity extends RecoActivity implements RECORangingListener{
    private static final String TAG = "BaseActivity";
    private static boolean FLAG = true;
    private static List<BannerModel> bannerModelList;
    private static List<PlayModel> playModelList;
    private static List<TicketModel> ticketModelList;
    private static List<TicketDataModel> reqDataList;
    private static List<PublishReadyListModel> publishReadyListModelList;
    private static PublishingResponseModel publishingResponseModel;
    private static List<PrepaidTicketModel> prepaidList;
    private static ArrayList<String> bannerimageList;
    private static List<BeaconInfoModel> beaconInfoModels;
    private static List<BookingListModel> salePlayLists;//티켓 예매 리스트
    private static List<UserTicketListModel> userTicketListModelList;//보유티켓리스트
    private static List<AdBannerResponseModel> adBannerResponseModelList;//광고 API 반납 리스트
    private static ArrayList<MainTicketListModel> mainTicketListModelList;//조합된 메인 이메지 리스트
    private static List<BottomTicketListModel> bottomTicketListModelArrayList;//하단에 조합된 광고 하고 예매 이메지 리스트
    private static List<SeatDataModel> seatDataModels; //좌석정보 리스트
    private static List<Map<String, String>> mapList;//티켓 주문 리스트
    private static ArrayList<GrpTicketModel> ticketGrpList;// 그룹잉된 티켓리스트
    private static  Map<String, ArrayList<UserTicketListModel>> ticketGrpMap;
    private static  Map<String, ArrayList<HashMap<String, String>>> seatTypeMap;
    private static  Map<String, GrpTicketModel> groupingMap;
    private static final String TYPE_MY = "0"; //보유티켓
    private static final String TYPE_AD = "1"; //광고
    private static final String TYPE_BK = "2"; //예매

    private static String phone1;
    private static String phone2;
    private static String phone3;
    private static String phone= phone1+phone2+phone3;
    private static String deviceId;
    private static String enc_memberNum;
    private static String pushKey;
    private static String enc_authKey;
    private static String phone_number_device = "";
    private int compare_counter = 0;
    private int mapIndex;

    @Override
    protected void start(ArrayList<RECOBeaconRegion> regions) {
        /**
         * There is a known android bug that some android devices scan BLE devices only once. (link: http://code.google.com/p/android/issues/detail?id=65863)
         * To resolve the bug in our SDK, you can use setDiscontinuousScan() method of the RECOBeaconManager.
         * This method is to set whether the device scans BLE devices continuously or discontinuously.
         * The default is set as FALSE. Please set TRUE only for specific devices.
         *
         * mRecoManager.setDiscontinuousScan(true);
         */

//        for (RECOBeaconRegion region : regions) {
//            try {
//                mRecoManager.startRangingBeaconsInRegion(region);
//            } catch (RemoteException e) {
//                Log.i(TAG, "Remote Exception");
//                e.printStackTrace();
//            } catch (NullPointerException e) {
//                Log.i(TAG, "Null Pointer Exception");
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    protected void stop(ArrayList<RECOBeaconRegion> regions) {
        for (RECOBeaconRegion region : regions) {
            try {
                mRecoManager.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i(TAG, "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i(TAG, "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<RECOBeacon> collection, RECOBeaconRegion recoBeaconRegion) {
        Log.e(TAG, "didRangeBeaconsInRegion() region: " + recoBeaconRegion.getUniqueIdentifier() + ", number of beacons ranged: " + collection.size());
        Log.e(TAG, "didRangeBeaconsInRegion() getProximityUuid: " + recoBeaconRegion.getProximityUuid());
        Log.e(TAG, "didRangeBeaconsInRegion() getMajor: " + recoBeaconRegion.getMajor());
        Log.e(TAG, "didRangeBeaconsInRegion() getMinor: " + recoBeaconRegion.getMinor());
        Log.e(TAG, "didRangeBeaconsInRegion() getNotifyOnExit: " + recoBeaconRegion.getNotifyOnExit());
        Log.e(TAG, "didRangeBeaconsInRegion() getRegionExpirationTimeMillis: " + recoBeaconRegion.getRegionExpirationTimeMillis());

        for (RECOBeacon obj : collection) {
            String BeaconMajor = String.valueOf(obj.getMajor());
            String BeaconMinor = String.valueOf(obj.getMinor());
            String BeaconProximityUuid = String.valueOf(obj.getProximityUuid());
            String ProximityNear = String.valueOf(obj.getProximity());
            String Accuracy = String.valueOf(String.format("%.2f", obj.getAccuracy()));
            String Rssi = String.valueOf(obj.getRssi());
            Log.e(TAG, "didRangeBeaconsInRegion() ProximityNear: " + ProximityNear);
            Log.e(TAG, "didRangeBeaconsInRegion() gAccuracy: " + Accuracy);
            Log.e(TAG, "didRangeBeaconsInRegion() Rssi: " + Rssi);

            if (GlobalValues.BeaconProximityUuid_s.equals(BeaconProximityUuid) || GlobalValues.BeaconMajor_s.equals(BeaconMajor)) {
                if (BeaconMinor.equals("9404") || !GlobalValues.RequestTicketing) {
                    Log.e(TAG, "9404인식된다");
                    GlobalValues.isPrinted = true;
                    String verifyCode = "6785";
                    GlobalValues.RequestTicketing = true;
                }
                if (BeaconMinor.equals("9403") || !GlobalValues.Entrance) {
                    Log.e(TAG, "9403인식된다");
                    GlobalValues.Entrance = true;
                }
            }
            Log.e(TAG, "didRangeBeaconsInRegion() getProximityUuid(): " + obj.getProximityUuid());
            Log.e(TAG, "didRangeBeaconsInRegion() getMajor(): " + obj.getMajor());
            Log.e(TAG, "didRangeBeaconsInRegion() getMinor(): " + obj.getMinor());
        }
        //Write the code when the beacons in the region is received
        if (GlobalValues.RequestTicketing || GlobalValues.Entrance) {
            this.stop(mRegions);
            Log.e(TAG, "비콘인식끈난다");
        }
    }

    @Override
    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion recoBeaconRegion, RECOErrorCode recoErrorCode) {

    }

    @Override
    public void onServiceConnect() {
        Log.i(TAG, "onServiceConnect()");
        mRecoManager.setDiscontinuousScan(Const.DISCONTINUOUS_SCAN);
        this.start(mRegions);
        //Write the code when RECOBeaconManager is bound to RECOBeaconService
    }

    @Override
    public void onServiceFail(RECOErrorCode recoErrorCode) {
        //Write the code when the RECOBeaconService is failed.
        //See the RECOErrorCode in the documents.
        return;
    }

    public void checkVerison() {
        AppUpdater appUpdater = new AppUpdater(this);
        appUpdater
                .setDisplay(Display.DIALOG)
                .setCancelable(false)
                .setUpdateFrom(UpdateFrom.JSON)
                .setUpdateJSON("https://github.com/javiersantos/AppUpdater/wiki/UpdateFrom.JSON")//check version api address
                .setTitleOnUpdateAvailable("Update available")
                .setContentOnUpdateAvailable("Check out the latest version available of my app!")
                .setTitleOnUpdateNotAvailable("Update not available")
                .setContentOnUpdateNotAvailable("No update available. Check for updates again later!")
                .setButtonUpdate("업데이트")
                .setButtonUpdateClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(BaseActivity.this, "업데이트 페이지 가겠습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonDismiss("나중에")
                .setButtonDismissClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(BaseActivity.this, "취소 버튼 누렸습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonDoNotShowAgain("업데이트 안함")
                .setButtonDoNotShowAgainClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        appUpdater.stop();
                    }
                })
                .setIcon(R.drawable.ic_action_plus) // Notification icon
                .setCancelable(true); // Dialog could not be dismissable

        appUpdater.start();

    }

    public void getPhoneInfo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (tm != null) {
            deviceId = tm.getDeviceId();
            phone_number_device = tm.getLine1Number();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                deviceId = tm.getImei();
            } else {
                deviceId = tm.getDeviceId();
            }
        }

        pushKey = FirebaseInstanceId.getInstance().getToken();
        int index = phone_number_device.indexOf("10");
        Log.e(TAG,"phone_number_device index is "+index);
        Log.e(TAG,"phone_number_device"+phone_number_device);
        // set phone number is null when get device number length is 0
        if(phone_number_device.length() == 0){
            Log.e(TAG,"can not get phone number");
        }

        if(index != -1){
            phone1 = "0"+phone_number_device.substring(index,index+2);
            phone2 = phone_number_device.substring(index+2,index+6);
            phone3 = phone_number_device.substring(index+6);
        }


//        ACache mCache = ACache.get(this);
//        mCache.put("phone1", phone1);
//        mCache.put("phone2", phone2);
//        mCache.put("phone3", phone3);
//        mCache.put("phone",phone1+phone2+phone3);
//        mCache.put("deviceId",deviceId);
//        mCache.put("pushKey",pushKey);

        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        SharedPreferences.Editor editor = userInfo.edit();

        editor.putString("phone1",phone1);
        editor.putString("phone2",phone2);
        editor.putString("phone3",phone3);
        editor.putString("phone",phone1+phone2+phone3);
        editor.putString("deviceId",deviceId);
        editor.putString("pushKey",pushKey);
        editor.commit();

        Log.i(TAG,"phone_id is=>"+ deviceId);
        Log.i(TAG," pushKey is=>"+ pushKey);
        Log.e(TAG,"phone1 response is=>"+phone1);
        Log.e(TAG,"phone2 response is=>"+phone2);
        Log.e(TAG,"phone3 response is=>"+phone3);
        Log.e(TAG,"phone response is=>"+phone1+phone2+phone3);

//        GlobalValues.setPhone1(phone1);
//        GlobalValues.setPhone2(phone2);
//        GlobalValues.setPhone3(phone3);
//        GlobalValues.setPhone(phone1+phone2+phone3);
//        GlobalValues.setPushKey(pushKey);
//        GlobalValues.setDeviceId(deviceId);
    }

//    //티켓 그굽잉 처리
//    public Map<String,ArrayList<UserTicketListModel>> groupingTicket(List<UserTicketListModel> userTicketListModelList){
//        Map<String,ArrayList<UserTicketListModel>> map = new HashMap<>();
//        for(int i = 0;i<userTicketListModelList.size();i++){
//            String enc_ticketNum = userTicketListModelList.get(i).getEnc_ticketNum();
//            String enc_validKey = userTicketListModelList.get(i).getEnc_validKey();
//            String enc_orderNum = userTicketListModelList.get(i).getEnc_orderNum();
//            String enc_printGrp = userTicketListModelList.get(i).getEnc_printGrp();
//            String enc_playNum = userTicketListModelList.get(i).getEnc_playNum();
//            String enc_playSaleNum = userTicketListModelList.get(i).getEnc_playSaleNum();
//            String playTitle = userTicketListModelList.get(i).getPlayTitle();
//            String enc_theaterNum = userTicketListModelList.get(i).getEnc_theaterNum();
//            String enc_sequence = userTicketListModelList.get(i).getEnc_sequence();
//            String sequenceText = userTicketListModelList.get(i).getSequenceText();
//            String seatType = userTicketListModelList.get(i).getSeatType();
//            String enc_seatNum = userTicketListModelList.get(i).getEnc_seatNum();
//            int discountNum = userTicketListModelList.get(i).getDiscountNum();
//            String discountName = userTicketListModelList.get(i).getDiscountName();
//            String publishDate = userTicketListModelList.get(i).getPublishDate();
//            int status = userTicketListModelList.get(i).getStatus();
//            String statusName = userTicketListModelList.get(i).getStatusName();
//            String ticketImg = userTicketListModelList.get(i).getTicketImg();
//            String sharedTo = userTicketListModelList.get(i).getSharedTo();
//            String key  = enc_playNum + enc_sequence + String.valueOf(status);
//
//            if(!map.containsKey(key)){
//                map.put(key,new ArrayList<>());
//            }
//            map.get(key).add(new UserTicketListModel(enc_ticketNum, enc_validKey, enc_orderNum, enc_printGrp,enc_theaterNum, enc_playNum, enc_playSaleNum, playTitle, enc_sequence, sequenceText, seatType, enc_seatNum,discountNum, discountName, publishDate,status, statusName, ticketImg, sharedTo));
//        }
//
//        final GlobalValues globalValues = (GlobalValues)getApplication();
//        globalValues.setTicketGrpMap(map);
//        return map;
//    }
//
//    //런더링 그룹잉 처리
//    public Map<String,GrpTicketModel> getGroupingList(List<UserTicketListModel> userTicketListModelList){
//        Map<String,GrpTicketModel> map = new HashMap<>();
//        for(int i = 0;i<userTicketListModelList.size();i++){
//            String enc_playNum = userTicketListModelList.get(i).getEnc_playNum();
//            String playTitle = userTicketListModelList.get(i).getPlayTitle();
//            String enc_sequence = userTicketListModelList.get(i).getEnc_sequence();
//            String enc_theaterNum = userTicketListModelList.get(i).getEnc_theaterNum();
//            int status = userTicketListModelList.get(i).getStatus();
//            String ticketImg = userTicketListModelList.get(i).getTicketImg();
//            String key  = enc_playNum + enc_sequence + String.valueOf(status);
//            if(!map.containsKey(key)){
//                map.put(key,new GrpTicketModel(playTitle,enc_playNum,enc_sequence,enc_theaterNum,status,key,ticketImg));
//            }
//        }
//        final GlobalValues globalValues = (GlobalValues)getApplication();
//        globalValues.setGroupingMap(map);
//        return map;
//    }
//
//    //좌석 정보 크룹잉 처리
//    public Map<String,ArrayList<HashMap<String,String>>> statSeatType(Map<String,ArrayList<UserTicketListModel>> map){
//        HashMap<String,ArrayList<HashMap<String,String>>> seatTypeMap = new HashMap<>();
//        for(String key:map.keySet()){
//            if(!seatTypeMap.containsKey(key)){
//                seatTypeMap.put(key,new ArrayList<>());
//            }
//            ArrayList<HashMap<String,String>>seatTypeList = new ArrayList<>();
//            HashMap<String,String> seatType = new HashMap<>();
//            for(UserTicketListModel userTicketListModel:map.get(key)){
//                if(!seatType.containsKey(userTicketListModel.getSeatType())){
//                    seatType.put(userTicketListModel.getSeatType(),"1");
//                }else{
//                    String old_counter = seatType.get(userTicketListModel.getSeatType());
//                    int counter_int = Integer.parseInt(old_counter);
//                    seatType.put(userTicketListModel.getSeatType(),String.valueOf(counter_int+1));
//                }
//            }
//            seatTypeList.add(seatType);
//            Log.d(TAG,"376/seatTypeLIst is => "+seatTypeList);
//            seatTypeMap.put(key,seatTypeList);
//        }
//        final GlobalValues globalValues = (GlobalValues)getApplication();
//        globalValues.setSeatTypeMap(seatTypeMap);
//        return seatTypeMap;
//    }

    public void setUser(String deviceId,String phone1,String phone2,String phone3,String pushKey){
    Log.d(TAG,"247/deviceId is=>"+ deviceId);
    Log.d(TAG,"phone1 is=>"+ phone1);
    Log.d(TAG,"phone2 is=>"+ phone2);
    Log.d(TAG,"phone3 is=>"+ phone3);
    Log.d(TAG,"pushKey is=>"+ pushKey);

    HashMap<String,String> map = new HashMap<>();
    map.put("machineKey", deviceId);
    map.put("phone1", phone1);
    map.put("phone2", phone2);
    map.put("phone3", phone3);
    map.put("pushKey", pushKey);
    map.put("os","android");
    map.put("sendMsg","");
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Const.API_BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIs api = retrofit.create(APIs.class);

    Call<SetUserResModel> call = api.setUser(map);

    call.enqueue(new Callback<SetUserResModel>() {
        @Override
        public void onResponse(Call<SetUserResModel> call, retrofit2.Response<SetUserResModel> response) {
            SetUserResModel response_obj = response.body();
            if(response_obj == null){
                return;
            }
            String enc_authKey_ = response_obj.getEnc_authKey();
            String enc_memberNum_ = response_obj.getEnc_memberNum();


            SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
            SharedPreferences.Editor editor = userInfo.edit();

            editor.putString("enc_authKey",enc_authKey_);
            editor.putString("enc_memberNum",enc_memberNum_);
            editor.commit();

            Log.d(TAG,"response_obj is=>"+ response_obj);
            Log.d(TAG,"GlobalValues.enc_authKey is=>"+ enc_authKey_);
            Log.d(TAG," GlobalValues.enc_memberNum is=>"+enc_memberNum_);
        }

        @Override
        public void onFailure(Call<SetUserResModel> call, Throwable t) {
            Log.e(TAG,"setUser() failed =>"+t);
        }
    });
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
        ticketGrpMap = map;

        GlobalValues globalValues = (GlobalValues)getApplication();
        globalValues.setTicketGrpMap(ticketGrpMap);

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
        groupingMap = map;

        GlobalValues globalValues = (GlobalValues)getApplication();
        globalValues.setGroupingMap(groupingMap);

        getMainBannerList();
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
        seatTypeMap = seatTypeMap_;
        GlobalValues globalValues = (GlobalValues)getApplication();
        globalValues.setSeatTypeMap(seatTypeMap);
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
                publishReadyListModelList = response.body();

                Log.d(TAG,"558/publishReadyListModelList is =>"+ publishReadyListModelList);

                String resData = fomatQesData_publish(publishReadyListModelList);
                requestPublishing(resData,enc_memberNum_);
            }

            @Override
            public void onFailure(Call<List<PublishReadyListModel>> call, Throwable t) {
                Log.e(TAG, "getPublishReadyList failed" + t);
            }
        });
    }

    public void requestPublishing(String reqDatas, String enc_memberNum) {
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
                publishingResponseModel = response.body();

                setPublishingResponse(publishingResponseModel);

                Log.d(TAG,"publishingResponseModel is =》"+publishingResponseModel);
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

    public String fomatQesData_publish(List<PublishReadyListModel> publishReadyListModelList_){
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

    public ArrayList<TicketDataModel> fomatQesData_ticket(String key_,Map<String, ArrayList<UserTicketListModel>> ticketGrpMap){

        ArrayList<TicketDataModel> reqDataList = new ArrayList<>();

        for(UserTicketListModel userTicketListModel: ticketGrpMap.get(key_)){
            String enc_ticketNum = userTicketListModel.getEnc_ticketNum();
            String enc_validKey = userTicketListModel.getEnc_validKey();
            reqDataList.add(new TicketDataModel(enc_ticketNum,enc_validKey));
        }

        return reqDataList;
    }

    //보유티켓을 메인 런더링 리스트 에 추가
    public void addToMainList() {
        String TYPE_MY = "0";
        Log.d(TAG,"groupingMap is"+groupingMap);

        mainTicketListModelList = new ArrayList<>();
        bottomTicketListModelArrayList = new ArrayList<>();
        bannerimageList = new ArrayList<>();

        //광고 처리
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

        //보유티켓 처리
        if(groupingMap == null){
            return;
        }
        for(GrpTicketModel grpTicketModel:groupingMap.values()){
            mainTicketListModelList.add(new MainTicketListModel(grpTicketModel.getPlayTitle(),0,grpTicketModel.getImg(),TYPE_MY,grpTicketModel.getKey()));
        }

        renderMainPageView(mainTicketListModelList, (ArrayList<BottomTicketListModel>) bottomTicketListModelArrayList);

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

                final GlobalValues globalValues = (GlobalValues)getApplication();
                globalValues.setAdBannerResponseModelList(adBannerResponseModelList);

                addToMainList();

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

    public void publishingTickets(){
        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        String enc_memberNum_ = userInfo.getString("enc_memberNum",null);
        String phone_ = userInfo.getString("phone",null);
        Log.d(TAG, "773/publishingTickets enc_memberNum is=>" + enc_memberNum_);
        Log.d(TAG, "774/publishingTickets enc_memberNum is=>" + phone_);
        getPublishReadyList(enc_memberNum_,phone_);

    }

    public abstract void setPublishingResponse(PublishingResponseModel publishingResponseModel_);

    public abstract void renderMainPageView(ArrayList<MainTicketListModel> mainTicketListModelList, ArrayList<BottomTicketListModel> bottomTicketListModelArrayList);

    public abstract void setRequestTicketingRes(ArrayList<TicketingResItemModel> ticketingResItemModelArrayList);

    public abstract void setEntranceRes(EntranceResModel entranceResModel);

    public abstract void setSendTicketRes(String str);

    public void requestTicketing(ArrayList<TicketDataModel>ticketData, String enc_memberNum){
        HashMap<String, String> map = new HashMap<>();
        map.put("ticketData",ticketData.toString());
        map.put("enc_memberNum", enc_memberNum);
        Log.d(TAG, "ticketData is=>" + ticketData);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<ArrayList<TicketingResItemModel>> call = api.requestTicketing(map);
        call.enqueue(new Callback<ArrayList<TicketingResItemModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TicketingResItemModel>> call, retrofit2.Response<ArrayList<TicketingResItemModel>> response) {
                ArrayList<TicketingResItemModel> ticketingResItemModelArrayList = response.body();

                setRequestTicketingRes(ticketingResItemModelArrayList);

                Log.d(TAG,"ticketingResItemModelArrayList is =>"+ ticketingResItemModelArrayList);
            }

            @Override
            public void onFailure(Call<ArrayList<TicketingResItemModel>> call, Throwable t) {
                Log.e(TAG, "티켓 발권 요청/requestTicketing failed" + t);
            }
        });
    }

    public void entrance(ArrayList<String>enc_ticketNums,String enc_memberNum){
        if(enc_ticketNums==null){
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        for(String item:enc_ticketNums){
            map.put("ticketData",item);
        }
        map.put("enc_memberNum", enc_memberNum);
        Log.d(TAG, "ticketData is=>" + enc_ticketNums);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<EntranceResModel> call = api.entrance(map);
        call.enqueue(new Callback<EntranceResModel>() {
            @Override
            public void onResponse(Call<EntranceResModel> call, retrofit2.Response<EntranceResModel> response) {
                EntranceResModel entranceResModel = response.body();

                setEntranceRes(entranceResModel);

                Log.d(TAG,"entranceResModel is =>"+ entranceResModel);
            }

            @Override
            public void onFailure(Call<EntranceResModel> call, Throwable t) {
                Log.e(TAG, "입장 요청/entrance failed" + t);
            }
        });
    }

    public void sendTicket(String ticketData_,String enc_memberNum_,String phone){
        if(ticketData_ == null){
            return;
        }
        Log.d(TAG,"ticketData arraylist is"+ticketData_);
        Log.d(TAG,"enc_memberNum is"+enc_memberNum_);
        Log.d(TAG,"phone is"+phone);

        HashMap<String, String> map = new HashMap<>();
        map.put("ticketData",ticketData_);
        map.put("enc_memberNum",enc_memberNum_);
        map.put("phone",phone);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<ResponseBody> call = api.sendTicket(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                ResponseBody  body= response.body();
                String str_ = null;
                try {
                    str_ = body.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"entranceResModel is =>"+ str_);

                setSendTicketRes(str_);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "선물 하기 요청/requestTicketing failed" + t);
            }
        });
    }

    public void rollbackSend(ArrayList<TicketDataModel>ticketData,String enc_memberNum){
        if(ticketData == null){
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("ticketData",ticketData.toString());
        map.put("enc_memberNum",enc_memberNum);

        Log.d(TAG, "ticketData is=>" + ticketData);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<RollbackSendModel> call = api.rollbackSend(map);
        call.enqueue(new Callback<RollbackSendModel>() {
            @Override
            public void onResponse(Call<RollbackSendModel> call, retrofit2.Response<RollbackSendModel> response) {
                RollbackSendModel rollbackSendModel = response.body();

                Log.d(TAG,"rollbackSendModel is =>"+ rollbackSendModel);
            }

            @Override
            public void onFailure(Call<RollbackSendModel> call, Throwable t) {
                Log.e(TAG, "티켓 선물 하기 취소 요청/requestTicketing failed" + t);
            }
        });
    }

    public String format_ticketData(ArrayList<TicketDataModel> list) {
        String ticketData_str = "";
        for (TicketDataModel ticketDataModel : list) {
            String str = "{\"enc_ticketNum\":\""+ticketDataModel.getEnc_ticketNum()+"\",\"enc_validKey\":\""+ticketDataModel.getEnc_validKey()+"\"},";
            ticketData_str = ticketData_str + str;
        }
        String ticketData ="["+ticketData_str.substring(0,ticketData_str.length()-1)+"]";
        return ticketData;
    }
}
