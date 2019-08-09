package com.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class User {


    private int age;

    @JsonIgnore
    private String pwd;

    @JsonProperty("account")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone="GNT+8")
    private Date createTime;

    public User(){
        super();
    }

    public User(int age, String pwd, Date createTime) {
        this.age=age;
        this.pwd = pwd;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
