package com.swagger.demo.controller;


import com.swagger.demo.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.ObjectExpression;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户模块",description = "用户模块的接口信息")
@RestController
public class UserController {

    //模拟数据库
    public static List<User> users=new ArrayList<>();

    static {
        users.add(new User("张三","123456"));
        users.add(new User("李四","123456"));
        users.add(new User("王五","123456"));
    }

    //获取用户列表的方法
    @GetMapping("/users")
    @ApiOperation(value = "获取用户列表列表",notes = "获取所有用户列表的接口")
    public Object users(){
        Map<String,Object> map=new HashMap<>();
        map.put("users",users);
        return users;
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据id获取单个用户",notes = "根据id查询单个用户信息")
    @ApiImplicitParam(value = "用户的ID",paramType = "path")
    public User getUserById(@PathVariable("id") int id){
        return users.get(id);
    }

    @ApiOperation(value = "这是添加用户",notes = "根据传入的用户信息添加用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PostMapping("/user")
    public Object add(User user){
        return users.add(user);
    }

    @ApiOperation(value = "这是删除用户",notes = "根据id删除对应的用户")
    @ApiImplicitParam(value = "用户的id",paramType = "path")
    @DeleteMapping("/user/{id}")
    public Object delete(@PathVariable("id") int id){
        return users.remove(id);
    }
}
