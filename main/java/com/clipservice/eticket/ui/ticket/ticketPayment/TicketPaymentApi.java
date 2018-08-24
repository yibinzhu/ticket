package com.clipservice.eticket.ui.ticket.ticketPayment;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface TicketPaymentApi {
    @POST("/Booking/App/Ordering")
    Call<PaymentResponseModel> ticketOdering(@QueryMap HashMap<String, String> data);
}
