package com.soul.job;

import com.soul.pojo.DBSource;
import com.soul.pojo.DBType;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/7/17.
 */
@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        String host = "127.0.0.1";
        String userName = "root";
        String password = "123";
        String savePath = "E:\\suredar\\backupDataBase\\mysql";
        String fileName = "firstTestBackUp.sql";
        String dataBase = "crm";
        String tableNames = "sys_user";
        String mysqlBin = "E:\\MySQL\\MySQL Server 5.5\\bin";
        String dbType = DBType.TYPE_MYSQL;
        String cron = "*/5 * * * * ?" ;
        DBSource dbSource = new DBSource();
        dbSource.setDataBaseName(dataBase);
        dbSource.setFileName(fileName);
        dbSource.setHost(host);
        dbSource.setUserName(userName);
        dbSource.setPassword(password);
        dbSource.setSavePath(savePath);
        dbSource.setMysqlBin(mysqlBin);
        dbSource.setTableNames(tableNames);
        dbSource.setDbType(dbType);
        dbSource.setCron(cron);
        dbSource.setTaskName("firstTestBackUp");
        dbSource.setEventType("full");

        String host1 = "127.0.0.1";
        String userName1 = "root";
        String password1 = "123";
        String savePath1= "E:\\suredar\\backupDataBase\\mysql";
        String fileName1 = "firstTestBackUp1.sql";
        String dataBase1 = "crm";
        String tableNames1 = "sys_user";
        String mysqlBin1 = "E:\\MySQL\\MySQL Server 5.5\\bin";
        String dbType1 = DBType.TYPE_MYSQL;
        String cron1 = "*/8 * * * * ?" ;
        DBSource dbSource1 = new DBSource();
        dbSource1.setDataBaseName(dataBase1);
        dbSource1.setFileName(fileName1);
        dbSource1.setHost(host1);
        dbSource1.setUserName(userName1);
        dbSource1.setPassword(password1);
        dbSource1.setSavePath(savePath1);
        dbSource1.setMysqlBin(mysqlBin1);
        dbSource1.setTableNames(tableNames1);
        dbSource1.setDbType(dbType1);
        dbSource1.setCron(cron1);
        dbSource1.setTaskName("firstTestBackUp1");
        dbSource1.setEventType("full");

        String host11 = "127.0.0.1";
        String userName11 = "root";
        String password11 = "123";
        String savePath11= "E:\\suredar\\backupDataBase\\mysql";
        String fileName11 = "firstTestBackUp11.sql";
        String dataBase11 = "estore";
        String tableNames11 = "goods";
        String mysqlBin11 = "E:\\MySQL\\MySQL Server 5.5\\bin";
        String dbType11 = DBType.TYPE_MYSQL;
        String cron11 = "*/10 * * * * ?" ;
        DBSource dbSource11 = new DBSource();
        dbSource11.setDataBaseName(dataBase11);
        dbSource11.setFileName(fileName11);
        dbSource11.setHost(host11);
        dbSource11.setUserName(userName11);
        dbSource11.setPassword(password11);
        dbSource11.setSavePath(savePath11);
        dbSource11.setMysqlBin(mysqlBin11);
        dbSource11.setTableNames(tableNames11);
        dbSource11.setDbType(dbType11);
        dbSource11.setCron(cron11);
        dbSource11.setTaskName("firstTestBackUp11");
        dbSource11.setEventType("full");


        ArrayList<DBSource> dbSources = new ArrayList<>();
        dbSources.add(dbSource);
        dbSources.add(dbSource1);
        dbSources.add(dbSource11);


        try {
            for (DBSource source : dbSources) {
                quartzManager.startJob(source);
                System.out.println("任务已经启动:"+dbSource.getTaskName());
                String path = this.getClass().getResource("/").getPath();
                String path1 = this.getClass().getClassLoader().getResource("").getPath();
                System.out.println("==============="+path1);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始注入scheduler
     */
    /*@Bean
    public Scheduler scheduler() throws SchedulerException{
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }*/


}
