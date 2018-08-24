package com.clipservice.eticket.common;

import com.clipservice.eticket.models.AdBannerResponseModel;
import com.clipservice.eticket.models.BeaconInfoModel;
import com.clipservice.eticket.models.BookingListModel;
import com.clipservice.eticket.models.EntranceResModel;
import com.clipservice.eticket.models.PublishReadyListModel;
import com.clipservice.eticket.models.PublishingResponseModel;
import com.clipservice.eticket.models.RollbackSendModel;
import com.clipservice.eticket.models.SendTicketResModel;
import com.clipservice.eticket.models.SequenceListModel;
import com.clipservice.eticket.models.SetTicketPrintGrpResModel;
import com.clipservice.eticket.models.SetUserResModel;
import com.clipservice.eticket.models.TicketingResItemModel;
import com.clipservice.eticket.models.UserTicketListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIs {

    @GET("/Booking/App/GetSalePlayList")
    Call<List<BookingListModel>> getSalePlayList(@Query("enc_storeNum") String enc_storeNum);

    @GET("/Ticket/App/GetUserTicketList")
    Call<List<UserTicketListModel>> getUserTicketList(@Query("enc_memberNum") String enc_memberNum);

    @GET("/Ticket/App/GetPublishReadyList")
    Call<List<PublishReadyListModel>> getPublishReadyList(@Query("enc_memberNum") String enc_memberNum, @Query("phone") String phone);

    @GET("/Ad/App/GetMainBannerList")
    Call<List<AdBannerResponseModel>> getMainBannerList();

//    @GET(" /Common/App/GetTheaterBeaconInfo")
//    Call<ArrayList<BeaconInfoModel>> getTheaterBeaconInfo(@Query("authKey") String authKey,@Query("enc_theaterNum") String enc_theaterNum);
    @GET(" /Common/App/GetTheaterBeaconInfo")
    Call<ResponseBody> getTheaterBeaconInfo(@Query("authKey") String authKey, @Query("enc_theaterNum") String enc_theaterNum);

    @GET("/Booking/App/GetSequenceList")
    Call<List<SequenceListModel>> getSequenceList(@Query("enc_playNum") String enc_playNum, @Query("enc_playSaleNum") String enc_playSaleNum);

    @FormUrlEncoded
    @POST("/Membership/App/SetUser")
    Call<SetUserResModel> setUser(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("/Membership/App/SetUserIdentity")
    Call<RequestBody> setUserIdentity(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST(" /Ticket/App/RequestPublishing")
    Call<PublishingResponseModel> requestPublishing(@FieldMap HashMap<String, String> data);

    @GET("/Ticket/App/GetUserTicketList")
    Call<List<UserTicketListModel>> getUserTicketList(@Query("authKey") String authKey, @Query("enc_memberNum") String enc_memberNum);

    @FormUrlEncoded
    @POST("/Ticket/App/RequestTicketing")
    Call<ArrayList<TicketingResItemModel>>requestTicketing(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("/Ticket/App/Entrance")
    Call<EntranceResModel>entrance(@FieldMap HashMap<String, String> data);

    //티켓을 묶음처리 하기 위해 그룹값을 재설정합니다.
    @FormUrlEncoded
    @POST("/Ticket/App/SetTicketPrintGrp")
    Call<SetTicketPrintGrpResModel>setTicketPrintGrp(@FieldMap HashMap<String, String> data);

    //묶음처리된 티켓을 각각의 주문 단위로 그룹값 초기화묶음
    @FormUrlEncoded
    @POST("/Ticket/App/ReleaseTicketPrintGrp")
    Call<ResponseBody>releaseTicketPrintGrp(@FieldMap HashMap<String, String> data);

    //티켓 선물하기
    @FormUrlEncoded
    @POST("/Ticket/App/SendTicket")
    Call<ResponseBody>sendTicket(@FieldMap HashMap<String, String> data);

    @FormUrlEncoded
    @POST("/Ticket/App/RollbackSend")
    Call<RollbackSendModel>rollbackSend(@FieldMap HashMap<String, String> data);


    //티켓 확인용 색상코드 반환
    @FormUrlEncoded
    @POST("/Ticket/App/GetTodayTicketColor")
    Call<ResponseBody>getTodayTicketColor(@FieldMap HashMap<String, String> data);
}
