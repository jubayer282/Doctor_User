package com.jubayer.doctorsappinmentsystemuser.chatbot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<MsgModel> getMessage(@Url String url);
}
