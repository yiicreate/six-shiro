package com.example.sixshiro.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lh
 * @date: 2021/4/22
 */

@Data
@Configuration
public class ParamConf {
    private String jwtExpireTime;
    private String url;
}
