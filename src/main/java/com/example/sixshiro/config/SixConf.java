package com.example.sixshiro.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lh
 * @date: 2021/4/1
 */


@Data
@Configuration
public class SixConf {
    @Value("${jwt.token.expireTime}")
    private Integer jwtExpireTime;
}
