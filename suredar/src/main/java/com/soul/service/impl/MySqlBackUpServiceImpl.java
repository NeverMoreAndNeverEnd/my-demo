package com.soul.service.impl;

import com.soul.pojo.DBSource;
import com.soul.service.DataBaseBackupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Administrator on 2019/6/20.
 */
@Service
public class MySqlBackUpServiceImpl extends DataBaseBackupServiceImpl {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void backup(DBSource dbSource) {
        String savePath = dbSource.getSavePath();
        String host = dbSource.getHost();
        String mysqlBin = dbSource.getMysqlBin();
        String dataBaseName = dbSource.getDataBaseName();
        String tableNames = dbSource.getTableNames();
        String userName = dbSource.getUserName();
        String password = dbSource.getPassword();
        String fileName = dbSource.getFileName();
        System.out.println(dbSource.getTaskName());


        File saveFile = new File(savePath);
        if(!saveFile.exists()){//如果目录不存在,创建文件夹
            saveFile.mkdirs();
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }
        //拼接命令行
        //TODO fileName 需要加时间
        //TODO 判断是否保存整个数据库 alldatabases
        StringBuffer cmd = new StringBuffer();
        cmd.append(mysqlBin).append("\\mysqldump").append(" --opt")
                .append(" --host=").append(host)
                .append(" --databases ").append(dataBaseName)
                .append(" --tables ").append(tableNames)
                .append(" --user=").append(userName)
                .append(" --password=").append(password)
               //只适用于dos,windows系统 .append(" --result-file=").append(savePath+fileName)
                .append(" --default-character-set=utf8 ");

        try {
            System.out.println(cmd.toString());
            Process p = Runtime.getRuntime().exec(cmd.toString());
            InputStream in = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
            String inStr;
            StringBuffer sb = new StringBuffer();
            String outStr;
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((inStr=br.readLine())!=null){
                sb.append(inStr+"\r\n");
            }
            outStr = sb.toString();
            FileOutputStream fileOutputStream = new FileOutputStream(savePath + fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            outputStreamWriter.write(outStr);
            outputStreamWriter.flush();

            in.close();
            inputStreamReader.close();
            br.close();
            fileOutputStream.close();
            outputStreamWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
            logger.error("备份失败");
            //todo 删除文件夹
            throw new RuntimeException("备份失败");

        }

    }

}
