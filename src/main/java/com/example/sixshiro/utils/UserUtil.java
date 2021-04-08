package com.example.sixshiro.utils;

import com.example.sixshiro.entity.User;
import com.example.sixshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author: lh
 * @date: 2021/4/7
 */


public class UserUtil {

    /**
     * 通过token 获取用户
     * @param token
     * @return
     */
    public static User getUserByToken(String token){
       return AppUtil.getBean(UserService.class).getByToken(token);
    }

    /**
     * 获取当前用户(根据token)
     * @return
     */
    public static User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        Object token = subject.getPrincipal();
        User u = getUserByToken((String) token);
        return u!=null?u:new User();
    }
}
