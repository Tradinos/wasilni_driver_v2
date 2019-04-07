package com.wasilni.wasilnidriverv2.network;


import com.wasilni.wasilnidriverv2.mvp.model.Booking;
import com.wasilni.wasilnidriverv2.mvp.model.BookingCause;
import com.wasilni.wasilnidriverv2.mvp.model.Cause;
import com.wasilni.wasilnidriverv2.mvp.model.Ride;
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
    @FormUrlEncoded
    @PUT("booking/{booking}/action")
    Call<Response<Booking>> ChangeBookingState(@Header("Authorization") String Authorization , @Path("booking") int booking, @Field("status") String status);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("booking/{id}/rate")
    Call<Response<BookingCause>> Rate(@Header("Authorization") String Authorization , @Path("id") int id, @Field("rating_cause_id") int rating_cause_id , @Field("extra") String extra);

    @Headers("Accept: application/json")
    @GET("captain_rating_cause")
    Call<Response<PaginationAPI<List<Cause>>>> GetCauses(@Header("Authorization") String Authorization  , @Query("perPage") int perPage);

    @Headers("Accept: application/json")
    @PUT("check")
    Call<Response<Boolean>> ChangeDriverState(@Header("Authorization") String Authorization );

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("booking/{id}/pay")
    Call<Response> Pay(@Header("Authorization") String Authorization , @Path("id") int id,@Field("passenger_paid_amount") String passenger_paid_amount );


}
