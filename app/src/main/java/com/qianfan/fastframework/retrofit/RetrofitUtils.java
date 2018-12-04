package com.qianfan.fastframework.retrofit;

import com.qianfan.fastframework.BuildConfig;
import com.qianfan.fastframework.MyApplication;
import com.qianfan.fastframework.utils.DeviceUtil;
import com.qianfan.fastframework.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Retrofit封装Utils
 *
 * @author WangJing on 2016/10/10 0010 16:37
 * @e-mail wangjinggm@gmail.com
 * @see [相关类/方法](可选)
 */

public class RetrofitUtils {
    private static Retrofit retrofit;
    private static OkHttpClient.Builder okhttpBuilder;
    private static HttpLoggingInterceptor interceptor;

    private static final String URL_HOST = "http://www.baidu.com";

    public static <S> S creatApi(Class<S> serviceClass) {
        synchronized (RetrofitUtils.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(URL_HOST)
                        //设置 Json 转换器
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getHttpClient())
                        .build();
            }
            return retrofit.create(serviceClass);
        }
    }

    /**
     * 不包含baseurl
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S creatApiNoBaseUrl(Class<S> serviceClass) {
        synchronized (RetrofitUtils.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        //设置 Json 转换器
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getHttpClient())
                        .build();
            }
            return retrofit.create(serviceClass);
        }
    }

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    private static OkHttpClient getHttpClient() {
        synchronized (RetrofitUtils.class) {
            if (okhttpBuilder == null) {
                okhttpBuilder = new OkHttpClient.Builder();
                //设置超时
                okhttpBuilder.connectTimeout(30, TimeUnit.SECONDS);
                okhttpBuilder.readTimeout(30, TimeUnit.SECONDS);
                okhttpBuilder.writeTimeout(30, TimeUnit.SECONDS);
                //错误重连
                okhttpBuilder.retryOnConnectionFailure(true);
                //设置请求头Header
                okhttpBuilder.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // addHeader("User-Agent", URLEncoder.encode(AppConfig.USERAGENT, "UTF-8"))
                        Request request = chain.request().newBuilder()
                                .header("Content-Type", "application/json;charset=UTF-8")//数据格式
//                                .header("Authorization", "" + MyApplication.getToken())//用户登录状态
                                .header("Device-platform", "2")//设备平台：1IOS 2ANDROID 99未知
                                .header("Device-imei", "" + DeviceUtil.getDeviceImei())//设备IMEI
                                .header("Device-imsi", "" + DeviceUtil.getDeviceImsi())//设备IMSI
                                .header("Device-token", "" + DeviceUtil.getDeviceToken())//设备TOKEN
                                .header("Device-system", "" + DeviceUtil.getMANUFACTURER_MODEL())//设备系统
                                .header("Device-systemver", "" + DeviceUtil.getSystemVersion())//设备系统版本
                                .header("Device-network", "" + NetworkUtils.getNetworkType())//设备网络状况1:WIFI 2:流量 99未知
                                .build();
                        return chain.proceed(request);
                    }
                });
                //缓存机制,无网络时，也能显示数据
                File cacheFile = new File(MyApplication.getmContext().getExternalCacheDir(), "ZongHengCache");
                Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);//设置缓存大小50M
                Interceptor cacheInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (!NetworkUtils.isNetworkConnected()) {//如果没有网络
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        if (NetworkUtils.isNetworkConnected()) {
                            int maxAge = 0;
                            // 有网络时 设置缓存超时时间0个小时
                            response.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .removeHeader("ZongHeng")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                    .build();
                        } else {
                            // 无网络时，设置超时为4周
                            int maxStale = 60 * 60 * 24 * 28;
                            response.newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .removeHeader("nyn")
                                    .build();
                        }
                        return response;
                    }
                };
                okhttpBuilder.cache(cache).addInterceptor(cacheInterceptor);
            }

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                if (interceptor == null) {
                    interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                }
                //设置Debug Log 模式
                okhttpBuilder.addInterceptor(interceptor);
            }

            //以上设置结束，才能build(),不然设置白搭
            OkHttpClient okHttpClient = okhttpBuilder.build();
            return okHttpClient;
        }
    }
}
