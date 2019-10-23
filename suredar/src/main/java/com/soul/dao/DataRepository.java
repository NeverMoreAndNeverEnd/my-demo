package com.soul.dao;

import com.soul.pojo.Data;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2019/3/28.
 */
public interface DataRepository extends JpaRepository<Data,Integer> {
}
