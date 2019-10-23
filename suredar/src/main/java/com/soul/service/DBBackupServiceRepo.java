package com.soul.service;

import com.soul.pojo.DBSource;
import com.soul.pojo.DBType;
import org.quartz.Job;

/**
 * Created by Administrator on 2019/7/17.
 */
public interface DBBackupServiceRepo {

    Class<?extends Job> getJobClass(DBSource dbSource);
}
