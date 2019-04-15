package com.wasilni.wasilnidriverv2.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetorfitSingelton {

    private static Retrofit retrofit;
    public static final String URL ="http://192.168.9.175:8000";
//    public static final String URL ="http://wasilni.com:8899";
//    public static final String URL ="http://192.168.9.170:8000";
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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

         retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
