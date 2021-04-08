package com.example.sixshiro.utils;

import java.util.UUID;

/**
 * @author: lh
 * @date: 2021/3/31
 */

public class RandUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
