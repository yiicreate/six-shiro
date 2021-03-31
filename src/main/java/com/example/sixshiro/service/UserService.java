package com.example.sixshiro.service;

import com.example.sixshiro.entity.User;
import com.example.sixshiro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public List<User> findAll(){
        return mapper.findAll();
    }
}
