package com.soul;

import com.soul.connection.ConnectionTest;
import com.soul.job.QuartzManager;
import com.soul.pojo.DBSource;
import com.soul.pojo.DBType;
import com.soul.pojo.Data;
import com.soul.service.DataService;
import com.soul.service.impl.DMBackupServiceImpl;
import com.soul.service.impl.MySqlBackUpServiceImpl;
import org.elasticsearch.index.mapper.SourceToParse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEs {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private DataService dataService;


    @Autowired
    private MySqlBackUpServiceImpl mySqlBackUpService;

    @Autowired
    private DMBackupServiceImpl dmBackupService;

    @Autowired
    private QuartzManager quartzManager;

    @Test
    public void createIndex(){
        elasticsearchTemplate.createIndex(Data.class);
        elasticsearchTemplate.putMapping(Data.class);
    }

    @Test
    public void findByFileName(){
        List<Data> data = dataService.findByFileName("11.jpg");
        for (Data datum : data) {
            System.out.println(datum);

        }

    }
    @Test
    public void test1(){
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    public void test2(){
        File file = new File("E://");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1);
        }
    }


    @Test
    public void testMySqlBackUp(){
        String host = "127.0.0.1";
        String userName = "root";
        String password = "123";
        String savePath = "E:\\suredar\\backupDataBase\\mysql";
        String fileName = "firstTestBackUp.sql";
        String dataBase = "crm";
        String tableNames = "sys_user";
        String mysqlBin = "E:\\MySQL\\MySQL Server 5.5\\bin";
        DBSource dbSource = new DBSource();
        dbSource.setDataBaseName(dataBase);
        dbSource.setFileName(fileName);
        dbSource.setHost(host);
        dbSource.setUserName(userName);
        dbSource.setPassword(password);
        dbSource.setSavePath(savePath);
        dbSource.setMysqlBin(mysqlBin);
        dbSource.setTableNames(tableNames);
        mySqlBackUpService.backup(dbSource);
    }


    @Test
    public void testDMBackUp(){
        String host = "192.168.5.147";
        String userName = "sysdba";
        String password = "123456789";
        String savePath = "/home/dmdba/dmdbms/backupfile";
        String fileName = "firstTestBackUp";
        String dataBase = null;
        String tableNames = null;
        String dmBin = "/home/dmdba/dmdbms/bin";
        DBSource dbSource = new DBSource();
        dbSource.setDataBaseName(dataBase);
        dbSource.setFileName(fileName);
        dbSource.setHost(host);
        dbSource.setUserName(userName);
        dbSource.setPassword(password);
        dbSource.setSavePath(savePath);
        dbSource.setMysqlBin(dmBin);
        dbSource.setTableNames(tableNames);
        dmBackupService.backup(dbSource);
    }



    @Test
    public void testQuartz() throws SchedulerException {
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
        quartzManager.startJob(dbSource);

    }


    @Test
    public void  cmdRunBat(){

        try {
            // 执行ping命令
            String str = "cmd /c E:\\usr\\local\\suredar\\bin\\mysqlTestConn.bat \"E:\\MySQL\\MySQL Server 5.5\\bin\" root 123";
            System.out.println(str);
            Process process = Runtime.getRuntime().exec(str);
            int i = process.waitFor();
            System.out.println(i);

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testMySqlConn(){
        Connection connection = ConnectionTest.connection("MYSQL", null, null, "127.0.0.1", "3306", "suredar", "root", "123");
    }


    @Test
    public void testStream(){

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::print);
       // random.ints().limit(10).forEach(System.out::println);

       // List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
       // 获取对应的平方数
       // List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
       // System.out.println(squaresList);
       // List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
       // long count = strings.stream().filter(s -> s.isEmpty()).count();
       // System.out.println(count);

    }
    @Test
    public void testqq(){
        int x = 10;
        x+=x-=x-x;
        System.out.println(x);
    }


    static void testql(int a){
        int b ;
        b=a;
        System.out.println(b);
        {
            //int b=0;
            System.out.println(++b);
        }
        System.out.println(b);

    }


    @Test
    public void testq1(){
        int i;
        int j;
        int k;
        int m;
        i=j=(int)18.54839;
        k = i++;
        m=++j;
        System.out.printf("i=%d,j=%d,k=%d,m=%d\n",i,j,k--,m=m+1);


    }


static void s(int x,int y){
        int temp;
        temp = y;
        y=x;
        x=temp;
}


    @Test
    public void testq2(){
    int a=1,b=2;
        System.out.printf("%d%d",a,b);
        s(a,b);
        System.out.printf("%d%d\n",a,b);
    }

    @Test
    public void testq3(){
       String str = "0908\07060504030201";
        System.out.println(str);
        System.out.printf("%d\n",str.length());
    }
    @Test
    public void testq4(){
        String str = "万达信息300168";
        System.out.println("笔试!!!!");
        System.out.printf("%d,%d",str.length(),Character.SIZE);
    }
    @Test
    public void testq5(){
      int[][]arr={{1,2,3,4},{5,6,7,8,9},{10,11,12,13,14,15,16,17}};
        System.out.printf("%d,%d\n",arr[1][2],arr[2][3]);
    }

    @Test
    public void testq6(){
       String sql = "selert name ,sum(score) from table group by name,having sum(score)>200";
       int a=2;
       int b=3;
    }




}
