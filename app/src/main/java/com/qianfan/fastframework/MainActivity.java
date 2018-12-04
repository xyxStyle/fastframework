package com.qianfan.fastframework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qianfan.fastframework.ViewImage.ViewImageActivity;
import com.qianfan.fastframework.greendao.TestGreenDaoActivity;
import com.qianfan.fastframework.testretrofit.TestRetrofitActivity;
import com.qianfan.fastframework.viewpagerfragment.ViewPagerFragmentActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_viewpagerfragment, btn_retrofit, btn_greendao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_viewpagerfragment = (Button) findViewById(R.id.btn_viewpagerfragment);
        btn_retrofit = (Button) findViewById(R.id.btn_retrofit);
        btn_greendao = (Button) findViewById(R.id.btn_greendao);

        btn_viewpagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerFragmentActivity.class);
                startActivity(intent);
            }
        });
        btn_retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestRetrofitActivity.class));
            }
        });
        btn_greendao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestGreenDaoActivity.class));
            }
        });
    }

    public void viewImage(View view) {
        startActivity(new Intent(MainActivity.this, ViewImageActivity.class));
    }
}
