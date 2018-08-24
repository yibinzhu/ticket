package com.clipservice.eticket.ui.ticket.myTicketDetail;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.controllers.BaseActivity;
import com.clipservice.eticket.models.BottomTicketListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.GrpTicketModel;
import com.clipservice.eticket.models.MainTicketListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.TicketDataModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.ui.common.RecoActivity;
import com.google.gson.Gson;
import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

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

//public class TicketDetailActivitys extends RecoActivity implements RECORangingListener {
public class TicketDetailActivitys extends BaseActivity {
    private String enc_ticketNum;
    private String enc_validKey;
    private String enc_theaterNum;
    private String enc_playNum;
    private String enc_playSaleNum;
    private String playTitle;
    private String enc_sequence;
    private String sequenceText;
    private String seatType;
    private String enc_seatNum;
    private String discountNum;
    private String discountName;
    private String publishDate;
    private String status;
    private String statusName;
    private String ticketImg;
    private String Accuracy;
    private String setAccuracy;
    private String seatData;
    private int ticketcounter;
    private String BeaconProximityUuid_s = "24DDF4118CF1440C87CDE368DAF9C93E";
    private String BeaconMajor_s = "501";
    private String BeaconMinor_1 = "9404";
    private String BeaconMinor_2 = "9403";
    private String ticketKey;
    private ArrayList<TicketDataModel> ticketDataModelArrayList;
    private ArrayList<String> ticketNumsList;
    private Boolean RequestTicketing = false;
    private Boolean Entrance = false;
    private Boolean isPrinted;
    private TextView  TicketCount_tv;
    private TextView seatType_tv;
    private TextView playTitle_tv;
    private TextView entrance_time_tv;
    private TextView ticketNum_tv;
    private ImageView imgStamp;
    private TextView seatData_tv;
    private LinearLayout seatDataContainer_tv;
    private List<SeatData> seatDataList;
    private View mView;
    private static final String TAG = "TicketDetailActivity02";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_LOCATION = 10;
    private ArrayList<HashMap<String,String>> seatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticekt_detail02);
        mView = getWindow().getDecorView();
        init();
    }
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

        for (RECOBeaconRegion region : regions) {
            try {
                mRecoManager.startRangingBeaconsInRegion(region);
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
            double distance = obj.getAccuracy();
            String distance_string = String.valueOf(String.format("%.2f", obj.getAccuracy()));
            String Rssi = String.valueOf(obj.getRssi());
            Log.e(TAG, "didRangeBeaconsInRegion() ProximityNear: " + ProximityNear);
            Log.e(TAG, "didRangeBeaconsInRegion() gAccuracy: " + distance_string);
            Log.e(TAG, "didRangeBeaconsInRegion() Rssi: " + Rssi);
            //비콘 번호에 따라 동작 실행
            if (BeaconProximityUuid_s.equals(BeaconProximityUuid) || BeaconMajor_s.equals(BeaconMajor)) {
                //발권처리
                if (BeaconMinor.equals("9404")) {
//                    if(distance < 0.1){
                        Log.e(TAG, "9404인식된다");
                        isPrinted = true;
                        imgStamp.setVisibility(View.VISIBLE);
                        requestTicketing(ticketDataModelArrayList,GlobalValues.enc_memberNum);

                        ticketNumsList = formatTicketNums(GlobalValues.ticketGrpMap.get(ticketKey));

//                    }
                }
                //입장처리
                if (BeaconMinor.equals("9403") || RequestTicketing) {
                    Log.e(TAG, "9403인식된다");
                    entrance(ticketNumsList,GlobalValues.enc_memberNum);
                    Entrance = true;
                    setEntranceTime();
                }
            }
            Log.e(TAG, "didRangeBeaconsInRegion() getProximityUuid(): " + obj.getProximityUuid());
            Log.e(TAG, "didRangeBeaconsInRegion() getMajor(): " + obj.getMajor());
            Log.e(TAG, "didRangeBeaconsInRegion() getMinor(): " + obj.getMinor());
        }
        //Write the code when the beacons in the region is received
        if (RequestTicketing || Entrance) {
            this.stop(mRegions);
            Log.e(TAG, "비콘인식끈난다");
        }
    }

    private void setEntranceTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = df.format(new Date());
        entrance_time_tv.setText(date);
    }

    @Override
    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion recoBeaconRegion, RECOErrorCode recoErrorCode) {
        Log.i(TAG, "error code :" + recoErrorCode);
        //Write the code when the RECOBeaconService is failed to range beacons in the region.
        //See the RECOErrorCode in the documents.
        return;
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

    @Override
    public void setPublishingResponse(PublishingResponseModel publishingResponseModel_) {

    }

    @Override
    public void renderMainPageView(ArrayList<MainTicketListModel> mainTicketListModelList, ArrayList<BottomTicketListModel> bottomTicketListModelArrayList) {

    }

    @Override
    public void setRequestTicketingRes(ArrayList<TicketingResItemModel> ticketingResItemModelArrayList) {
        boolean flag = true;
        for(TicketingResItemModel ticketingResItemModel:ticketingResItemModelArrayList){
            String result = ticketingResItemModel.getResult();
            if(result.equals("FAIL")){
                flag = false;
            }
        }
        if(flag == true){
            RequestTicketing = true;
        }
    }

    @Override
    public void setEntranceRes(EntranceResModel entranceResModel) {

    }

    @Override
    public void setSendTicketRes(String str) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            //If the request to turn on bluetooth is denied, the app will be finished.
            //사용자가 블루투스 요청을 허용하지 않았을 경우, 어플리케이션은 종료됩니다.
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode) {
            case REQUEST_LOCATION : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(mView, "onMonitoringToggleButtonClicked off to on", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(mView,"onMonitoringToggleButtonClicked on to off", Snackbar.LENGTH_LONG).show();
                }
            }
            default :
                break;
        }


    }

    private void init() {
        initView();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "The location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is not granted.");
                this.requestLocationPermission();
            } else {
                Log.i(TAG, "The location permission (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION) is already granted.");
            }
        }

        checkBlueTooth();

        getDataFromMain();

        formatTicketData();


        getTodayTicketColor();
//        setStamp();
    }

    private void formatTicketData() {
        ticketDataModelArrayList = fomatQesData_ticket(ticketKey,GlobalValues.ticketGrpMap);
        Log.d(TAG,"ticketData_str is"+ticketDataModelArrayList);
    }


    private void getTodayTicketColor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       MyTicketDetailApi api = retrofit.create(MyTicketDetailApi.class);
        Call<ResponseBody> call = api.getTodayTicketColor();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String responseBody = response.body().toString();
                Log.d(TAG,"티켓 인증색상 /getTodayTicketColor is =>"+responseBody );
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"티켓 인증색상 /getTodayTicketColor failed"+t);
            }
        });
    }

    private void setStamp() {
        //발권도장 처리
        if(RequestTicketing){
            imgStamp.setVisibility(View.VISIBLE);
        }else{
            imgStamp.setVisibility(View.GONE);
        }
    }

    private void initView() {
        playTitle_tv = (TextView)findViewById(R.id.ticket_title);
        ticketNum_tv = (TextView) findViewById(R.id.ticket_counter);
        entrance_time_tv = (TextView) findViewById(R.id.enter_time);
        imgStamp = (ImageView) findViewById(R.id.imgStamp);
        TicketCount_tv = (TextView)findViewById(R.id.ticket_counter);
        seatDataContainer_tv  = (LinearLayout) findViewById(R.id.seat_container);
    }

    private void getDataFromMain() {

        Bundle bundle = getIntent().getExtras();
        ArrayList list = bundle.getIntegerArrayList("seatList");
        seatList = (ArrayList<HashMap<String,String>>)list.get(0);
        String playTitle = getIntent().getStringExtra("playTitle");
        ticketKey = getIntent().getStringExtra("key");

        setSeatData(seatList);
        playTitle_tv.setText(playTitle);
    }

    private void setSeatData(ArrayList<HashMap<String,String>> list) {
        Log.d(TAG,"338/seatDataList size is"+list.size());
        int ticket_counter = 0;
        for(Map<String,String> ele:list){
            for(String k:ele.keySet()){
                SeatTypeLayout seatTypeLayout = new SeatTypeLayout(this);
                String counter = ele.get(k);
                ticket_counter = Integer.parseInt(counter)+ticket_counter;
                seatTypeLayout.setSeatType(k);
                seatTypeLayout.setCounter(ele.get(k));
                Log.d(TAG,"327/add 1 view");

                seatDataContainer_tv.addView(seatTypeLayout);
            }
        }
        TicketCount_tv.setText(String.valueOf(ticket_counter));
    }


    public void checkBlueTooth() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();
        //支持蓝牙模块
        if (blueadapter != null) {
            if (blueadapter.isEnabled()) {
                Toast tst = Toast.makeText(TicketDetailActivitys.this, "블루투스 이미 부트했습니다", Toast.LENGTH_SHORT);
                tst.show();
                initBeaconScaner();
            } else {
                new AlertDialog.Builder(TicketDetailActivitys.this).setTitle("블루투스 커진，부트 하시겠습니까?")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (turnOnBluetooth()) {
                                    Toast tst = Toast.makeText(TicketDetailActivitys.this, "블루투스 부트 성공", Toast.LENGTH_SHORT);
                                    tst.show();
                                    initBeaconScaner();
                                } else {
                                    Toast tst = Toast.makeText(TicketDetailActivitys.this, "블루투스 부트 실페", Toast.LENGTH_SHORT);
                                    tst.show();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
            }
        } else {
            Toast tst = Toast.makeText(TicketDetailActivitys.this, "this device not support buletooth", Toast.LENGTH_SHORT);
            tst.show();
        }
    }

    private void initBeaconScaner() {
        //mRecoManager will be created here. (Refer to the RECOActivity.onCreate())
        //mRecoManager 인스턴스는 여기서 생성됩니다. RECOActivity.onCreate() 메소들르 참고하세요.

        //Set RECORangingListener (Required)
        //RECORangingListener 를 설정합니다. (필수)
        mRecoManager.setRangingListener(this);

        /**
         * Bind RECOBeaconManager with RECOServiceConnectListener, which is implemented in RECOActivity
         * You SHOULD call this method to use monitoring/ranging methods successfully.
         * After binding, onServiceConenct() callback method is called.
         * So, please start monitoring/ranging AFTER the CALLBACK is called.
         *
         * RECOServiceConnectListener와 함께 RECOBeaconManager를 bind 합니다. RECOServiceConnectListener는 RECOActivity에 구현되어 있습니다.
         * monitoring 및 ranging 기능을 사용하기 위해서는, 이 메소드가 "반드시" 호출되어야 합니다.
         * bind후에, onServiceConnect() 콜백 메소드가 호출됩니다. 콜백 메소드 호출 이후 monitoring / ranging 작업을 수행하시기 바랍니다.
         */
        mRecoManager.bind(this);
    }

    public static boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }

        return false;
    }

    private void requestLocationPermission() {
        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
            return;
        }

        Snackbar.make(mView,"Location permission is needed to monitor or range beacons.", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(TicketDetailActivitys.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
                    }
                })
                .show();
    }

    private ArrayList<String> formatTicketNums(ArrayList<UserTicketListModel> userTicketListModelArrayList){
        if(userTicketListModelArrayList==null){
            return null;
        }else{
            ArrayList<String> enc_ticketNums = new ArrayList<>();
            for(UserTicketListModel userTicketListModel:userTicketListModelArrayList){
                String enc_ticketNum = userTicketListModel.getEnc_ticketNum();
                enc_ticketNums.add(enc_ticketNum);
            }
            return enc_ticketNums;
        }
    }

    class SeatData{
        private String seatType;
        private String counter;

        public SeatData(String seatType, String counter) {
            this.seatType = seatType;
            this.counter = counter;
        }

        public String getSeatType() {
            return seatType;
        }

        public String getCounter() {
            return counter;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public void setCounter(String counter) {
            this.counter = counter;
        }
    }
}
