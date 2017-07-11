package com.why.project.okhttputilsbasedemo.utils.httputil;

import android.content.Context;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Used 判断网络连接的封装类
 */
public class HttpUtil {
	
    /**
     * 仅仅用来判断是否有网络连接
     */
    public static boolean isNetworkAvailable(Context context) {
        if (Configs.TEST_FLAG == 0)
        {
            return true;
        } else {
            return NetWorkHelper.isNetworkAvailable(context);
        }
    }
    
    /**
     * 判断是否有可用的网络连接
     * */
    public static boolean isNetworkConnected(Context context) {
        if (Configs.TEST_FLAG == 0)
        {
            return true;
        } else {
            return NetWorkHelper.isNetworkConnected(context);
        }
    }
    
    /**
     * 判断mobile网络是否可用
     */
    public static boolean isMobileDataEnable(Context context) {
        String TAG = "httpUtils.isMobileDataEnable()";
        try {
            return NetWorkHelper.isMobileDataEnable(context);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 判断wifi网络是否可用
     */
    public static boolean isWifiDataEnable(Context context) {
        String TAG = "httpUtils.isWifiDataEnable()";
        try {
            return NetWorkHelper.isWifiDataEnable(context);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 判断是否为漫游
     */
    public static boolean isNetworkRoaming(Context context) {
        return NetWorkHelper.isNetworkRoaming(context);
    }
    
    /**
     * 编码测试
     */
    public static void testCharset(String datastr) {
        try {
            String temp = new String(datastr.getBytes(), "GBK");
            Log.v("TestCharset", "****** getBytes() -> GBK ******/n" + temp);
            temp = new String(datastr.getBytes("GBK"), "UTF-8");
            Log.v("TestCharset", "****** GBK -> UTF-8 *******/n" + temp);
            temp = new String(datastr.getBytes("GBK"), "ISO-8859-1");
            Log.v("TestCharset", "****** GBK -> ISO-8859-1 *******/n" + temp);
            temp = new String(datastr.getBytes("ISO-8859-1"), "UTF-8");
            Log.v("TestCharset", "****** ISO-8859-1 -> UTF-8 *******/n" + temp);
            temp = new String(datastr.getBytes("ISO-8859-1"), "GBK");
            Log.v("TestCharset", "****** ISO-8859-1 -> GBK *******/n" + temp);
            temp = new String(datastr.getBytes("UTF-8"), "GBK");
            Log.v("TestCharset", "****** UTF-8 -> GBK *******/n" + temp);
            temp = new String(datastr.getBytes("UTF-8"), "ISO-8859-1");
            Log.v("TestCharset", "****** UTF-8 -> ISO-8859-1 *******/n" + temp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}