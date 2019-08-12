package com.springboot.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//服务器配置
@Component
@PropertySource({"classpath:application.properties"})
//@ConfigurationProperties
public class ServerSettings {

    //名称
    @Value("${Server.name}")
    private String name;

    //域名地址
    @Value("${Server.domain}")
    private String doamin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoamin() {
        return doamin;
    }

    public void setDoamin(String doamin) {
        this.doamin = doamin;
    }
}
