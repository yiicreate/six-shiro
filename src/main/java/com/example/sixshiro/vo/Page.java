package com.example.sixshiro.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/30
 */

@Getter
@Setter
public class Page<T> implements Serializable {

    private int page = 1;

    private int pageSize = 10;

    private long total = 0;

    private List<T> list = new ArrayList<T>();

    private String orderBy = "";

    public Page(){}

    public Page(Integer page,Integer pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }
}
