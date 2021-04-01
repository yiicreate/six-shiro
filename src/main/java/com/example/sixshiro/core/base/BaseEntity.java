package com.example.sixshiro.core.base;

import com.example.sixshiro.vo.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lh
 * @date: 2021/3/31
 */

@Getter
@Setter
public abstract class BaseEntity<T> implements Serializable {

    private String id;

    protected Page<T> page;

    protected Date createTime;

    protected boolean isAuto = false;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }
}
