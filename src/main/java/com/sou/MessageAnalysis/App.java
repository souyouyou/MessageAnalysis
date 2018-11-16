package com.sou.MessageAnalysis;

import com.sou.MessageAnalysis.company.GyFintech;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import util.PropertiesUtil;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class);

    private static String profile = Config.profile;
    private static String sparkMaster;
    private static String hdfsHost;
    private static String sourcePath;
    private static String gySourcePath;

    static {
        try{
            PropertiesUtil.loadProperties(profile + "/config.properties");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

//        sparkMaster = PropertiesUtil.getProperty("spark.master");
//        sparkMaster = "spark://10.0.1.95:7077";
        sparkMaster = "spark://10.0.1.95:7077";
//        hdfsHost = PropertiesUtil.getProperty("hdfs.host");
        hdfsHost = "hdfs://10.0.1.95:9000/";
//        sourcePath = PropertiesUtil.getProperty("source.path");
        sourcePath = "/data/test.txt";
//        gySourcePath = PropertiesUtil.getProperty("source.gy.path");
        gySourcePath = "/data/gy/";
    }

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("MessageAnalyse").setMaster(sparkMaster);

        JavaSparkContext jsc = new JavaSparkContext(conf);

        SQLContext sc = new SQLContext(jsc);

        GyFintech.msgStatistics(jsc,sc,hdfsHost,gySourcePath);
//      掌众数据统计
//      Weshare.statistics(jsc,sc,hdfsHost,sourcePath);




    }


}