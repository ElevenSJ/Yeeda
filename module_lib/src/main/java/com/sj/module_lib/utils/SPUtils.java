package com.sj.module_lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: on 2018/4/1.
 * 创建人: 孙杰
 * 功能描述:sharepreferences 工具类
 */
public class SPUtils {

    public static Context mContext;

    private static volatile SPUtils spUtils;


    private Map<String,SharedPreferences> sharedPreferences = new HashMap<>();

    /*
     * 保存手机里面的名字
     */
    private SharedPreferences.Editor editor;

    public static SPUtils getInstance() {
        if(mContext == null) {
            Logger.e("SharedPreferences please call the method of init() first");
        }
        if(spUtils == null) {
            synchronized(SPUtils.class) {
                if(spUtils == null) {
                    spUtils = new SPUtils();
                }
            }
        }
        return spUtils;
    }

    public static void init(Context context){
        mContext = context.getApplicationContext();
    }
    public SPUtils edit(String FILE_NAME){
        if(mContext == null) {
            Logger.e("SharedPreferences please call the method of init() first");
            return spUtils;
        }
        if(sharedPreferences.containsKey(FILE_NAME)){
            editor = sharedPreferences.get(FILE_NAME).edit();
        }else{
            SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
            sharedPreferences.put(FILE_NAME,sp);
        editor = sp.edit();
        }
        return spUtils;
    }

    /**
     * 存储同步commit
     */
    public void commit(String key, Object object) {
        if(editor == null) {
            Logger.e("SharedPreferences please call the method of edit()");
            return;
        }
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 存储异步commit
     */
    public void apply(String key, Object object) {
        if(editor == null) {
            Logger.e("SharedPreferences please call the method of edit()");
            return;
        }
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 获取保存的数据
     */
    public Object getSharedPreference(String FILE_NAME,String key, Object defaultObject) {
        if (sharedPreferences.containsKey(FILE_NAME)){
            return null;
        }
        if (defaultObject instanceof String) {
            return sharedPreferences.get(FILE_NAME).getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.get(FILE_NAME).getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.get(FILE_NAME).getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.get(FILE_NAME).getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.get(FILE_NAME).getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.get(FILE_NAME).getString(key, null);
        }
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String FILE_NAME,String key) {
        if (sharedPreferences.containsKey(FILE_NAME)){
            return false;
        }
        return sharedPreferences.get(FILE_NAME).contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll(String FILE_NAME) {
        if (sharedPreferences.containsKey(FILE_NAME)){
            return null;
        }
        return sharedPreferences.get(FILE_NAME).getAll();
    }
}
