package com.soul.service;

import com.soul.pojo.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/3/28.
 */
public interface DataService {

    boolean save(Data data);

    List<Data> findByFileName(String fileName);
}
