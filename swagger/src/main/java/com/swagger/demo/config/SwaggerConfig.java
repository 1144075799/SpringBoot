package com.swagger.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration      //说明是配置类
@EnableSwagger2
@EnableWebMvc
// 扫描的API Controller包
@ComponentScan(basePackages = {"com.swagger.demo.controller"})
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        Contact contact=new Contact("胡思彬","https://blog.csdn.net/qq_35905501","1144075799@qq.com");
        return new ApiInfoBuilder()
                .title("项目的API接口")
                .description("API接口")
                .contact(contact)
                .version("1.0.0")
                .build();
    }
}
