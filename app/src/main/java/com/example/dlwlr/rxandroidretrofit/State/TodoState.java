package com.example.dlwlr.rxandroidretrofit.State;

import com.example.dlwlr.rxandroidretrofit.Model.TodoItem;

import java.util.List;

/**
 * TodoState 의 상태값 저장
 */
public class TodoState {
    List<TodoItem> items;

    public TodoState(List<TodoItem> items) {
        this.items = items;
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public void setItems(List<TodoItem> items) {
        this.items = items;
    }
}
