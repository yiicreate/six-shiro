package com.example.sixshiro.entity;

import com.example.sixshiro.core.DataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: lh
 * @date: 2021/4/27
 */

@Getter
@Setter
public class Menu extends DataEntity<Menu>
{
    private String name;

    private  String url;

    private Integer sort;

    private String icon;

    private Integer type;

    private Integer is_use;

    private Integer target;

    private String pid;
}
