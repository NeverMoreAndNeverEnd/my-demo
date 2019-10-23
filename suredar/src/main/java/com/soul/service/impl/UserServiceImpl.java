package com.soul.service.impl;

import com.soul.dao.UserRepository;
import com.soul.pojo.User;
import com.soul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/3/26.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        User u = userRepository.save(user);
        if(u == null) {
            return false;
        }
        return true;
    }


}
