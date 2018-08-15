package com.ssy.pink.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * Created by Administrator on 2016/12/12.
 */

public class JsonUtils {

    public static String toString(Object obj) throws JSONException {
        try {
            return JSON.toJSONString(obj);
        } catch (JSONException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static <T> T toObject(String jsonString, Class<T> cls) throws JSONException {
        try {
            return JSON.parseObject(jsonString, cls);
        } catch (JSONException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}


