package com.example.dlwlr.rxandroidretrofit;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dlwlr.rxandroidretrofit.API.BusAPI;
import com.example.dlwlr.rxandroidretrofit.Model.Bus;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";
    BusAPI apiProvider = new BusAPI();

    @BindView(R.id.editText)
    EditText busStopIDTxt;
    @BindView(R.id.button)
    Button searchBtn;
    @BindView(R.id.textView)
    TextView resultView;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.resultView.setText("Loading~~~");
//        Call<Bus> res = apiProvider.getData(410);
//        res.enqueue(new Callback<Bus>() {
//            @Override
//            public void onResponse(Call<Bus> call, Response<Bus> response) {
//                Log.d(TAG,call.request().url().toString());
//                resultView.setText(response.body().getResult().getResultCode());
//            }
//
//            @Override
//            public void onFailure(Call<Bus> call, Throwable t) {
//                Log.e(TAG,t.getLocalizedMessage());
//            }
//        });
//        apiProvider.getBusDataObservable(Integer.parseInt("410"))
//                .subscribe(
//                       bus -> {
//                           Log.d(TAG, "code:" +bus.getResult().getResultCode() + "msg" + bus.getResult().getResultMsg());
//                       },
//                        e-> { this.resultView.setText(e.getLocalizedMessage());}
//                );
        RxView.clicks(searchBtn)
                .map(n -> this.busStopIDTxt.getText().toString())
                .filter(n -> n.length() > 0)
                .doOnNext(n -> Log.d(TAG,n))
                .flatMap(n -> apiProvider.getBusDataObservable(Integer.parseInt(n)))
                .doOnNext(bus -> Log.d(TAG,bus.getResult().getResultCode()))
                .doOnError(e -> Log.e("TAG",e.getLocalizedMessage()))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(n -> resultView.setText(n.getResult().getResultCode()),
                        e -> resultView.setText("애러발생 !!" + e.getLocalizedMessage()));
    }

    void callUsingLambda() {
        Log.d(TAG,"callUsingLambda");
        Observable.interval(1,TimeUnit.SECONDS)
                .map(n -> this.busStopIDTxt.getText().toString())
                .filter(n -> n.length() > 0)
                .flatMap(n -> apiProvider.getBusDataObservable(Integer.parseInt("041")))
                .subscribe(
                        n -> resultView.setText(n.toString()),
                        e -> Log.e(TAG,e.getLocalizedMessage())
                );
    }
    @OnClick(R.id.button)
    void clickSearchingBtn() {
        Log.d(TAG,"clickSearchingBtn");
    }
}
