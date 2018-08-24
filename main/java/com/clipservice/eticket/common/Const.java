package com.clipservice.eticket.common;

/**
 * Created by SGJin on 2017-08-30.
 */

public class Const {
    private final static String TAG = "Const";
    public static final String AUTHKEY = "30bdc9349e6b4a92a56af9bb5cde1952";

    //DATABASE
    public static final String DATABASE_NAME="cloudticket.db";//database name
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "userInfo";
    public static final String ENC_AUTHKEY = "enc_authKey";
    public static final String ENC_MEMBERNUM = "enc_memberNum";
    public static final String PHONE_NUM = "phone_num";

    //비콘 설정
    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";
    public static final boolean SCAN_RECO_ONLY = false;
    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;
    public static final boolean DISCONTINUOUS_SCAN = false;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_LOCATION = 10;

    //발권API
    public static final String API_BASEURL = "http://api-test.cloudticket.co.kr";
    public static final String API_GETPUBLISHREADYLIST = API_BASEURL+"/Ticket/App/GetPublishReadyList";//발행가능 티켓리스트 받기
    public static final String API_REQUESTPUBLISHING = API_BASEURL+"/Ticket/App/RequestPublishing";//발행 대기중인 티켓에 대한 사용자 티켓 발행을 요청합니다
    public static final String API_REQUESTTICKETING = API_BASEURL+"/Ticket/App/RequestTicketing";//티켓의 발권을 요청
    public static final String API_ENTRANCE = API_BASEURL+"/Ticket/App/Entrance";//관객 객석 입장 처리
    public static final String API_GETUSERTICKETLIST = API_BASEURL+"/Ticket/App/GetUserTicketList";//보유중인 티켓 리스트

    //예매API
    public static final String API_GETSALEPLAYLIST = API_BASEURL +"/Booking/App/GetSalePlayList";//현재 예매 가능한 공연 리스트를 반환합니다
    public static final String API_GETSEQUENCELIST = API_BASEURL +"/Booking/App/GetSequenceList";//현재 예매 가능한 회차 목록을 반환합니다.
    public static final String API_GETPLAYSALEINFO = API_BASEURL +"/Booking/App/GetPlaySaleInfo";//지정 회차의 판매정보를 반환합니다.
    public static final String API_ORDERING = API_BASEURL +"/Booking/App/Ordering";//결제정보와 함께 주문요청을 진행합니다
    public static final String API_SENDTICKET = API_BASEURL +"/Ticket/App/SendTicket";//티켓 선물하기
    //멤버십API
    public static final String API_SETUSER = API_BASEURL +"/Membership/App/SetUser";

    public static final String SP_NAME = "CLOUDTICKET";

}
