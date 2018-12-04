/*
******************* Copyright (c) ***********************\
**
**         (c) Copyright 2016, wangjing, china, qianfan. sd
**                  All Rights Reserved
**
**                 By(青岛世新科技有限公司)
**                    www.qianfanyun.com
**
**                       _oo0oo_
**                      o8888888o
**                      88" . "88
**                      (| -_- |)
**                      0\  =  /0
**                    ___/`---'\___
**                  .' \\|     |// '.
**                 / \\|||  :  |||// \
**                / _||||| -:- |||||- \
**               |   | \\\  -  /// |   |
**               | \_|  ''\---/''  |_/ |
**               \  .-\__  '-'  ___/-. /
**             ___'. .'  /--.--\  `. .'___
**          ."" '<  `.___\_<|>_/___.' >' "".
**         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
**         \  \ `_.   \_ __\ /__ _/   .-` /  /
**     =====`-.____`.___ \_____/___.-`___.-'=====
**                       `=---='
**
**
**     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**
**               佛祖保佑         永无BUG
**
**
**                   南无本师释迦牟尼佛
**

**----------------------版本信息------------------------
** 版    本: V0.1
**
******************* End of Head **********************\
*/

package com.qianfan.fastframework.db;

import android.content.Context;


import com.qianfan.fastframework.dao.DaoMaster;
import com.qianfan.fastframework.utils.LogUtil;

import org.greenrobot.greendao.database.Database;

/**
 * 文 件 名: MyOpenHelper,主要用于数据库升级
 * 创 建 人: WangJing
 * 创建日期: 16-10-11 08:28
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtil.e("db version update from " + oldVersion + " to " + newVersion);
        switch (oldVersion) {
            case 1:

                //不能先删除表，否则数据都木了
//                StudentDao.dropTable(db, true);

//                StudentDao.createTable(db, true);

                // 加入新字段 score
//                db.execSQL("ALTER TABLE 'STUDENT' ADD 'SCORE' TEXT;");

                break;
        }

    }
}
