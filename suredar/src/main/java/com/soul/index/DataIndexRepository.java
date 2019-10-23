package com.soul.index;

import com.soul.pojo.Data;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Administrator on 2019/3/28.
 */
@Repository

public interface DataIndexRepository extends ElasticsearchRepository<Data,Integer> {


    List<Data> findByFileName(String fileName);


}
