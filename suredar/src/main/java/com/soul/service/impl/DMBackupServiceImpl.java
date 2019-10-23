package com.soul.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.soul.pojo.DBSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.*;

/**
 * Created by Administrator on 2019/6/25.
 */
@Service
public class DMBackupServiceImpl extends DataBaseBackupServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(DMBackupServiceImpl.class);
    private static String  DEFAULTCHART="UTF-8";

    @Override
    public void backup(DBSource dbSource) {

        String savePath = dbSource.getSavePath();
        String host = dbSource.getHost();
        String dmBin = dbSource.getMysqlBin();
        String dataBaseName = dbSource.getDataBaseName();
        String tableNames = dbSource.getTableNames();
        String userName = dbSource.getUserName();
        String password = dbSource.getPassword();
        String fileName = dbSource.getFileName();

        File saveFile = new File(savePath);
        if(!saveFile.exists()){//如果目录不存在,创建文件夹
            saveFile.mkdirs();
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + "/";
        }
        //拼接命令行
        StringBuffer cmd = new StringBuffer();
        cmd.append(dmBin).append("/disql ")
                .append(userName+"/")
                .append(password+"@")
                .append(host+"; ")
                .append("Alter database mount;")
                .append("Alter database archivelog;")
                .append("Alter database open;")
                .append("backup database ")
                //.append(dataBaseName+" ")
                .append("full to ")
                .append(fileName+" ")
                .append("bakfile ")
                .append("\'"+savePath+fileName+"\';");


        System.out.println(cmd.toString());
        String[] cmds = {"/bin/sh", "-c",cmd.toString()};
        try {
            /*Process p = Runtime.getRuntime().exec(cmds);

                if(p.waitFor()==0){
                    System.out.println("backup success");
                }*/
            Connection conn = this.login(host, "root", "123456");
            String execute = this.execute(conn, cmd.toString());
            System.out.println(execute);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 登录主机
     * @return
     *      登录成功返回true，否则返回false
     */
    public static Connection login(String ip, String userName, String userPwd){
        boolean flg=false;
        Connection conn = null;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg=conn.authenticateWithPassword(userName, userPwd);//认证
            if(flg){
                log.info("=========登录成功========="+conn);
                return conn;
            }
        } catch (IOException e) {
            log.error("=========登录失败========="+e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     */
    public static String execute(Connection conn,String cmd){
        String result="";
        try {
            if(conn !=null){
                Session session= conn.openSession();//打开一个会话
                 session.execCommand(cmd);//执行命令
                 result=processStdout(session.getStdout(),DEFAULTCHART); //如果为得到标准输出为空，说明脚本执行出错了
                 if(StringUtils.isBlank(result)){
                     log.info("得到标准输出为空,链接conn:"+conn+",执行的命令："+cmd);
                     result=processStdout(session.getStderr(),DEFAULTCHART);
                 }else{
                     log.info("执行命令成功,链接conn:"+conn+",执行的命令："+cmd); }
                     conn.close();
                 session.close();
            }
        } catch (IOException e) {
            log.info("执行命令失败,链接conn:"+conn+",执行的命令："+cmd+"  "+e.getMessage());
            e.printStackTrace(); }
            return result;
    }
    /**
     * 解析脚本执行返回的结果集
     * @param in 输入流对象
     * @param charset 编码
     * @return
     *       以纯文本的格式返回
     */
    private static String processStdout(InputStream in, String charset){
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));
            String line=null;
            while((line=br.readLine()) != null){
                buffer.append(line+"\n"); }
        } catch (UnsupportedEncodingException e) {
            log.error("解析脚本出错："+e.getMessage()); e.printStackTrace();
        } catch (IOException e) {
            log.error("解析脚本出错："+e.getMessage()); e.printStackTrace();
        } return buffer.toString();
    }


}
