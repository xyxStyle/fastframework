package com.qianfan.fastframework.retrofit;

/**
 * 接口返回值基类
 *
 * @author WangJing on 2016/10/10 0010 16:39
 * @e-mail wangjinggm@gmail.com
 * @see [相关类/方法](可选)
 */

public class BaseCallEntity<T> {
    public int status;
    public String  error;
    public T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
