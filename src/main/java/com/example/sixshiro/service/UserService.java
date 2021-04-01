package com.example.sixshiro.service;

import com.example.sixshiro.core.CrudService;
import com.example.sixshiro.entity.User;
import com.example.sixshiro.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Service
public class UserService extends CrudService<User,UserMapper> {
    public List<User> findAll(){
        return mapper.findAll();
    }
}
