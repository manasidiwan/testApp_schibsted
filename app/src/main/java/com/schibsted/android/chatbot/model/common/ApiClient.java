package com.schibsted.android.chatbot.model.common;

/**
 * Created by manasidiwan on 01/08/2016.
 */
import retrofit2.Retrofit;
import retrofit2.GsonConverterFactory;
import retrofit2.RxJavaCallAdapterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://s3-eu-west-1.amazonaws.com";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}