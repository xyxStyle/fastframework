package com.qianfan.fastframework.service;

import com.qianfan.fastframework.entity.TestEntity;
import com.qianfan.fastframework.retrofit.BaseCallEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/8.
 */

public interface TestService {
    /**
     * 获取小车科目一考题
     *
     * @return
     */
    @GET("http://zongheng-quan.oss-cn-shanghai.aliyuncs.com/exam/json/11_exam.json")
    Call<BaseCallEntity<List<TestEntity>>> getExam11List();
}
