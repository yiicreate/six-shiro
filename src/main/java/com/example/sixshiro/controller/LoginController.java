package com.example.sixshiro.controller;

import com.example.sixshiro.core.base.BaseController;
import com.example.sixshiro.entity.User;
import com.example.sixshiro.service.UserService;
import com.example.sixshiro.utils.CacheUtil;
import com.example.sixshiro.utils.JwtUtil;
import com.example.sixshiro.utils.UserUtil;
import com.example.sixshiro.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lh
 * @date: 2021/4/1
 */

@RestController
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord){
        User user = userService.getByName(userName);
        Result r = new Result();
        if(user!=null&&userService.verify(passWord,user.getPassword())){
            String token = JwtUtil.create(userName,user.getPassword());
            userService.updateTokenById(token,user.getId());
            r.setSuccess(true);
            r.put("token",token);
            return r;
        }else {
            r.setSuccess(false);
            r.setMsg("用户名或密码错误");
            return r;
        }
    }

    @GetMapping("/test1")
    public Result test(){
        User u = (User) CacheUtil.get("user");
        if(u==null){
            u = UserUtil.getCurrentUser();
            CacheUtil.put("user",u);
            System.out.println("有缓存了么");
        }
        return Result.success().put("user",u);
    }
}
