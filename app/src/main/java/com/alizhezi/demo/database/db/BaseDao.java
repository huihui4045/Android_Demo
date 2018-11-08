package com.alizhezi.demo.database.db;

import android.database.sqlite.SQLiteDatabase;

import com.alizhezi.demo.database.annotation.DbField;
import com.alizhezi.demo.database.annotation.DbTable;

import java.lang.reflect.Field;

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;

    private String tableName;

    private boolean isInit=false;

    private Class<T> entityClass;

    protected boolean init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;

        if(!isInit){

            if(entityClass.getAnnotation(DbTable.class)==null){
                tableName=entityClass.getSimpleName();
            }else {
                tableName=entityClass.getAnnotation(DbTable.class).value();
            }
        }

        if(!sqLiteDatabase.isOpen()){

            return false;
        }


        String sql=getCreateTableSql();

        sqLiteDatabase.execSQL(sql);


            isInit=true;


        return  isInit;
    }

    private String getCreateTableSql(){


        StringBuffer sb=new StringBuffer("create table if not exists ");
        sb.append(tableName+"( ");

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {

            Class type = field.getType();

            if(field.getAnnotation(DbField.class)!=null){

                if (type==String.class){

                    sb.append(field.getAnnotation(DbField.class).value()+" TEXT,");
                }else  if (type==Integer.class){

                    sb.append(field.getAnnotation(DbField.class).value()+" INTEGER,");
                }else  if (type==Long.class){

                    sb.append(field.getAnnotation(DbField.class).value()+" BIGINT,");
                }else
                if (type==Double.class){

                    sb.append(field.getAnnotation(DbField.class).value()+" DOUBLE,");
                }else  if (type==byte[].class){

                    sb.append(field.getAnnotation(DbField.class).value()+" BLOB,");
                }else {

                    continue;
                }

            }else {

                if (type==String.class){

                    sb.append(field.getName()+" TEXT,");
                }else  if (type==Integer.class){

                    sb.append(field.getName()+" INTEGER,");
                }else  if (type==Long.class){

                    sb.append(field.getName()+" BIGINT,");
                }else
                if (type==Double.class){

                    sb.append(field.getName()+" DOUBLE,");
                }else  if (type==byte[].class){

                    sb.append(field.getName()+" BLOB,");
                }else {

                    continue;
                }
            }



        }

        if(sb.charAt(sb.length()-1)==','){

            sb.deleteCharAt(sb.length()-1);
        }

        sb.append(")");


        return sb.toString();
    }


    @Override
    public long insert(T entity) {
        return 0;
    }
}
