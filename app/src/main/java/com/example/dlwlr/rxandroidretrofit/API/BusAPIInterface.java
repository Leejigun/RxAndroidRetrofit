package com.example.dlwlr.rxandroidretrofit.API;

import com.example.dlwlr.rxandroidretrofit.Model.Bus;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusAPIInterface {
    //    http://bis.naju.go.kr:8080/json/arriveAppInfo?BUSSTOP_ID=410
//    버스 정류장 아이디로 버스 정보 불러오기
    @GET("json/arriveAppInfo")
    Observable<Bus> getData(@Query("BUSSTOP_ID") Integer busStopID);
    @GET("json/arriveAppInfo")
    Call<Bus> getData2(@Query("BUSSTOP_ID") Integer busStopID);
}
