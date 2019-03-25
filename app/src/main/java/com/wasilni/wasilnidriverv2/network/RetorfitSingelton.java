package com.wasilni.wasilnidriverv2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetorfitSingelton {

    private static Retrofit retrofit;
    public static final String URL ="http://192.168.9.148:8000";
    public static final String BASE_URL = URL+"/api/";

    private RetorfitSingelton() {
    }

    /**
     * Create an instance of Retrofit object
     * */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            creatInstance();
        }
        return retrofit;
    }

    public static void creatInstance(){
         retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
