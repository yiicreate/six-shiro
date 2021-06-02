package com.example.sixshiro.controller;

import com.example.sixshiro.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lh
 * @date: 2021/4/1
 * 用于公共的异常提醒等
 */

@ControllerAdvice
@RestController
public class GlobalController {

    public static void error401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            request.getRequestDispatcher("/401").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public static void error405(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            request.getRequestDispatcher("/405").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/401")
    public ResponseEntity error401() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(401);
        r.setMsg("您的登录已过期，请重新获取！");
        return ResponseEntity.status(401).body(r);
    }

    @RequestMapping("/405")
    public ResponseEntity error405() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(401);
        r.setMsg("你无权访问，请授权");
        return ResponseEntity.status(401).body(r);
    }
}
