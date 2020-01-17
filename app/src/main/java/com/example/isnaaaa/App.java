package com.example.isnaaaa;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;

public class App extends Application {
    public void onCreat(){
        super.onCreate();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
        AndroidNetworking.initialize(this,okHttpClient);
    }
}
