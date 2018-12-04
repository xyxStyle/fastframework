package com.qianfan.fastframework.testretrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfan.fastframework.R;
import com.qianfan.fastframework.base.BaseActivity;
import com.qianfan.fastframework.entity.TestEntity;
import com.qianfan.fastframework.retrofit.BaseCallEntity;
import com.qianfan.fastframework.retrofit.MyCallback;
import com.qianfan.fastframework.retrofit.RetrofitUtils;
import com.qianfan.fastframework.service.TestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wangjing on 2017/3/8.
 */

public class TestRetrofitActivity extends BaseActivity {
    private Button btn_getdata;
    private TextView tv_data;
    private Call<BaseCallEntity<List<TestEntity>>> call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfetrofit);
        btn_getdata = (Button) findViewById(R.id.btn_getdata);
        tv_data = (TextView) findViewById(R.id.tv_data);
        btn_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestRetrofitActivity.this, "暂无可用接口测试", Toast.LENGTH_SHORT).show();
//                getData();
            }
        });
    }

    private void getData() {
        call = RetrofitUtils.creatApi(TestService.class).getExam11List();
        call.enqueue(new MyCallback<BaseCallEntity<List<TestEntity>>>() {
            @Override
            public void onSuc(Response<BaseCallEntity<List<TestEntity>>> response) {

            }

            @Override
            public void onSucOther(Response<BaseCallEntity<List<TestEntity>>> response) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call!=null){
            call.cancel();
        }
    }
}
