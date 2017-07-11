package com.why.project.okhttputilsbasedemo.utils.httputil;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Used 网络连接的判断：是否有网络、网络是否可用、判断是wifi还是3G、判断wifi是否可用、判断3G是否可用
 * 权限 android.permission.ACCESS_NETWORK_STATE
 */
public class NetWorkHelper {
    /**
     * 仅仅用来判断是否有网络连接
     */
    public static boolean isNetworkAvailable(Context context) {
    	// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
        	// 获取当前活动的NetworkInfo对象
    		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
    		if (networkInfo != null) {
    			/*Log.d(TAG, "info.isAvailable=="+info.isAvailable());*/
    			return networkInfo.isAvailable();
    		}
        }
        return false;
    }
    
    /**
     * 判断是否有可用的网络连接
     * */
    /*
	 * 只打开3G连接的时候：
	 * 0===类型===MOBILE
	 * 0===状态===CONNECTED
	 * 打开wifi连接和3G连接的时候：
	 * 0===类型===MOBILE
	 * 0===状态===DISCONNECTED
	 * 1===类型===WIFI
	 * 1===状态===CONNECTED
	 * */
    public static boolean isNetworkConnected(Context context) {
    	
        boolean netstate = false;
        
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (manager != null)
        {
            NetworkInfo[] networkInfo = manager.getAllNetworkInfo();
            if (networkInfo != null) {
                for (int i = 0; i < networkInfo.length; i++)
                {
                	/*Log.d(TAG,i + "===类型===" + info[i].getTypeName());
                	Log.d(TAG,i + "===状态===" + info[i].getState());*/
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }
    
    /**
     * 判断是否是wifi,用户的体现性在这里了，wifi就可以建议下载或者在线播放
     * */
    public static boolean isWifi(Context context) {   
        ConnectivityManager manager = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();   
        if (networkInfo != null   
                && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {   
            return true;   
        }   
        return false;   
    }
    
    /**
     * 判断wifi 是否可用
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) throws Exception {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        
        boolean isWifiDataEnable = false;
        
        isWifiDataEnable = manager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }
    
    /**
     * 判断是否是3G网络
     * */
    public static boolean is3rd(Context context) {   
        ConnectivityManager manager = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();   
        if (networkInfo != null   
                && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
            return true;   
        }   
        return false;   
    }
    
    /**
     * 判断3G网络是否可用
     * 
     * @param context
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) throws Exception {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileDataEnable = false;
        isMobileDataEnable = manager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();

        return isMobileDataEnable;
    }
    
    /**
     * 判断网络是否为漫游
     */
    public static boolean isNetworkRoaming(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
        	NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null && tm.isNetworkRoaming()) {
                    return true;
                } else {
                }
            }
        }
        return false;
    }
    /**
     * 判断GPS是否打开
     * */
    public static boolean isGpsEnabled(Context context) {   
        LocationManager lm = ((LocationManager) context   
                .getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);   
        return accessibleProviders != null && accessibleProviders.size() > 0;   
    }
    
}