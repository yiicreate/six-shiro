package com.example.sixshiro.mapper;

import com.example.sixshiro.core.base.BaseMapper;
import com.example.sixshiro.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: lh
 * @date: 2021/4/27
 */

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

}
