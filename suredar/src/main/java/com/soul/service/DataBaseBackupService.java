package com.soul.service;

import com.soul.pojo.DBSource;

/**
 * Created by Administrator on 2019/6/20.
 * 数据库备份通用接口
 */
public interface DataBaseBackupService {

    /**
     *
     * @param host IP地址
     * @param userName 用户名
     * @param password 密码
     * @param savePath 备份文件地址
     * @param fileName 备份文件名称
     * @param dataBaseName 需要备份的数据库名称
     * @param tableNames 表名如: test1 test2 test3
     * @param mysqlBin mysql的bin目录路径
     */
    public void backup(DBSource dbSource);


}
