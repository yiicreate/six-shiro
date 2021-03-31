package com.example.sixshiro.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author: lh
 * @date: 2021/3/30
 */

public interface BaseMapper {
    @Select("${sql}")
    public List<Map<String, Object>> execSelectSql(@Param(value="sql")String sql);

    @Update("${sql}")
    public void execUpdateSql(@Param("sql") String sql);

    @Insert("${sql}")
    public void execInsertSql(@Param("sql") String sql);

    @Delete("${sql}")
    public void execDeleteSql(@Param("sql") String sql);
}
