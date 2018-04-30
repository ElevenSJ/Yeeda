package com.sj.yeeda.Utils;

import android.support.annotation.Keep;

import java.io.File;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:文件工具类
 */
@Keep
public class FileUtils {

    //网络缓存目录
    public static String APP_DIR = "/YeeDa";

    //用户数据本地序列化文件名
    public static String FILE_NAME_USERINFO = "user.obj";

    //客服账号本地序列化文件名
    public static String FILE_NAME_IMACCOUNT = "imaccount.obj";

    //图片缓存目录
    public static String CACHE_IMAGE = APP_DIR+"/.CacheImages";

    //网络缓存目录
    public static String CACHE_NET = APP_DIR+"/.CacheNet";

    public static void init(){
        File rootDir = new File(APP_DIR);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File netDir = new File(CACHE_NET);
        if (!netDir.exists()) {
            netDir.mkdir();
        }

        File imageDir = new File(CACHE_IMAGE);
        if (!imageDir.exists()) {
            imageDir.mkdir();
        }
    }
}
