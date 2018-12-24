package com.example.dlwlr.rxandroidretrofit.Views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dlwlr.rxandroidretrofit.Actions.TodoActions;
import com.example.dlwlr.rxandroidretrofit.Model.TodoItem;
import com.example.dlwlr.rxandroidretrofit.R;
import com.example.dlwlr.rxandroidretrofit.Reducers.TodoReducer;
import com.example.dlwlr.rxandroidretrofit.State.TodoState;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.StateChangeListener;
import com.yheriatovych.reductor.Store;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class TodoActivity extends AppCompatActivity {

    Store<TodoState> todoStore = Store.create(TodoReducer.create());
    Integer id = 0;

    @BindView(R.id.nameLabel)
    EditText nameeditTxt;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.todoList)
    TextView todoList;

    /* RxJava DisposeBag */
    ArrayList<Disposable> disposeBag = new ArrayList<>();
    /* Action 모음 */
    TodoActions actions = Actions.from(TodoActions.class);

    /**
     * store 구독
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);
        todoStore.subscribe(new StateChangeListener<TodoState>() {
            @Override
            public void onStateChanged(TodoState state) {
                Integer size = state.getItems().size();
                Log.d(this.getClass().getName(),"addedCount ::" + size.toString());
                todoList.setText("");
                for(TodoItem item: state.getItems()){
                    todoList.append(item.getText() + "\n");
                }
            }
        });
    }

    /**
     * RxEvent 생성
     */
    @Override
    protected void onResume() {
        super.onResume();
        Observable<String> addBtnEvents = RxView.clicks(addBtn)
                .map(avoid -> nameeditTxt.getText().toString());
        Observable<String> doneEvents = RxTextView.editorActions(nameeditTxt)
                .map(avoid -> nameeditTxt.getText().toString());
        Observable<String> together = Observable.merge(addBtnEvents,doneEvents);
        Disposable addEvents = together.filter(name -> name.length() > 0)
                .doOnNext(avoid -> {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nameeditTxt.getWindowToken(),0);
                })
                .subscribe(
                        name -> {
                            TodoItem item = new TodoItem(generateId(),name,false);
                            todoStore.dispatch(actions.addItem(item));
                            nameeditTxt.setText("");
                        }
                );
    }

    /**
     * 일치하지 유니크한 아이디 값 생성을 위해 사용
     * @return
     */
    Integer generateId() {
        id = id + 1;
        return id;
    }
}
