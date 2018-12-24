package com.example.dlwlr.rxandroidretrofit.Views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.dlwlr.rxandroidretrofit.Actions.CounterActions;
import com.example.dlwlr.rxandroidretrofit.R;
import com.example.dlwlr.rxandroidretrofit.Reducers.CounterReducer;
import com.jakewharton.rxbinding3.view.RxView;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.StateChangeListener;
import com.yheriatovych.reductor.Store;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //MARK: - Views
    @BindView(R.id.countTextView)
    TextView counterTextView;
    @BindView(R.id.increaseBtn)
    Button increaseBtn;
    @BindView(R.id.decreaseBtn)
    Button decreaseBtn;
    @BindView(R.id.addTenBtn)
    Button addTenBtn;

    /* Redux Store */
    Store<Integer> counterStore = Store.create(CounterReducer.create());
    ArrayList<Disposable> disposeBag = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        counterStore.subscribe(new StateChangeListener<Integer>() {
            @Override
            public void onStateChanged(Integer state) {
                counterTextView.setText(counterStore.getState().toString());
            }
        });
        /* +1 버튼 이벤트 리스너 */
        Disposable increase = RxView.clicks(increaseBtn)
        .subscribe(n -> {
            CounterActions actions = Actions.from(CounterActions.class);
            counterStore.dispatch(actions.increment());
        });
        /* -1 버튼 이벤트 리스너 */
        Disposable decrease = RxView.clicks(decreaseBtn)
        .subscribe(n -> {
            CounterActions actions = Actions.from(CounterActions.class);
            counterStore.dispatch(actions.decrement());
        });
        /* 10 추가 버튼 이벤트 리스너 */
        Disposable addTen = RxView.clicks(addTenBtn)
        .subscribe( n -> {
            CounterActions actions = Actions.from(CounterActions.class);
            counterStore.dispatch(actions.add(10));
        });
        /* disposeBag 에 담기 */
        disposeBag.add(increase);
        disposeBag.add(decrease);
        disposeBag.add(addTen);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /* 메모리 관리 */
        for(Disposable item : disposeBag) {
            item.dispose();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
