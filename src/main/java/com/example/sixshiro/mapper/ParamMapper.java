package com.example.sixshiro.mapper;

import com.example.sixshiro.core.base.BaseMapper;
import com.example.sixshiro.entity.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: lh
 * @date: 2021/4/21
 */

@Mapper
@Repository
public interface ParamMapper extends BaseMapper<Param> {

}
