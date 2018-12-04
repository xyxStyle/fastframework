package com.qianfan.fastframework.greendao;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfan.fastframework.R;
import com.qianfan.fastframework.base.BaseActivity;
import com.qianfan.fastframework.db.DbUtil;
import com.qianfan.fastframework.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */

public class TestGreenDaoActivity extends BaseActivity {
    private EditText edittext;
    private Button btn_add, btn_query;
    private RecyclerView recyclerView;
    private TestGreenDaoActivityAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testgreendao);

        edittext = (EditText) findViewById(R.id.edittext);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_query = (Button) findViewById(R.id.btn_query);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestGreenDaoActivityAdapter(this);
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edittext.getText().toString())) {
                    Student student = new Student();
                    student.setName(edittext.getText().toString());
                    DbUtil.getStudentHelper().save();
                    edittext.setText("");
                    Toast.makeText(TestGreenDaoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    adapter.addData(DbUtil.getStudentHelper().queryAll());
                } else {
                    Toast.makeText(TestGreenDaoActivity.this, "请输入保存内容", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData(DbUtil.getStudentHelper().queryAll());
            }
        });
    }


    public class TestGreenDaoActivityAdapter extends RecyclerView.Adapter<TestGreenDaoActivityAdapter.ItemViewHolder> {
        private Context mContext;

        public TestGreenDaoActivityAdapter(Context mContext) {
            this.mContext = mContext;

        }

        private List<Student> infos = new ArrayList<>();

        @Override
        public TestGreenDaoActivityAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testgreendao, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TestGreenDaoActivityAdapter.ItemViewHolder holder, final int position) {
            final Student info = infos.get(position);
            holder.tv_name.setText("" + info.getName());
            holder.btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DbUtil.getStudentHelper().delete(info);
                    infos.remove(position);
                    notifyDataSetChanged();
                }
            });

            holder.btn_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(edittext.getText().toString())) {
                        Toast.makeText(mContext, "请输入修改的内容", Toast.LENGTH_SHORT).show();
                    } else {
                        info.setName(edittext.getText().toString());
                        DbUtil.getStudentHelper().update(info);
                        edittext.setText("");
                        Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                        notifyItemChanged(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return infos == null ? 0 : infos.size();
        }

        public void addData(List<Student> infos) {
            if (infos != null) {
                this.infos = infos;
                notifyDataSetChanged();
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            TextView tv_name;
            Button btn_del, btn_change;

            public ItemViewHolder(View itemView) {
                super(itemView);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                btn_del = (Button) itemView.findViewById(R.id.btn_del);
                btn_change = (Button) itemView.findViewById(R.id.btn_change);
            }
        }
    }
}
