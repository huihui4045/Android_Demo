package com.alizhezi.demo.database.db;

import android.database.sqlite.SQLiteDatabase;

public class BaseDaoFactory {

    private static final BaseDaoFactory instance = new BaseDaoFactory();


    public static BaseDaoFactory getInstance() {

        return instance;
    }

    private SQLiteDatabase sqLiteDatabase;

    private String sqLiteDatabasePath;

    private BaseDaoFactory() {

        sqLiteDatabasePath = "data/data/com.alizhezi.demo/gavin.db";

        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqLiteDatabasePath, null);
    }

    public <T> BaseDao<T> getBaseDao(Class<T> entityClass){

        BaseDao baseDao=null;

        try {
             baseDao = BaseDao.class.newInstance();

             baseDao.init(sqLiteDatabase,entityClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return baseDao;
    }
}
