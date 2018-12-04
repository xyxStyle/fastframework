package com.qianfan.fastframework.db;

import com.qianfan.fastframework.entity.Student;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by wangjing on 2017/3/10.
 */

public class StudentHelper extends BaseDbHelper<Student,Long>{
    public StudentHelper(AbstractDao dao) {
        super(dao);
    }
}
