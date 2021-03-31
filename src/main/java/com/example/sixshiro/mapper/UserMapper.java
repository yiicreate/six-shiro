package com.example.sixshiro.mapper;

import com.example.sixshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Mapper
@Repository
public interface UserMapper extends BaseMapper {
    List<User> findAll();
}
