package com.qianfan.fastframework.retrofit;

import com.qianfan.fastframework.utils.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 自定义网络请求回调
 *
 * @author WangJing on 2016/10/10 0010 16:48
 * @e-mail wangjinggm@gmail.com
 * @see @http://apidoc.qianfanyun.com/index.php
 */

public abstract class MyCallback<T extends BaseCallEntity> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int httpcode = response.raw().code();
        if (httpcode == 200) {//200是服务器有合理响应
            int errorcode = response.body().status;
            switch (errorcode) {
                case 0:
                    if (response != null && response.body().toString() != null) {
                        onSuc(response);
                    } else {
                        onFail("返回的内容不能为空哦！");
                    }
                    break;
                default:
                    if (response != null && response.body().toString() != null) {
                        onSucOther(response);
//                        ToastUtil.TShort(_mActivity, response.body().status + " " + response.body().error);
                    } else {
                        onFail("返回的内容不能为空哦！");
                    }
//                    onFail(errorcode + " " + response.body().error);
                    break;
            }
        } else if (httpcode == 401) {//登录状态丢失过期或者未登录
//            LoginOutUtils.logout();
        } else {//服务器响应失败
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
            LogUtil.e("MyCallback", "" + new RuntimeException("response error,detail = " + response.raw().toString()));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {//网络问题会走该回调
        if (t instanceof SocketTimeoutException) {

        } else if (t instanceof ConnectException) {

        } else if (t instanceof RuntimeException) {

        }
        onFail(t.getMessage());
    }

    /**
     * 请求成功，并且返回值status为0
     *
     * @param response
     */

    public abstract void onSuc(Response<T> response);

    /**
     * 请求成功，但是status不为0
     *
     * @param response
     */
    public abstract void onSucOther(Response<T> response);

    /**
     * 服务器响应失败
     *
     * @param message
     */
    public abstract void onFail(String message);

}
