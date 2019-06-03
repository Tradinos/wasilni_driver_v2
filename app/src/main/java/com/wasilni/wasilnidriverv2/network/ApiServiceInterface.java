package com.wasilni.wasilnidriverv2.network;


import com.wasilni.wasilnidriverv2.mvp.model.Bank;
import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.BookingCause;
import com.wasilni.wasilnidriverv2.mvp.model.Brand;
import com.wasilni.wasilnidriverv2.mvp.model.BrandModel;
import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.model.Color;
import com.wasilni.wasilnidriverv2.mvp.model.Location;
import com.wasilni.wasilnidriverv2.mvp.model.Nationality;
import com.wasilni.wasilnidriverv2.mvp.model.Payment;
import com.wasilni.wasilnidriverv2.mvp.model.RegisterCaptain;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
import com.wasilni.wasilnidriverv2.mvp.model.User;
import com.wasilni.wasilnidriverv2.mvp.model.pojo.PaginationAPI;

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
    @PUT("booking/{booking}/action")
    Call<Response<Booking>> ChangeBookingState(@Header("Authorization") String Authorization , @Path("booking") int booking);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("booking/{id}/rate")
    Call<Response<BookingCause>> Rate(@Header("Authorization") String Authorization , @Path("id") int id, @Field("rating_cause_id") int rating_cause_id , @Field("extra") String extra);

    @Headers("Accept: application/json")
    @GET("captain_rating_cause")
    Call<Response<PaginationAPI<List<Cause>>>> GetCauses(@Header("Authorization") String Authorization  , @Query("perPage") int perPage);

    @Headers("Accept: application/json")
    @GET("daily_report")
    Call<Response<PaginationAPI<List<Cause>>>> dailyReport(@Header("Authorization") String Authorization  , @Query("perPage") int perPage);

    @Headers("Accept: application/json")
    @GET("ride")
    Call<Response<PaginationAPI<Ride>>> GetRides(@Header("Authorization") String Authorization  ,@Query("perPage") int perPage,@Query("filter") String filter);

    @Headers("Accept: application/json")
    @GET("ride/{ride_id}/bookings")
    Call<Response<Ride>> GetRideBookings(@Header("Authorization") String Authorization  , @Path("ride_id") int ride_id, @Query("perPage") int perPage);


    @Headers("Accept: application/json")
    @PUT("check")
    Call<Response<Boolean>> ChangeDriverState(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("booking/{id}/pay")
    Call<Response<Payment>> Pay(@Header("Authorization") String Authorization , @Path("id") int id, @Field("passenger_paid_amount") String passenger_paid_amount );

    @Headers("Accept: application/json")
    @POST("notification_token")
    Call<Response> FCMToken(@Header("Authorization") String Authorization ,@Query("notification_token") String notification_token);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("request/verify")
    Call<Response<User>> VerifyCode(@Field("phone_number") String phone_number  , @Field("activation_code") String activation_code  , @Field("provider") String provider);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("request/activation_code")
    Call<Response<User>> RequestActivationCode(@Field("phone_number") String phone_number, @Field("provider") String provider);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login")
    Call<Response<User>> Login(@Field("username") String phone_number,@Field("password") String password, @Field("provider") String provider);

    @Headers("Accept: application/json")
    @POST("captain")
    Call<Response<User>> CompleteInfo(@Body RegisterCaptain registerCaptain);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("interview")
    Call<Response<Object>> Interview(@Header("Authorization") String Authorization ,@Field("datetime") String datetime);

    @Headers("Accept: application/json")
    @GET("color")
    Call<Response<PaginationAPI<Color>>> Colors(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @GET("location")
    Call<Response<PaginationAPI<Location>>> Locations(@Header("Authorization") String Authorization, @Query("search") String search );

    @Headers("Accept: application/json")
    @GET("car_brand")
    Call<Response<PaginationAPI<Brand>>> Brands(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @GET("car_brand")
    Call<Response<PaginationAPI<Brand>>> searchBrands(@Header("Authorization") String Authorization , @Query("search") String search);

    @Headers("Accept: application/json")
    @GET("nationality")
    Call<Response<PaginationAPI<Nationality>>> Nationalities(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @GET("bank")
    Call<Response<PaginationAPI<Bank>>> Banks(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @GET("car_brand/{car_brand_id}/models")
    Call<Response<PaginationAPI<BrandModel>>> BrandModels(@Header("Authorization") String Authorization, @Path("car_brand_id") int carBrandId);

    @Headers("Accept: application/json")
    @GET("check_auth")
    Call<Response<User>> GetUserData(@Header("Authorization") String token);
}
