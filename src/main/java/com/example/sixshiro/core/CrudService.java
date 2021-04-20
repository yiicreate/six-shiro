package com.example.sixshiro.core;

import com.example.sixshiro.core.base.BaseMapper;
import com.example.sixshiro.core.base.BaseService;
import com.example.sixshiro.utils.RandUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author: lh
 * @date: 2021/3/31
 */


public abstract class CrudService<T extends DataEntity<T>,M extends BaseMapper<T>> extends BaseService {
    @Autowired
    protected M mapper;

    public T get(String id){
        return mapper.get(id);
    };

    public void del(String id){
        mapper.del(id);
    };

    public List<T> findAll(){
        return mapper.findAll();
    }

    public List<T> findList(T entity){
        return mapper.findList(entity);
    }

    public T update(T t){
        return mapper.update(t);
    };

    public T save(T t){
        return mapper.save(t);
    }

    public T preInsert(T t){
        if(!t.isAuto()){
            t.setId(RandUtil.uuid());
        }
        t.setCreateTime(new Date());

        return t;
    };
}
