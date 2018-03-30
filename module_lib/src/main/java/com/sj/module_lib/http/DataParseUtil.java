package com.sj.module_lib.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务器返回数据的解析工具；
 * 支持XML,json对象,json数组
 * </p>
 *
 * @author 张华洋 2017/1/9 16:00
 * @version V1.2.0
 * @name DataParseUtil
 */


public class DataParseUtil {

    private DataParseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 解析json对象
     *
     * @param string 要解析的json
     * @param clazz  解析类
     */
    public static <T> T parseObject(String string, Class<T> clazz) {
        return new Gson().fromJson(string, clazz);
    }

    /**
     * 解析json数组为ArrayList
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return ArrayList
     */
    public static <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     * 解析json数组为List
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return List
     */
    public static <T> List<T> parseToList(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }


}
