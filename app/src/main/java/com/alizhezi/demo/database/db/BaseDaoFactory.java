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

    public <T extends BaseDao<M>,M> T getBaseDao(Class<T> daoClass ,Class<M> entityClass){

        BaseDao baseDao=null;

        try {
            if (daoClass==null){

                baseDao=BaseDao.class.newInstance();

            }else {

                baseDao =daoClass.newInstance();
            }

             baseDao.init(sqLiteDatabase,entityClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T)baseDao;
    }

    public <T extends BaseDao<M>,M> T getBaseDao(Class<M> entityClass){


        return (T) getBaseDao(BaseDao.class,entityClass);
    }
}
