package com.example.sixshiro.entity;

import com.example.sixshiro.core.DataEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author: lh
 * @date: 2021/3/29
 */

@Setter
@Getter
public class User extends DataEntity<User> {

    private String  userName;

    private String password;

    @JsonIgnore
    private String token;

    private int sex;
}
