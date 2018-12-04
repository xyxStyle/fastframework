package com.qianfan.fastframework.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.qianfan.fastframework.MyApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * DeviceUtil
 *
 * @author wangjing on 2016/10/24 09:30
 * @e-mail wangjinggm@gmail.com
 * @see @http://www.cnblogs.com/tyjsjl/p/3585051.html
 */

public class DeviceUtil {
    private static String Imei = null;
    private static String Imsi = null;
    private static String DeviceToken = null;
    private static String System_Version = null;
    private static String MANUFACTURER_MODEL = null;//手机厂商+型号
    private static TelephonyManager tm;


    public static String getDeviceImsi() {
        if (TextUtils.isEmpty(Imsi)) {
            Imsi = TextUtils.isEmpty(getTm().getSubscriberId()) ? " " : getTm().getSubscriberId();
        }
        return "" + Imsi;
    }

    public static String getDeviceImei() {
        if (TextUtils.isEmpty(Imei)) {
            Imei = ((TelephonyManager) MyApplication.getmContext().getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        }
        if (TextUtils.isEmpty(Imei)) {
            Imei = " ";
        }
        return "" + Imei;
    }

    /**
     * 获取设备唯一识别号
     *
     * @return
     */
    public static String getDeviceToken() {
        if (DeviceToken == null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) MyApplication.getmContext()
                        .getSystemService(Context.TELEPHONY_SERVICE);
                // String device = Secure.getString(mContext.getContentResolver(),
                // Secure.ANDROID_ID); //需要设备注册谷歌账户，跟上面的方法获取的值不一样
                // LogUtil.i(TAG, "Secure device：" + device);
                if (TextUtils.isEmpty(telephonyManager.getDeviceId())) {
                    DeviceToken = "000000000000000";
                } else {
                    DeviceToken = telephonyManager.getDeviceId();
                }
            } catch (Exception e) {
                DeviceToken = "000000000000000";
            }
        }
        return DeviceToken;
    }

    /**
     * 获取TelephonyManager
     *
     * @return
     */
    private static TelephonyManager getTm() {
        if (tm == null) {
            tm = (TelephonyManager) MyApplication.getmContext().getSystemService(TELEPHONY_SERVICE);
        }
        return tm;
    }

    /**
     * 获取手机厂商+型号
     *
     * @return
     */
    public static String getMANUFACTURER_MODEL() throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(MANUFACTURER_MODEL)) {
            MANUFACTURER_MODEL = Build.MANUFACTURER + "-" + Build.MODEL.replace(" ", "");
        }
        return URLEncoder.encode("" + MANUFACTURER_MODEL, "UTF-8");
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getSystemVersion() {
        if (TextUtils.isEmpty(System_Version)) {
            System_Version = "" + Build.VERSION.SDK_INT;
        }
        return System_Version;
    }

    /**
     * @return String
     * @description: 获取md5加密后的设备id
     */
    public static String getDeviceId() {
        return getMD5String(getDeviceToken());
    }

    /**
     * @param before
     * @return String
     * @description: 获取md5加密后的字符串
     */
    public static String getMD5String(String before) {
        String after = MD5.getCode(before);
        return after;
    }
}
