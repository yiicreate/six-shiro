package com.example.sixshiro.utils;

import com.example.sixshiro.scurtity.session.SessionManager;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * @author: lh
 * @date: 2021/4/14
 * 验证码图片生成器
 */

@Component
public class KaptchaUtil {
    private static Producer kaptcha;

    private static final String KAPTCHA_CODE = "code_kap";

    KaptchaUtil(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border","no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color","black");
        //边框厚度
        properties.setProperty("kaptcha.border.thickness","1");
        // 图片宽
        properties.setProperty("kaptcha.image.width","200");
        // 图片高
        properties.setProperty("kaptcha.image.height","50");
        //图片实现类
        properties.setProperty("kaptcha.producer.impl","com.google.code.kaptcha.impl.DefaultKaptcha");
        //文本实现类
        properties.setProperty("kaptcha.textproducer.impl","com.google.code.kaptcha.text.impl.DefaultTextCreator");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string","01234567890");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length","4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names","宋体");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color","black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space","5");
        //干扰实现类
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        //干扰颜色
        properties.setProperty("kaptcha.noise.color","blue");
        //干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");
        //背景实现类
        properties.setProperty("kaptcha.background.impl","com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to","white");
        //文字渲染器
        properties.setProperty("kaptcha.word.impl","com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        kaptcha = defaultKaptcha;
    }

    /**
     * 生成验证码图片
     * @param request 设置session
     * @param response 转成图片
     * @throws Exception
     */
    public static void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = kaptcha.createText();

        // store the text in the session

        request.getSession().setAttribute(KAPTCHA_CODE, capText);

        // create the image with the text
        BufferedImage bi = kaptcha.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 验证码 验证
     * @param request
     * @param code
     * @return
     */
    public static Boolean check(HttpServletRequest request,String code){
        HttpSession s = request.getSession();
        Object obj = s.getAttribute(KAPTCHA_CODE);
        if(obj==null){
            return  false;
        }
        String loginCode = obj.toString();
        if(loginCode.equals(code)){
            s.removeAttribute(KAPTCHA_CODE);
            return true;
        }else {
            return false;
        }
    }
}
