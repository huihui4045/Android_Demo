package com.alizhezi.demo.database.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.alizhezi.demo.database.annotation.DbField;
import com.alizhezi.demo.database.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDao<T> implements IBaseDao<T> {

    private SQLiteDatabase sqLiteDatabase;

    private String tableName;

    private boolean isInit = false;

    private Class<T> entityClass;

    private HashMap<String, Field> cacheMap;

    private String TAG=this.getClass().getSimpleName();

    protected boolean init(SQLiteDatabase sqLiteDatabase, Class<T> entityClass) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;

        if (!isInit) {

            if (entityClass.getAnnotation(DbTable.class) == null) {
                tableName = entityClass.getSimpleName();
            } else {
                tableName = entityClass.getAnnotation(DbTable.class).value();
            }
        }

        if (!sqLiteDatabase.isOpen()) {

            return false;
        }


        String sql = getCreateTableSql();

        sqLiteDatabase.execSQL(sql);

        cacheMap = new HashMap<>();
        initCacheMap();


        isInit = true;


        return isInit;
    }

    private void initCacheMap() {


        String sql = "select * from " + tableName + " limit 1,0";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        String[] columnNames = cursor.getColumnNames();

        Field[] fields = entityClass.getDeclaredFields();

        for (String columnName : columnNames) {

            Field columnField = null;

            String fieldName = null;

            for (Field field : fields) {

                field.setAccessible(true);

                if (field.getAnnotation(DbField.class) != null) {

                    fieldName = field.getAnnotation(DbField.class).value();
                } else {

                    fieldName = field.getName();
                }


                if (columnName.equals(fieldName)) {

                    columnField = field;

                    break;
                }

            }

            if(columnField!=null){

                cacheMap.put(columnName,columnField);
            }
        }

        cursor.close();

    }

    private String getCreateTableSql() {


        StringBuffer sb = new StringBuffer("create table if not exists ");
        sb.append(tableName + "( ");

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {

            Class type = field.getType();

            if (field.getAnnotation(DbField.class) != null) {

                if (type == String.class) {

                    sb.append(field.getAnnotation(DbField.class).value() + " TEXT,");
                } else if (type == Integer.class) {

                    sb.append(field.getAnnotation(DbField.class).value() + " INTEGER,");
                } else if (type == Long.class) {

                    sb.append(field.getAnnotation(DbField.class).value() + " BIGINT,");
                } else if (type == Double.class) {

                    sb.append(field.getAnnotation(DbField.class).value() + " DOUBLE,");
                } else if (type == byte[].class) {

                    sb.append(field.getAnnotation(DbField.class).value() + " BLOB,");
                } else {

                    continue;
                }

            } else {

                if (type == String.class) {

                    sb.append(field.getName() + " TEXT,");
                } else if (type == Integer.class) {

                    sb.append(field.getName() + " INTEGER,");
                } else if (type == Long.class) {

                    sb.append(field.getName() + " BIGINT,");
                } else if (type == Double.class) {

                    sb.append(field.getName() + " DOUBLE,");
                } else if (type == byte[].class) {

                    sb.append(field.getName() + " BLOB,");
                } else {

                    continue;
                }
            }


        }

        if (sb.charAt(sb.length() - 1) == ',') {

            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(")");


        return sb.toString();
    }


    @Override
    public long insert(T entity) {

//        ContentValues values=new ContentValues();
//
//        sqLiteDatabase.insert(tableName,null,values);

        Map<String, String> map = getValues(entity);

        ContentValues contentValues=getContentValues(map);



        Log.e(TAG,"insert:"+contentValues.toString());

        long insert = sqLiteDatabase.insert(tableName, null, contentValues);


        return insert;
    }

    @Override
    public long update(T entity, T where) {

        //sqLiteDatabase.update(tableName,contentValue,"name=?",new String[]{});

        int result = -1;
        Map values = getValues(entity);

        Log.e(TAG,"map:"+values.toString());
        ContentValues contentValues = getContentValues(values);



        Log.e(TAG,"update values:"+values.toString());

        Map whereCause = getValues(where);//key==_id   value=1



        Log.e(TAG,"update whereCause:"+whereCause.toString());


        Condition condition = new Condition(whereCause);
        result = sqLiteDatabase.update(tableName, contentValues, condition.whereCasue, condition.whereArgs);
        return result;

    }

    private class Condition {
        private String whereCasue;//"name=? and password=?"
        private String[] whereArgs;//new String[]{"jett"}

        public Condition(Map<String, String> whereCasue) {
            ArrayList list = new ArrayList();//whereArgs里面的内容存入list
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1=1");
            //取所有的字段名
            Set keys = whereCasue.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = whereCasue.get(key);
                if (value != null) {
                    stringBuilder.append(" and " + key + "=?");
                    list.add(value);
                }
            }
            this.whereCasue = stringBuilder.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);

        }

        @Override
        public String toString() {
            return "Condition{" +
                    "whereCasue='" + whereCasue + '\'' +
                    ", whereArgs=" + Arrays.toString(whereArgs) +
                    '}';
        }
    }



    @Override
    public long delete(T where) {
        //        sqLiteDatabase.delete(tableName,"name=?",new String[]{});
        Map map = getValues(where);
        Condition condition = new Condition(map);
        int result = sqLiteDatabase.delete(tableName, condition.whereCasue, condition.whereArgs);
        return result;
    }

    @Override
    public List<T> query(T where) {


        return query(where,null,null,null);
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {

        Map map = getValues(where);
        String limitString = null;
        if (startIndex != null && limit != null) {
            limitString = startIndex + " , " + limit;
        }
        Condition condition = new Condition(map);

        Cursor cursor = sqLiteDatabase.query(tableName, null, condition.whereCasue, condition.whereArgs, null, null, orderBy, limitString);
        //定义一个用来解析游标的方法
        List<T> result = getResult(cursor, where);


        return result;
    }

    //obj是用来表示User类的结构
    private List<T> getResult(Cursor cursor, T obj) {
        ArrayList list=new ArrayList();
        Object item=null;
        while(cursor.moveToNext()){
            try {
                item=obj.getClass().newInstance();//new User();
                Iterator iterator=cacheMap.entrySet().iterator();//字段-成员变量
                while(iterator.hasNext()){
                    Map.Entry entry=(Map.Entry)iterator.next();
                    //取列名
                    String columnName=(String)entry.getKey();
                    //然后以列名拿到列名在游标中的位置
                    Integer columnIndex=cursor.getColumnIndex(columnName);

                    Field field=(Field)entry.getValue();
                    Class type=field.getType();

                    if(columnIndex!=-1){
                        if(type==String.class){
                            field.set(item,cursor.getString(columnIndex));
                        }else if(type==Double.class){
                            field.set(item,cursor.getDouble(columnIndex));
                        }else if(type==Integer.class){
                            field.set(item,cursor.getInt(columnIndex));
                        }else if(type==Long.class){
                            field.set(item,cursor.getLong(columnIndex));
                        }else if(type==byte[].class){
                            field.set(item,cursor.getBlob(columnIndex));
                        }else{
                            continue;
                        }
                    }
                }
                list.add(item);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        cursor.close();

        return list;
    }

    private ContentValues getContentValues(Map<String, String> map) {

        ContentValues values=new ContentValues();

        Set<String> keys = map.keySet();

        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()){

            String key = iterator.next();

            String value = map.get(key);

            values.put(key,value);
        }




        return values;
    }


    private Map<String,String> getValues(T entity){

        HashMap<String,String> map=new HashMap<>();

        Set<String> keys = cacheMap.keySet();

        Iterator<String> keyIterator = keys.iterator();

        while (keyIterator.hasNext()){

            String key = keyIterator.next();

            Log.e(TAG,"key:"+key);

            Field field = cacheMap.get(key);

            Log.e(TAG,"field:"+field);

            field.setAccessible(true);

            try {
                Object o = field.get(entity);

                Log.e(TAG,"Object:"+o);

               if(o==null){
                   continue;
               }

                String value = o.toString();
                Log.e(TAG,"value:"+value);
                if(!TextUtils.isEmpty(key)&& !TextUtils.isEmpty(value)){

                    map.put(key,value);

               }




            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }




        return map;

    }




}
