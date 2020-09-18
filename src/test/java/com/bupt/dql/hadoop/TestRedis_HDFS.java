package com.bupt.dql.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Set;

/**
 * @author: mai
 * @date: 2019/8/12
 */
public class TestRedis_HDFS {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String HDFS_PATH="hdfs://10.103.246.68:8020";
    public static final String BASE_DIR = "/home/xiongy/repository/";

    private static long start_time;
    private static long end_time;

    FileSystem fileSystem = null;
    Configuration configuration = null;

    public void domain(){

    }

    public void date_ship(String index_path){
        String hdfs_path = BASE_DIR+index_path+".repo";


    }

    /**
     * 建立连接
     */
    @Before
    public void setUp(){
        start_time = System.currentTimeMillis();
        configuration = new Configuration();
        configuration.set("fs.defaultFS",HDFS_PATH);
        try {
//            System.setProperty("HADOOP_USER_NAME", "tom");
            fileSystem = FileSystem.get(configuration);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(""+e);
        }
        logger.info("建立连接成功.......");
    }

    /**
     * 关闭连接
     */
    @After
    public void tearDown() throws IOException {
        fileSystem.close();
        configuration = null;
        fileSystem = null;
        logger.info("连接关闭...........");
    }


}
