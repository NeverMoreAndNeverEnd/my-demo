package com.soul.service.impl;

import com.soul.job.MyJob;
import com.soul.pojo.DBSource;
import com.soul.pojo.DBType;
import com.soul.service.DBBackupServiceRepo;
import org.quartz.Job;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/7/17.
 */
@Service
public class DBBackupServiceRepoImpl implements DBBackupServiceRepo {

    private Map<String,Class<?extends Job>> dbJobMap =  new HashMap<>();

    @PostConstruct
    public void init() {
        dbJobMap.clear();
        dbJobMap.put(DBType.TYPE_MYSQL, MyJob.class);
    }


    @Override
    public Class<? extends Job> getJobClass(DBSource dbSource) {
        return dbJobMap.get(dbSource.getDbType());
    }
}
