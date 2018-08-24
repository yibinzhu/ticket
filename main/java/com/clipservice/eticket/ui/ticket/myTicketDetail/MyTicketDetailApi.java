package com.clipservice.eticket.ui.ticket.myTicketDetail;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

public interface MyTicketDetailApi {
    @POST("/Ticket/App/GetTodayTicketColor")
    Call<ResponseBody> getTodayTicketColor();

}
