package com.soul.service;

import com.soul.pojo.User;


/**
 * Created by Administrator on 2019/3/26.
 */
public interface UserService {

    User findByUsername(String username);

    boolean save(User user);
}
