package com.example.sixshiro.vo;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author: lh
 * @date: 2021/3/31
 */


public class Result extends HashMap<String,Object> implements Serializable {

    public Result(){
        this.put("success", true);
        this.put("code", HttpStatus.OK.value());
        this.put("msg", "操作成功");
    }

    public static Result success(){
        return  new Result();
    }

    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setMsg("操作失败");
        return r;
    }


    public boolean isSuccess(){
        return (boolean) this.get("success");
    }

    public void setSuccess(boolean success){
        this.put("success",success);
    }

    public String getMsg(){
        return (String) this.get("msg");
    }

    public void setMsg(String msg){
        this.put("msg",msg);
    }

    public int getCode(){
        return (int) this.get("code");
    }

    public void setCode(int code){
        this.put("code",code);
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
