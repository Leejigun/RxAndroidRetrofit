package com.example.dlwlr.rxandroidretrofit.Actions;

import com.example.dlwlr.rxandroidretrofit.Model.TodoItem;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

@ActionCreator
public interface TodoActions {
    public static final String ADD_ITEM = "ADD_ITEM";
    public static final String CHANGE_STATE = "CHANGE_STATE";

    @ActionCreator.Action(ADD_ITEM)
    Action addItem(TodoItem item);

    @ActionCreator.Action(CHANGE_STATE)
    Action changeState(long id, boolean isChecked);
}
