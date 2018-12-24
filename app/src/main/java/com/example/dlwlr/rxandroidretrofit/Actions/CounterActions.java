package com.example.dlwlr.rxandroidretrofit.Actions;

import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

@ActionCreator
public interface CounterActions {
    String INCREMENT = "INCREMENT";
    String DECREMENT = "DECREMENT";
    String ADD = "ADD";

    @ActionCreator.Action(INCREMENT)
    Action increment();

    @ActionCreator.Action(DECREMENT)
    Action decrement();

    @ActionCreator.Action(ADD)
    Action add(int value);
}
