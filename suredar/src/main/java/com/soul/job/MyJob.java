package com.soul.job;

import com.soul.pojo.DBSource;
import com.soul.service.impl.MySqlBackUpServiceImpl;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2019/7/16.
 */
@Component
public  class MyJob implements Job {

    @Autowired
    private MySqlBackUpServiceImpl mySqlBackUpService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务开始执行"+new Date());
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        DBSource dbSource = (DBSource) jobDataMap.get("dbSource");
        mySqlBackUpService.backup(dbSource);

    }
}
