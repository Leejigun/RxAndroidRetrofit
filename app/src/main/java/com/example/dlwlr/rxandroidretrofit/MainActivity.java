package com.example.dlwlr.rxandroidretrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dlwlr.rxandroidretrofit.API.BusAPI;
import com.example.dlwlr.rxandroidretrofit.Model.Bus;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";
    BusAPI apiProvider = new BusAPI();
    ArrayList<Disposable> disposeBag = new ArrayList<>();

    @BindView(R.id.editText)
    EditText busStopIDTxt;
    @BindView(R.id.button)
    Button searchBtn;
    @BindView(R.id.button2)
    Button nextBtn;
    @BindView(R.id.textView)
    TextView resultView;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        super.onStart();
        /* 검색 버튼 클릭 이벤트 */
        Observable<String> clickEvent = RxView.clicks(searchBtn)
                .map(n -> this.busStopIDTxt.getText().toString());
        /* 키보드 완료 버튼 클릭 이벤트 */
        Observable<String> doneEvent = RxTextView.editorActions(busStopIDTxt)
                .map(n -> this.busStopIDTxt.getText().toString());
        /* 이벤트 Merge */
        Observable<String> together = Observable.merge(clickEvent,doneEvent);
        /* 이벤트 Subscription */
        Disposable events = together.subscribeOn(AndroidSchedulers.mainThread())
        .filter(n -> n.length() > 0)
        .doOnNext(avoid -> { /* 키보드 내리기*/
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.busStopIDTxt.getWindowToken(),0);
        })
        .observeOn(Schedulers.newThread())
        .flatMap(n -> apiProvider.getBusDataObservable(Integer.parseInt(n))) /*API 통신*/
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(n -> { /*결과 화면에 출력*/
            resultView.append(n.getResult().getResultCode() + "\n");
            resultView.append(n.getResult().getResultMsg() + "\n");
            for(int i = 0; i< n.getBusStopListList().size(); i++){
                resultView.append(n.getBusStopListList().get(i).getBUSSTOPNAME() + "\n");
            }
            },
                e -> resultView.setText(e.getLocalizedMessage())
        );

        Disposable nextEvent = RxView.clicks(nextBtn)
        .subscribe(
                avoid -> {
                    Intent intent = new Intent(this, Main2Activity.class);
                    startActivity(intent);
                }
        );
        /* Disposable 저장 */
        disposeBag.add(events);
        disposeBag.add(nextEvent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /* all Item dispose */
        for(int i = 0; i<disposeBag.size(); i++) {
            Disposable item = disposeBag.get(i);
            if(item != null && !item.isDisposed()) {
                item.dispose();
            }
        }
    }

    @OnClick(R.id.button)
    void clickSearchingBtn() {
        Log.d(TAG,"clickSearchingBtn");
    }
}
