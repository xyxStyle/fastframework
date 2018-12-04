package com.qianfan.fastframework.db;


import com.qianfan.fastframework.dao.StudentDao;

/**
 * 文 件 名: DbUtil
 * 说   明:  获取表 Helper 的工具类
 * 创 建 人: WangJing
 * 创建日期: 16-7-19 10:44
 */
public class DbUtil {
    private static StudentHelper studentHelper;

    private static StudentDao getOtherLoginEntityDao() {
        return DbCore.getDaoSession().getStudentDao();
    }

    public static StudentHelper getStudentHelper() {
        if (studentHelper == null) {
            studentHelper = new StudentHelper(getOtherLoginEntityDao());
        }
        return studentHelper;
    }
}
