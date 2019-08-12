package com.springboot.controller;

import com.springboot.pojo.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@PropertySource({"classpath:application.properties"})
public class FileController {

    @RequestMapping("/api/gopage")
    public Object index(){
        return "index";
    }

    @RequestMapping("/uploadImage")
    public Object image(){
        return "upload";
    }

    /**文件存储的路径**/
    @Value("${flie.path}")
    private String filePath;

    @RequestMapping(value = "/upload")
    @ResponseBody
    public JsonData upload(@RequestParam("head_image") MultipartFile file, HttpServletRequest request){
        String name=request.getParameter("name");
        System.out.println("用户名:"+name);

        //获取文件名
        String fileName=file.getOriginalFilename();
        System.out.println("上传文件名为"+fileName);

        //获取文件的后缀名
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为"+suffixName);

        //文件上传后的路径
        fileName= UUID.randomUUID()+suffixName;
        System.out.println("转换后的名称"+fileName);
        File dest=new File(filePath+fileName);
        System.out.println("保存路径:"+filePath);

        try {
            file.transferTo(dest);
            return new JsonData("200",fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JsonData("400",fileName);
    }
}
