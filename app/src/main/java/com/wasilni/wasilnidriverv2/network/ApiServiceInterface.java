package com.wasilni.wasilnidriverv2.network;


import com.wasilni.wasilnidriverv2.mvp.model.Ride;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceInterface {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("booking/{booking}/action")
    Call<Response<Ride>> ChangeState(@Header("Authorization") String Authorization , @Path("booking") int booking, @Field("status") String status);

}
