package com.clipservice.eticket.ui.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.clipservice.eticket.R;
import com.clipservice.eticket.beans.PrepaidTicketModel;
import com.clipservice.eticket.beans.SeatDataModel;
import com.clipservice.eticket.common.APIs;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.common.GlobalValues;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.controllers.BaseActivity;
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
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.TicketDataModel;
import com.clipservice.eticket.models.TicketModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.clipservice.eticket.models.UserTicketListModel;
import com.clipservice.eticket.ui.adDetail.AdDetailActivity;
import com.clipservice.eticket.ui.login.LoginActivity;
import com.clipservice.eticket.ui.ticket.myTicketDetail.TicketDetailActivitys;
import com.clipservice.eticket.ui.ticket.ticketBookingDetail.BookingdetailActivity;
import com.clipservice.eticket.ui.ticket.ticketPresentList.TicketPresentListActivity;
import com.clipservice.eticket.utils.SharedUtils;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.clipservice.eticket.common.database.DatabaseHelper;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String Accuracy;
    private String setAccuracy;
    private String BeaconProximityUuid_s = "24DDF4118CF1440C87CDE368DAF9C93E";
    private String BeaconMajor_s = "501";
    private String BeaconMinor_1 = "9404";
    private String BeaconMinor_2 = "9403";
    private Boolean RequestTicketing = false;
    private Boolean Entrance = false;
    private Boolean isPrinted;
    private View mLayout;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_LOCATION = 10;
    private List<BookingListModel> salePlayLists;//티켓 예매 리스트
    private List<UserTicketListModel> userTicketListModelList;//보유티켓리스트
    public static ArrayList<GrpTicketModel> ticketGrpList;// 그룹잉된 티켓리스트
    private List<AdBannerResponseModel> adBannerResponseModelList;//광고 API 반납 리스트
    private ArrayList<MainTicketListModel> mainTicketListModelList;//조합된 메인 이메지 리스트
    private ArrayList<BottomTicketListModel> bottomTicketListModelArrayList;//하단에 조합된 광고 하고 예매 이메지 리스트
    public static ArrayList<String> bannerimageList;
    public static List<BannerModel> bannerModelList;
    public static List<PlayModel> playModelList;
    public static List<TicketModel> ticketModelList;
    public static List<TicketDataModel> reqDataList;
    public static List<PublishReadyListModel> publishReadyListModelList;
    public static PublishingResponseModel publishingResponseModel;
    public static List<PrepaidTicketModel> prepaidList;
    public static List<BeaconInfoModel> beaconInfoModels;
    public static List<SeatDataModel> seatDataModels; //좌석정보 리스트
    private HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;
    private ImageView backgroundContainer;
    private static final String TAG = "MainActivity";

    private String enc_authKey;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TextView addressText;
    private TextView playTimeText;
    private TextView ticketTitleText;
    private ImageView adImgText;
    private WebView webView;
    private Banner banner;
    private String phone;
    private String phone1;
    private String phone2;
    private String phone3;
    private String enc_memberNum;

    private static final String TYPE_MY = "0"; //보유티켓
    private static final String TYPE_AD = "1"; //광고
    private static final String TYPE_BK = "2"; //예매

    private DatabaseHelper helper;
    private ProgressDialog asyncDialog;

    private Boolean isCheckVersion = false;
    private Boolean showNav = false;
    private ImageButton btnNav;
    private DrawerLayout drawerLayout;
    private RelativeLayout relativeLayout;
    private Map<String,ArrayList<UserTicketListModel>> ticketGrpMap;
    private Map<String,ArrayList<HashMap<String,String>>> seatTypeMap;
//    private Map<String,GrpTicketModel> groupingMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        initView();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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

    private void init() {
        if(!SharedUtils.getRegisterBoolean(getBaseContext())){
            register();
//            getTheaterBeaconInfo();
            SharedUtils.putRegisterBoolean(getBaseContext(),true);
        }
        checkVerison();
        showDialogBar();
        initLists();
        setSideNav();
        checkBlueTooth();
        getdataFromServer();
//        getTheaterBeaconInfo();
    }

    private void register() {
        super.getPhoneInfo();

        //String deviceId,String phone1,String phone2,String phone3,String pushKey
        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        String deviceId = userInfo.getString("deviceId",null);
        phone1 = userInfo.getString("phone1",null);
        phone2 = userInfo.getString("phone2",null);
        phone3 = userInfo.getString("phone3",null);
        String pushKey = userInfo.getString("pushKey",null);

        super.setUser(deviceId, phone1, phone2, phone3, pushKey);

        checkPhonePermission();
//        requestLocationPermission();
    }

    private void checkPhonePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CONTACTS
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }

    private void showDialogBar() {
        asyncDialog = new ProgressDialog(this);
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        asyncDialog.show();
    }

    private void initLists() {
        bannerimageList = new ArrayList<>();
        mainTicketListModelList = new ArrayList<>();
        bottomTicketListModelArrayList = new ArrayList<>();
        salePlayLists = new ArrayList<>();
        beaconInfoModels = new ArrayList<>();
        userTicketListModelList = new ArrayList<>();
        seatDataModels = new ArrayList<>();
        prepaidList = new ArrayList<>();
        ticketGrpList = new ArrayList<>();
    }

    private void setSideNav() {
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void setToolBar() {
        relativeLayout = (RelativeLayout)findViewById(R.id.top_bar);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"clied");
            }
        });
        btnNav = (ImageButton)findViewById(R.id.btn_nav);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"button is clicked");
                if(!showNav){
                    drawerLayout.openDrawer(mNavigationView);
                    showNav = true;
                }else{
                    drawerLayout.closeDrawer(mNavigationView);
                    showNav = false;
                }
            }
        });
    }


//    private void setClickEvents() {
//        Button bookingBtn = (Button)findViewById(R.id.btn_booking);
//        bookingBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,BookingdetailActivity.class);
//                intent.putExtra("ticketInfo",new Gson().toJson(salePlayLists.get(0)));
//                startActivity(intent);
//            }
//        });
//
//    }

    private void getdataFromServer() {
        SharedPreferences userInfo = getSharedPreferences(GlobalValues.FILE_NAME,0);
        String enc_memberNum_ = userInfo.getString("enc_memberNum",null);
        String phone_ = userInfo.getString("phone",null);
        Log.d(TAG,"MainActivity getPublishReadyList enc_memberNum is"+enc_memberNum_);
        Log.d(TAG,"MainActivity getPublishReadyList phone is"+phone_);
        getSalePlayList();
//        if(SharedUtils.getPublishBoolean(getBaseContext())){
//            getPublishReadyList();

//            final GlobalValues globalValues = (GlobalValues)getApplication();
//            globalValues.getPublishReadyList(enc_memberNum_,phone_);
//
//            Log.d(TAG,"348/GlobalValues.publishReadyListModelList is"+GlobalValues.publishReadyListModelList);
//            Log.d(TAG,"349/GlobalValues.publishReadyListModelList is"+globalValues.getPublishReadyListModelList());

//            String resData = GlobalValues.fomatQesData_publish(GlobalValues.publishReadyListModelList);
//            Log.d(TAG,"resDATA is "+resData);
//
//            GlobalValues.requestPublishing(resData,enc_memberNum);

//            globalValues.getUserTicketList();
//
//            Map<String,GrpTicketModel> groupingMap_ =  globalValues.getGroupingMap();
//            Log.d(TAG," groupingMap =>"+groupingMap_);
//
//
//            globalValues.getMainBannerList();

            publishingTickets();


//            setAdBanner();
//            SharedUtils.putPublishBoolean(getBaseContext(),false);
//        }else{
//            GlobalValues.getUserTicketList(enc_memberNum);
//            getMainBa359/GlobalValues.groupingMapnnerList();
//        }
    }

    @Override
    public void setPublishingResponse(PublishingResponseModel publishingResponseModel_) {
        getUserTicketList();
    }

    @Override
    public void renderMainPageView(ArrayList<MainTicketListModel> mainTicketListModelList_, ArrayList<BottomTicketListModel> bottomTicketListModelArrayList_) {
        mainTicketListModelList = mainTicketListModelList_;
        bottomTicketListModelArrayList = bottomTicketListModelArrayList_;

        setViewPager(mainTicketListModelList_);
        setAdBanner(bottomTicketListModelArrayList_);
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


//    @Override
//    public void renderMainPageView() {
//        setAdBanner();
//        setViewPager();
//    }

    //    public void getUserTicketList1(String enc_memberNum) {
//        userTicketListModelList = new ArrayList<>();
//        Log.d(TAG,"getUserTicketList() enc_memberNum is=>"+GlobalValues.enc_memberNum);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Const.API_BASEURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        APIs api = retrofit.create(APIs.class);
//        Call<List<UserTicketListModel>> call = api.getUserTicketList(GlobalValues.enc_memberNum);
//
//        call.enqueue(new Callback<List<UserTicketListModel>>() {
//            @Override
//            public void onResponse(Call<List<UserTicketListModel>> call, retrofit2.Response<List<UserTicketListModel>> response) {
//                userTicketListModelList = response.body();
//                Log.d(TAG,"336/getUserTicketList is =>"+ userTicketListModelList);
//                if(userTicketListModelList != null && userTicketListModelList.size()>0){
//                    ticketGrpMap = MainActivity.super.groupingTicket(userTicketListModelList);
//                    final GlobalValues globalValues = (GlobalValues)getApplication();
//                    globalValues.setTicketGrpMap(ticketGrpMap);
//
//                    groupingMap = MainActivity.super.getGroupingList(userTicketListModelList);
//                    globalValues.setGroupingMap(groupingMap);
//
//                    seatTypeMap = MainActivity.super.statSeatType(ticketGrpMap);
//                    globalValues.setSeatTypeMap(seatTypeMap);
//
//                    Log.d(TAG," groupingMap is =>"+ groupingMap);
//                    Log.d(TAG," groupingMap size is =>"+ groupingMap.size());
//                    Log.d(TAG,"seatTypeMap is =>"+seatTypeMap);
//                    Log.d(TAG,"339/ ticketGrpList map is =>"+   ticketGrpMap );
//                    Log.d(TAG,"339/ ticketGrpList map size is=>"+   ticketGrpMap.size());
//                    addToMainList(groupingMap);
//                    getMainBannerList();
//                }else{
//                    getMainBannerList();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<UserTicketListModel>> call, Throwable t) {
//                Log.e(TAG,"getUserTicketLis failed");
//            }
//        });
//    }

    //광고 API 호출
//    private void getMainBannerList() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Const.API_BASEURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIs api = retrofit.create(APIs.class);
//
//        Call<List<AdBannerResponseModel>> call = api.getMainBannerList();
//
//        call.enqueue(new Callback<List<AdBannerResponseModel>>() {
//            @Override
//            public void onResponse(Call<List<AdBannerResponseModel>> call, retrofit2.Response<List<AdBannerResponseModel>> response) {
//                adBannerResponseModelList = response.body();
//                Log.d(TAG,"adBannerModelList is=>"+adBannerResponseModelList);
//                setAdBanner();
//            }
//
//            @Override
//            public void onFailure(Call<List<AdBannerResponseModel>> call, Throwable t) {
//                Log.e(TAG,"getMainBannerList failed =>"+t);
//            }
//        });
//    }

    private void setAdBanner(ArrayList<BottomTicketListModel> bottomTicketListModelArrayList_) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合

        for(int i = 0; i<bottomTicketListModelArrayList_.size();i++){
            bannerimageList.add(bottomTicketListModelArrayList_.get(i).getImg());
        }

        banner.setImages(bannerimageList);
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Intent intent = new Intent(MainActivity.this,BookingdetailActivity.class);
//                intent.putExtra("ticketInfo",new Gson().toJson(salePlayLists.get(0)));
//                startActivity(intent);
                int childId_ad = bottomTicketListModelArrayList_.get(position).getChildId();
                String type_ad = bottomTicketListModelArrayList_.get(position).getType();
                switch (type_ad){
                    case "1"://행당항목 광고 일때
                        int actionType  = GlobalValues.adBannerResponseModelList.get(childId_ad).getActionType();
                        AdBannerResponseModel ad_item_bottom = GlobalValues.adBannerResponseModelList.get(childId_ad);
                        String bannerData = ad_item_bottom.getBannerData();
                        switch (actionType){
                            case 1://url 통해서 새로운 페이지 열린다
                                try {
                                    JSONObject jsonObject = new JSONObject(bannerData);
                                    String url = jsonObject.getString("url");
                                    Log.d(TAG,"actiion type 1 url is=>"+url);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                break;
                            case 2://티켓 예매 리스트의 enc_playNum 비교하고 예매 상세 페이지 가는다
                                try {
                                    JSONObject jsonObject = new JSONObject(bannerData);
                                    String enc_playNum = jsonObject.getString("enc_playNum");
                                    String enc_playSaleNum = jsonObject.getString("enc_playSaleNum");
                                    Log.d(TAG,"bottom ad bar actiion type 2 enc_playNum is=>"+enc_playNum);
                                    Log.d(TAG,"bottom ad bar actiion type 2 enc_playSaleNum is=>"+enc_playSaleNum);
                                    findSomeNumFromList(enc_playNum,enc_playSaleNum);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3://새로운 페이지에서 광고 상세 이메지를 열린다
                                String detailImg =  ad_item_bottom.getDetailImg();
                                Log.d(TAG,"actiion type 3 detailImg is=>"+detailImg);

                                break;
                        }
                        break;
                    case "2"://행당항목 예매 일때
                        break;
                }
            }
        });
        banner.start();
        Log.e(TAG,"adbanner is render");
    }

//    private void getPublishReadyList() {
//        Log.d(TAG,"getPublishReadyList phone =>"+GlobalValues.getPhone());
//        Log.d(TAG,"getPublishReadyList enc_memberNum =>"+GlobalValues.getEnc_memberNum());
//
//        reqDataList = new ArrayList<>();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Const.API_BASEURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        APIs api = retrofit.create(APIs.class);
//        Call<List<PublishReadyListModel>> call = api.getPublishReadyList(GlobalValues.getEnc_memberNum(),GlobalValues.getPhone());
//        call.enqueue(new Callback<List<PublishReadyListModel>>() {
//            @Override
//            public void onResponse(Call<List<PublishReadyListModel>> call, retrofit2.Response<List<PublishReadyListModel>> response) {
//                publishReadyListModelList = response.body();
//                GlobalValues.setPublishReadyListModelList(publishReadyListModelList);
//                try{
//                    Log.d(TAG," publishReadyListModel list is=>"+publishReadyListModelList);
//                    Log.d(TAG," publishReadyListModel size is=>"+publishReadyListModelList.size());
//                    if( publishReadyListModelList!= null && publishReadyListModelList.size()>0){
//
//                        for(PublishReadyListModel publishReadyListModel:publishReadyListModelList){
//                            reqDatasString = reqDatasString +"{\"enc_ticketNum\":\""+publishReadyListModel.getEnc_ticketNum()+"\","+"\"enc_validKey\":\""+publishReadyListModel.getEnc_validKey()+"\"},";
//                        }
//                        String reqDatasString_format = reqDatasString.substring(0,reqDatasString.length()-1);
//                        requestPublishing("["+reqDatasString_format+"]");
//                    }else{
//                        GlobalValues.getUserTicketList(GlobalValues.enc_memberNum);
//                    }
//                }catch (Exception e){
//                    Log.e(TAG,"getPublishReadyList()=>"+e);
//                }
//
//
//            }
//            @Override
//            public void onFailure(Call<List<PublishReadyListModel>> call, Throwable t) {
//                Log.e(TAG,"getPublishReadyList failed"+t);
//            }
//        });
//    }

//    private void requestPublishing(String reqDatas) {
//        HashMap<String,String> map = new HashMap<>();
//        map.put("enc_memberNum",enc_memberNum);
//        map.put("reqData",reqDatas);
//        Log.d(TAG,"reqData is=>"+reqDatas);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Const.API_BASEURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        APIs api = retrofit.create(APIs.class);
//        Call<PublishingResponseModel> call = api.requestPublishing(map);
//        call.enqueue(new Callback<PublishingResponseModel>() {
//            @Override
//            public void onResponse(Call<PublishingResponseModel> call, retrofit2.Response<PublishingResponseModel> response) {
//                publishingResponseModel = response.body();
//                Log.d(TAG,"티켓 발행 요청/publishingResponseMode lList is =>"+ publishingResponseModel);
//                GlobalValues.getUserTicketList(GlobalValues.enc_memberNum);
//            }
//            @Override
//            public void onFailure(Call<PublishingResponseModel> call, Throwable t) {
//                Log.e(TAG,"티켓 발행 요청/requestPublishing failed"+t);
//            }
//        });
//    }

    private void getSalePlayList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<List<BookingListModel>> call = api.getSalePlayList(GlobalValues.ENC_STORENUM);

        call.enqueue(new Callback<List<BookingListModel>>() {
            @Override
            public void onResponse(Call<List<BookingListModel>> call, retrofit2.Response<List<BookingListModel>> response) {
                salePlayLists = response.body();
                Log.d(TAG,"getSalePlayList is=>"+salePlayLists);
                Log.d(TAG,"getEnc_playNum() is=>"+salePlayLists.get(0).getEnc_playNum());
                Log.d(TAG,"Enc_playSaleNum() is=>"+salePlayLists.get(0).getEnc_playSaleNum());
            }

            @Override
            public void onFailure(Call<List<BookingListModel>> call, Throwable t) {
                Log.d(TAG,"getSalePlayList failed");
            }
        });
    }

    private void setViewPager(ArrayList<MainTicketListModel> mainTicketListModelList_) {
        Log.d(TAG,"615/mainTicketListModelLists is =>"+mainTicketListModelList_);
        MainTicketListAdapter adapter = new MainTicketListAdapter(this,mainTicketListModelList_);
        horizontalInfiniteCycleViewPager.setAdapter(adapter);
        asyncDialog.dismiss();
    }

    private void initView() {
        horizontalInfiniteCycleViewPager = (HorizontalInfiniteCycleViewPager)findViewById(R.id.horizontal_cycle);
        backgroundContainer = (ImageView) findViewById(R.id.ticket_container);
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);
        banner = (Banner)findViewById(R.id.banner);
        webView = (WebView)findViewById(R.id.webview);
    }

    public void checkBlueTooth() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();
        //支持蓝牙模块
        if (blueadapter != null) {
            if (blueadapter.isEnabled()) {
//                Toast tst = Toast.makeText(getApplicationContext(), "블루투스 이미 부트했습니다", Toast.LENGTH_SHORT);
//                tst.show();
                initBeaconScaner();
            } else {
                new AlertDialog.Builder(getApplicationContext()).setTitle("블루투스 커진，부트 하시겠습니까?")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (turnOnBluetooth()) {
                                    Toast tst = Toast.makeText(getApplicationContext(), "블루투스 부트 성공", Toast.LENGTH_SHORT);
                                    tst.show();
                                    initBeaconScaner();
                                } else {
                                    Toast tst = Toast.makeText(getApplicationContext(), "블루투스 부트 실페", Toast.LENGTH_SHORT);
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
            Toast tst = Toast.makeText(getApplicationContext(), "this device not support buletooth", Toast.LENGTH_SHORT);
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

        Snackbar.make(mLayout,"Location permission is needed to monitor or range beacons.", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION);
                    }
                })
                .show();
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

            if (BeaconProximityUuid_s.equals(BeaconProximityUuid) || BeaconMajor_s.equals(BeaconMajor)) {
                if (BeaconMinor.equals("9404") || !RequestTicketing) {
                    Log.e(TAG, "9404인식된다");
                    isPrinted = true;
                    String verifyCode = "6785";
//                    RequestTicketing();
                    RequestTicketing = true;
                }
                if (BeaconMinor.equals("9403") || !Entrance) {
                    Log.e(TAG, "9403인식된다");
//                    Entrance();
                    Entrance = true;
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id  = item.getItemId();
        switch (id){
            case R.id.nav_gift:
                intent = new Intent(this,TicketPresentListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    //infiniteCycleViewPager adapter
    public class MainTicketListAdapter extends PagerAdapter {
        private Context context;
        private ArrayList<MainTicketListModel> mainTicketList;
        private LayoutInflater mlayoutInflater;

        public MainTicketListAdapter(Context context,ArrayList<MainTicketListModel> mainTicketList) {
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
            String ticketImg = String.valueOf(mainTicketList.get(position).getPosterImg());
            //set ticket poster image
            Picasso.with(context).load(ticketImg).placeholder(R.drawable.ic_image_background).into(imageView);
            //set background image blur effect
//            Picasso.with(context).load(ticketImg).transform(new BlurTransformation(context, 25, 1)).placeholder(R.drawable.t1_img_bg).into(backgroundContainer);
            //get ticket title from ticket infomation array list
            String ticketTit = mainTicketList.get(position).getTicketTitle();
            //set tikcet title
            ticketTitle.setText(ticketTit);
            //main poster image click event
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ticketType =  mainTicketListModelList.get(position).getType();
                    int chlidId_ad =  mainTicketListModelList.get(position).getChildListId();
                    if(ticketType.equals("1")){
                        //행당 항목 광고 일떼
                        AdBannerResponseModel ad_item = GlobalValues.adBannerResponseModelList.get(chlidId_ad);
                        int actionType = ad_item.getActionType();
                        String bannerData;
                        switch (actionType){
                            case 1://url 통해서 새로운 페이지 열린다
                                bannerData = ad_item.getBannerData();
                                try {
                                    JSONObject jsonObject = new JSONObject(bannerData);
                                    String url = jsonObject.getString("url");
                                    Log.d(TAG,"actiion type 1 url is=>"+url);

                                    webView.getSettings().setJavaScriptEnabled(true);
                                    webView.loadUrl(url);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2://티켓 예매 리스트의 enc_playNum 비교하고 예매 상세 페이지 가는다
                                bannerData = ad_item.getBannerData();
                                try {
                                    JSONObject jsonObject = new JSONObject(bannerData);
                                    String enc_playNum = jsonObject.getString("enc_playNum");
                                    String enc_playSaleNum = jsonObject.getString("enc_playSaleNum");
                                    Log.d(TAG,"actiion type 2 enc_playNum is=>"+enc_playNum);
                                    Log.d(TAG,"actiion type 2 enc_playSaleNum is=>"+enc_playSaleNum);
                                    findSomeNumFromList(enc_playNum,enc_playSaleNum);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3://새로운 페이지에서 광고 상세 이메지를 열린다
                                String detailImg = ad_item.getDetailImg();
                                Log.d(TAG,"actiion type 3 detailImg is=>"+detailImg);
                                Intent intent = new Intent(MainActivity.this,AdDetailActivity.class);
                                intent.putExtra("detailImg",detailImg);
                                intent.putExtra("adType","1");
                                startActivity(intent);
                                break;
                        }
                    }else if(ticketType.equals("0")){
                        //행당 항목 보요티켓 일떼
                        String key = mainTicketListModelList.get(position).getKey();
                        String playTitle = GlobalValues.groupingMap.get(key).getPlayTitle();
                        ArrayList<HashMap<String,String>> list = new ArrayList<>();
                        list = GlobalValues.seatTypeMap.get(key);

                        Intent intent =new Intent(MainActivity.this, TicketDetailActivitys.class);
                        Bundle bundle = new Bundle();
                        ArrayList bundleList = new ArrayList();
                        bundleList.add(list);
                        bundle.putParcelableArrayList("seatList",bundleList);
                        intent.putExtras(bundle);
                        intent.putExtra("playTitle",playTitle);
                        intent.putExtra("key",key);
                        startActivity(intent);
                    }
                }
            });
            container.addView(view);
            return view;
        }

    }

    private void findSomeNumFromList(String enc_playNum, String enc_playSaleNum) {
            for(int i=0 ;i<salePlayLists.size();i++){
                String enc_playNum_list =salePlayLists.get(i).getEnc_playNum();
                String enc_playSaleNum_list = salePlayLists.get(i).getEnc_playSaleNum();
                Log.d(TAG,"enc_playNum_list is=>"+enc_playNum_list);
                Log.d(TAG,"enc_playNum_list is=>"+enc_playNum_list);
                if(enc_playNum_list.equals(enc_playNum)&&enc_playSaleNum_list.equals(enc_playSaleNum)){
                    Intent intent = new Intent(MainActivity.this,BookingdetailActivity.class);
//                    intent.putExtra("infoFromMain",new Gson().toJson(salePlayLists.get(i)));
                    ACache mCache = ACache.get(this);
                    mCache.put("salePlayInfo",new Gson().toJson(salePlayLists.get(i)));
                    BookingListModel bookingListModel = salePlayLists.get(i);
                    String t01 = bookingListModel.getEnc_playSaleNum();
                    String t02 = bookingListModel.getEnc_playNum();
                    Log.d(TAG,"getEnc_playSaleNum()"+t01);
                    Log.d(TAG,"getEnc_playNum()"+t02);
                    startActivity(intent);
                }else {
                    Log.d(TAG,"해당 티켓이 없습니다");
                }
            }
        Log.d(TAG,"salePlayLists.size() is"+salePlayLists.size());
    }

    //set banner image
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load(String.valueOf(path)).into(imageView);
        }
    }

    public void getTheaterBeaconInfo(){
        String enc_theaterNum = "D9RhYu5Vp3UuFglDZUCyeA%3D%3D";
        String authKey = "30bdc9349e6b4a92a56af9bb5cde1952";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs api = retrofit.create(APIs.class);
        Call<ResponseBody> call = api.getTheaterBeaconInfo(authKey,enc_theaterNum);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                String res = response.body().toString();
                Log.d(TAG,"BeaconInfoModel is=>"+res);
//                GlobalValues.setBeaconInfoModels(requestBody);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"getTheaterBeaconInfo failed"+t);
            }
        });
    }

    public void checkVerison() {
        com.github.javiersantos.appupdater.AppUpdater appUpdater = new AppUpdater(this);
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
                        Toast.makeText(MainActivity.this, "업데이트 페이지 가겠습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButtonDismiss("나중에")
                .setButtonDismissClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "취소 버튼 누렸습니다.", Toast.LENGTH_SHORT).show();
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

    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new android.support.v7.app.AlertDialog.Builder(this)
                    .setTitle("Permission Request")
                    .setMessage("")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    0);
                        }
                    })
                    .setIcon(R.drawable.back1)
                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    0);
        }
    }

//        private void addToMainList(Map<String,GrpTicketModel> groupingMap) {
//            Log.d(TAG,"groupingMap is"+groupingMap);
//            if(groupingMap == null){
//                return;
//            }
//            for(GrpTicketModel grpTicketModel:groupingMap.values()){
//                mainTicketListModelList.add(new MainTicketListModel(grpTicketModel.getPlayTitle(),0,grpTicketModel.getImg(),TYPE_MY,grpTicketModel.getKey()));
//            }
//    }
}
