package com.soul.job;

import com.soul.pojo.DBSource;
import com.soul.service.DBBackupServiceRepo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;


/**
 * Created by Administrator on 2019/7/16.
 */
@Component
public class QuartzManager {

    public static final String JOB1="job1";
    public static final String GROUP1="group1";

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private DBBackupServiceRepo dbBackupServiceRepo;

    /*@Autowired
    private MyJobFactory myJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        System.out.println("myJobFactory:"+myJobFactory);
        return schedulerFactoryBean;
    }
    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }*/


    /**
     * 开始执行定时任务
     * @throws SchedulerException
     */
    public void startJob(DBSource dbSource) throws SchedulerException {
        startJobTask(dbSource);
        scheduler.start();
    }



    /**
     * 开启定时任务
     *
     * @throws SchedulerException
     */
    public void startJobTask(DBSource dbSource) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("dbSource",dbSource);
        JobDetail jobDetail = JobBuilder.newJob(dbBackupServiceRepo.getJobClass(dbSource)).withIdentity(dbSource.getTaskName(), dbSource.getEventType()).setJobData(jobDataMap).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(dbSource.getCron());
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(dbSource.getTaskName(), dbSource.getEventType()).withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,trigger);

    }

    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public void pauseAllJob(Scheduler scheduler)throws SchedulerException{
        scheduler.pauseAll();
    }

}
