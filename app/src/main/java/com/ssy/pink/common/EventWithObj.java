package com.ssy.pink.common;

/**
 * @author ssy
 * @date 2017/12/21
 */
public class EventWithObj<T> {
    public int eventCode;
    public T obj;

    public EventWithObj(int eventCode, T obj) {
        this.eventCode = eventCode;
        this.obj = obj;
    }
}
