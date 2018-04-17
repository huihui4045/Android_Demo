package com.alizhezi.demo.db;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

public class DaoManager {
    private static final String TAG = "DaoManager";
    private static final String DB_NAME = "mydb.sqlite";//数据库名称

    private volatile static DaoManager manager;//多线程访问声明为单例模式

    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster master;
    private static DaoSession session;

    private Context mContext;

    private DaoManager() {

    }

    public static DaoManager getInstance() {
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (manager == null) {
                    manager = new DaoManager();
                }
            }
        }
        return manager;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    /**
     * 判断是否存在数据库,如果没有则创建数据库
     */
    // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
    // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
    // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
    // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
    public DaoMaster getMaster() {
        if (master == null) {
            helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            master = new DaoMaster(helper.getWritableDatabase());
        }
        return master;
    }

    /**
     * 完成对数据库的 增删改查 ,这里仅仅是一个接口
     */
    public DaoSession getSession() {
        if (session == null) {
            if (master == null) {
                master = getMaster();
            }
            session = master.newSession();
        }
        return session;
    }

    /**
     * 打开输出日志的操作,默认的是关闭的
     */
    public void setDeBug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的连接
     */
    public void closeConnections() {
        closeHelper();
        closeSession();
    }

    private void closeSession() {
        if (session != null) {
            session.clear();
            session = null;
        }
    }

    private void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }
}
