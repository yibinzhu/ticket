package com.clipservice.eticket.ui.ticket.ticketBookingDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookingDetailApi {
    @GET("/Booking/App/GetSequenceList")
    Call<List<SequenceListModel>> getSequenceList(@Query("authKey") String authKey,@Query("enc_playNum") String enc_playNum,@Query("enc_playSaleNum") String enc_playSaleNum);

    @GET("/Booking/App/GetPlaySaleInfo")
    Call<Object> getPlaySaleInfo(@Query("authKey") String authKey,@Query("enc_playNum") String enc_playNum,@Query("enc_playSaleNum") String enc_playSaleNum,@Query("enc_sequence") String enc_sequence);
}
