package com.example.sixshiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Data
public class User implements Serializable {

    private int id;

    private String  userName;

    private String password;

    private String token;

    private int sex;
}
