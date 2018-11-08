package com.alizhezi.demo.database.db;

import java.util.List;

/***
 * 数据库操作
 */
public interface IBaseDao<T> {

    long insert(T entity);
    long update(T entity,T where);
    long delete(T where);

   List<T> query(T where);
   List<T> query(T where,String orderBy,Integer startIndex,Integer limit);
}

