package com.soul.service.impl;

import com.soul.index.DataIndexRepository;
import com.soul.dao.DataRepository;
import com.soul.pojo.Data;
import com.soul.service.DataService;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/28.
 */
@Service
@Transactional
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private DataIndexRepository dataIndexRepository;

    @Override
    public boolean save(Data data) {
        Data d = dataRepository.save(data);
        Data save = dataIndexRepository.save(data);
        if(save == null){
            throw new RuntimeException("数据未保存到索引库");
        }
        if(d == null){
            return false;
        }
        return true;
    }


    @Override
    public List<Data> findByFileName(String fileName) {
        /*MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("fileName", fileName);
        Iterable<Data> data = dataIndexRepository.search(queryBuilder);
        ArrayList<Data> list = new ArrayList<>();
        for (Data datum : data) {
           list.add(datum);
        }*/
        List<Data> list = dataIndexRepository.findByFileName(fileName);
        if(list == null){
            return null;
        }

        return list;
    }
}
