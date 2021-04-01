package com.example.sixshiro.controller;

import com.example.sixshiro.core.base.BaseController;
import com.example.sixshiro.entity.User;
import com.example.sixshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@RestController
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public List<User> test(){
        return userService.findAll();
    }
}
