package com.spring.controller;

import com.spring.domain.User;
import com.spring.mapper.UserMapper;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("add")
    public Object add(){
        User user=new User();
        user.setAge(20);
        user.setName("AAA");
        user.setPhone("13115833693");
        user.setCreateTime(new Date());
        int id=userService.add(user);
        return id;
    }

    @Autowired
    private UserMapper userMapper;

    /**
     * 查找全部用户
     * @return
     */
    @GetMapping("findAll")
    public Object findAll(){
        return userMapper.getAll();
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Object findById(Long id){
        return userMapper.findById(id);
    }

    @GetMapping("del_by_id")
    public Object delById(Long id){
        userMapper.delete(id);
        return null;
    }

    @GetMapping("update")
    public Object update(String name,int id){
        User user=new User();
        user.setName(name);
        user.setId(id);
        userMapper.update(user);

        return null;
    }

    @GetMapping("add_accout")
    public Object addAccount(){
        int id=userService.addAccount();
        return id;
    }



}
