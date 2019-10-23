package com.soul.dao;

import com.soul.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019/3/26.
 */
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);


}
