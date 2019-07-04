package com.tradinos.wasilnidriver.mvp.model;

public class EventBusMessage<T> {
    private String ActivityName ;
    private T data ;

    public EventBusMessage(String activityName, T data) {
        ActivityName = activityName;
        this.data = data;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
