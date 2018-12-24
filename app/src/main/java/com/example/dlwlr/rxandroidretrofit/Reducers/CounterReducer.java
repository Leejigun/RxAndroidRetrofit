package com.example.dlwlr.rxandroidretrofit.Reducers;

import com.example.dlwlr.rxandroidretrofit.Actions.CounterActions;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

@AutoReducer
abstract public class CounterReducer implements Reducer<Integer> {
    @AutoReducer.InitialState
    int initialState() {
        return 0;
    }

    @AutoReducer.Action(value = CounterActions.INCREMENT,from = CounterActions.class)
    int increment(int state) {
        return state + 1;
    }

    @AutoReducer.Action(value = CounterActions.DECREMENT,from = CounterActions.class)
    int decrement(int state) {
        return state - 1;
    }

    @AutoReducer.Action(value = CounterActions.ADD, from = CounterActions.class)
    int add(int state, int value) {
        return state + value;
    }

    public static CounterReducer create() {
        return new CounterReducerImpl();
    }
}
