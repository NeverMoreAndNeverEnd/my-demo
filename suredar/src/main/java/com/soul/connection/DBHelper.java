package com.soul.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by Administrator on 2019/8/5.
 */
public class DBHelper {

    protected static final Logger logger = LoggerFactory.getLogger(DBHelper.class);

    /**
     * @description 创建ORACLE连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initOracle(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //连接数据库
            conn = DriverManager.getConnection(jdbcurl, username, password);
            conn.setAutoCommit(autoCommit);
//            logger.debug(jdbcurl);
//            logger.debug("username=【"+username+"】");
//            logger.debug("Oracle DATABASE: Connection success!");
        }catch(Exception se) {
            //连接失败
//            logger.debug("initOra："+jdbcurl + "\r\n" + " Connection failed! " + se.getMessage());
            //System.exit(0);
            logger.error("ORACLE连接失败",se);
            return null;
        }
        return conn;
    }

    /**
     * @description 创建DB2连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initDB2(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Driver driver=(Driver) Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            //连接数据库
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(jdbcurl, username, password);
            conn.setAutoCommit(autoCommit);
//            logger.info("db2 DATABASE:" + jdbcurl + " Connection success!");
        }catch(Exception se) {
//            logger.debug("initDB2："+jdbcurl + "\r\n" + " Connection failed! " + se.getMessage());
            logger.error("DB2连接失败",se);
            return null;
        }
        return conn;
    }

    /**
     * @description 创建MYSQL连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initMysql(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            conn = DriverManager.getConnection(jdbcurl, username, password);
            conn.setAutoCommit(autoCommit);

           logger.debug("MYSQL DATABASE: Connection success!");
        }catch(Exception se) {
            logger.error("MYSQL连接失败",se);
            return null;
        }
        return conn;
    }

    /**
     * @description 创建SQLServer连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initSQLServer(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // 连接数据库
            conn = DriverManager.getConnection(jdbcurl, username, password);
            conn.setAutoCommit(autoCommit);
        } catch (Exception se) {
            logger.error("SQLSERVER连接失败",se);
            return null;
        }
        return conn;
    }


    /**
     * @description 创建GreenPlum连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initGreenPlum(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Class.forName("com.pivotal.jdbc.GreenplumDriver");
            conn = DriverManager.getConnection(jdbcurl,username, password);
            conn.setAutoCommit(autoCommit);
        } catch (Exception se) {
            logger.error("GREENPLUM连接失败",se);
            return null;
        }
        return conn;
    }

    /**
     * @description 创建HIVE连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @return
     */
    public static Connection initHIVE(String jdbcurl, String username, String password, boolean autoCommit) {
        Connection conn=null;
        try {
            Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection(jdbcurl,username, password);
            conn.setAutoCommit(autoCommit);
        } catch (Exception se) {
            logger.error("HIVE连接失败",se);
            return null;
        }
        return conn;
    }
    /**
     * @description 根据传入的数据源类型，创建Connection连接信息
     * @param jdbcurl
     * @param username
     * @param password
     * @param autoCommit
     * @param dbtype
     * @return
     */
    public static Connection intiConnection(String jdbcdriver,String jdbcurl, String username, String password, boolean autoCommit, String dbtype) {
        Connection conn = null;
        try {
            Class.forName(jdbcdriver);
            // 连接数据库
            conn = DriverManager.getConnection(jdbcurl, username, password);
            conn.setAutoCommit(autoCommit);
        } catch (Exception se) {
            logger.error("连接失败",se);
            return null;
        }
        return conn;
    }

    /**
     * 关闭数据库
     * @param conn
     * @param pstmt
     */
    public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            rs = null;

            if (pstmt != null) {
                pstmt.close();
            }
            pstmt = null;

            if (conn != null) {
                conn.close();
            }
            conn = null;
        } catch (Exception ee) {
            logger.error("数据源关闭失败",ee);
        }
    }

}
