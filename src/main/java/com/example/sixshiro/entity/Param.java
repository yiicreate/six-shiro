package com.example.sixshiro.entity;

import com.example.sixshiro.core.DataEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: lh
 * @date: 2021/4/21
 */

@Setter
@Getter
public class Param extends DataEntity<Param> {
    private String  code;

    private String value;
}
