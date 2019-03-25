package com.wasilni.wasilnidriverv2.network;

import com.wasilni.wasilnipassengerv2.mvp.model.Booking;
import com.wasilni.wasilnipassengerv2.mvp.model.Service;
import com.wasilni.wasilnipassengerv2.mvp.model.User;
import com.wasilni.wasilnipassengerv2.mvp.model.pojo.PaginationAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceInterface {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("request/activation_code")
    Call<Response> requestActivationCode(@Field("phone_number") String phone_number, @Field("provider") String provider);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("request/verify")
    Call<Response<User>> verifyCode(@Field("phone_number") String phone_number, @Field("activation_code") String activation_code, @Field("provider") String provider);

    @Headers("Accept: application/json")
    @POST("booking")
    Call<Response<Booking>> PostBook(@Header("Authorization") String Authorization, @Body Booking book);

    @Headers("Accept: application/json")
    @GET("service")
    Call<Response<PaginationAPI<List<Service>>>> getServices(@Header("Authorization") String Authorization, @Query("perPage") int perPage);


}
