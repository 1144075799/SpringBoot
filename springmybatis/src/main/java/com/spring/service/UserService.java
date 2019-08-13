package com.spring.service;

import com.spring.domain.User;

public interface UserService {


    public int add(User user);

    /**
     * 测试事务
     * @return
     */
    public int addAccount() ;

}
