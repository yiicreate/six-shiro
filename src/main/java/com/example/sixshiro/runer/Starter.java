package com.example.sixshiro.runer;

import com.example.sixshiro.config.ParamConf;
import com.example.sixshiro.entity.Param;
import com.example.sixshiro.service.ParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: lh
 * @date: 2021/4/21
 */

@Component
public class Starter implements CommandLineRunner {
    @Autowired
    ParamService paramService;
    @Autowired
    ParamConf paramConf;

    private void setFieldValueByName(String fieldName, Object o,String value) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(setter, String.class);
            method.invoke(o, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //提前加载配置项
        List<Param> params = paramService.findAll();
        for (Param p: params) {
            setFieldValueByName(p.getCode(),paramConf,p.getValue());
        }
    }
}
