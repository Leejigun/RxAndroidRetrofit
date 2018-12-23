package com.example.dlwlr.rxandroidretrofit.API;

import com.example.dlwlr.rxandroidretrofit.Model.Bus;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.*;

public class BusAPI {
//    http://bis.naju.go.kr:8080/json/arriveAppInfo?BUSSTOP_ID=410
    static private String BASE_URL = "http://bis.naju.go.kr:8080/";

    public Observable<Bus> getBusDataObservable(Integer busID) {
//      1. Retrofit 클라이언트 생성
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//       2. RestAPI 서비스 생성
        BusAPIInterface service = client.create(BusAPIInterface.class);
        Observable<Bus> busData = service.getData(busID);
        return busData;
    }

    public Call<Bus> getData(Integer busID) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//       2. RestAPI 서비스 생성
        BusAPIInterface service = client.create(BusAPIInterface.class);
        return service.getData2(busID);
    }
}
