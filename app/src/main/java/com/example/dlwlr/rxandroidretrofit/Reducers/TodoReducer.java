package com.example.dlwlr.rxandroidretrofit.Reducers;

import com.example.dlwlr.rxandroidretrofit.Actions.TodoActions;
import com.example.dlwlr.rxandroidretrofit.Model.TodoItem;
import com.example.dlwlr.rxandroidretrofit.State.TodoState;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

import java.util.ArrayList;

@AutoReducer
abstract public class TodoReducer implements Reducer<TodoState> {
    @AutoReducer.InitialState
    TodoState init() {
        return new TodoState(new ArrayList<>());
    }
    @AutoReducer.Action(value = TodoActions.ADD_ITEM,from = TodoActions.class)
    TodoState addItem(TodoState state,TodoItem item) {
        state.getItems().add(item);
        return state;
    }
    @AutoReducer.Action(value = TodoActions.CHANGE_STATE,from = TodoActions.class)
    TodoState changeState(TodoState state, long id, boolean isChecked) {
        for(TodoItem item: state.getItems()) {
            if(item.getId() == id) {
                item.setChecked(isChecked);
                return state;
            }
        }
        return state;
    }
    public static TodoReducer create() {
        return new TodoReducerImpl();
    }
}
