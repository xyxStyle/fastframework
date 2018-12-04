package com.qianfan.fastframework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qianfan.fastframework.MyApplication;


/**
 * 网络判断方法类
 *
 * @author WangJing on 2016/10/12 0012 09:30
 * @e-mail wangjinggm@gmail.com
 * @see @http://www.2cto.com/kf/201507/424212.html
 */

public class NetworkUtils {

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getmContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 获取当前网络类型
     *
     * @return 99：没有网络/未知   1：WIFI网络   2：流量
     */
    public static int getNetworkType() {
        ConnectivityManager connMgr = (ConnectivityManager) MyApplication.getmContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return 99;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return 2;
//            if (networkInfo.getExtraInfo().toLowerCase().equals(cmnet)) {
//                return netType.CMNET;
//            } else {
//                return netType.CMWAP;
//            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return 1;
        }
        return 99;

    }

}
