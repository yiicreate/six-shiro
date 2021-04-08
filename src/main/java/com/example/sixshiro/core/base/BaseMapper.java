package com.example.sixshiro.core.base;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author: lh
 * @date: 2021/3/31
 */


public interface BaseMapper<T> {

    public List<T> findAll();

    public List<T> findList(T entity);

    public T get(String id);

    public void del(String id);

    public T update(T entity);

    public T save(T entity);

    @Select("${sql}")
    public List<Map<String, Object>> execSelectSql(@Param(value="sql")String sql);

    @Update("${sql}")
    public void execUpdateSql(@Param("sql") String sql);

    @Insert("${sql}")
    public void execInsertSql(@Param("sql") String sql);

    @Delete("${sql}")
    public void execDeleteSql(@Param("sql") String sql);
}
