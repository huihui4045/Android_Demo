package com.alizhezi.demo.database.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseDaoFactory {

    private static final BaseDaoFactory instance = new BaseDaoFactory();


    public static BaseDaoFactory getInstance() {

        return instance;
    }

    private SQLiteDatabase sqLiteDatabase;

    private String sqLiteDatabasePath;

    private Map<String ,BaseDao> map=Collections.synchronizedMap(new HashMap<String, BaseDao>());

    private BaseDaoFactory() {

        sqLiteDatabasePath = "data/data/com.alizhezi.demo/gavin.db";

        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqLiteDatabasePath, null);
    }

    public <T extends BaseDao<M>,M> T getBaseDao(Class<T> daoClass ,Class<M> entityClass){

        BaseDao baseDao=null;

        if (map.get(daoClass.getClass().getSimpleName())!=null){


            return (T) map.get(daoClass.getSimpleName());

        }

        try {
            if (daoClass==null){

                baseDao=BaseDao.class.newInstance();

            }else {

                baseDao =daoClass.newInstance();
            }

             baseDao.init(sqLiteDatabase,entityClass);

            map.put(daoClass.getSimpleName(),baseDao);
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
