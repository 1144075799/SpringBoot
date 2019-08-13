package com.spring.service.impl;

import com.spring.domain.User;
import com.spring.mapper.UserMapper;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLClientInfoException;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public int add(User user) {
        mapper.insert(user);
        int id=user.getId();
        return id;
    }

    @Override
    @Transactional
    public int addAccount() {

        try {
            User user=new User();
            user.setAge(60);
            user.setName("测试事务");
            user.setPhone("100100100100");
            user.setCreateTime(new Date());
            mapper.insert(user);

            int i=1/0;          //抛出异常
        }catch (Exception e){
            throw e;
        }

        return 0;
    }
}
