package com.springboot.pojo;

import java.io.Serializable;

public class JsonData implements Serializable {

    //状态码
    private String code;

    //结果
    private Object data;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonData(String code, Object data) {
        this.code = code;
        this.data = data;
    }
}
