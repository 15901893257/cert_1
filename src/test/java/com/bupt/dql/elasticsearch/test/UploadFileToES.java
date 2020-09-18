package com.bupt.dql.elasticsearch.test;

import com.bupt.dql.file.FileAPI;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: mai
 * @date: 2019/7/10
 * 上传文件至ES
 */
public class UploadFileToES {

    private BulkProcessor bulkProcessor = new BulkProcessorAPI().getBulkProcessor();
    private FileAPI fileAPI = new FileAPI();
    private static int n = 0;   //统计文件上传数量



    /**
     * 遍历上传项目所有java文件至es
     * 将进程添加至 bulkProcessor
     */
    public void addTobulkProcessor(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("文件夹为空");
                return;
            } else {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        addTobulkProcessor(file1.getAbsolutePath());
                    } else if(file1.isFile()){
                        String filePath = file1.getAbsolutePath();
//                        System.out.println(file1);
                        String[] a = filePath.split("\\\\");
                        String user_filename = a[6]+"/"+file1.getName();
                        String type = a[5];
                        String code = fileAPI.readFile(file1).toString();
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("type",type);
                        map.put("user_filename",user_filename);
                        map.put("code",code);
                        bulkProcessor.add(new IndexRequest("test2").source(map));

//                        XContentBuilder xContentBuilder = null;
//                        try {
//                            xContentBuilder = XContentFactory.jsonBuilder()
//                                    .startObject()
//                                    .field("type",type)
//                                    .field("user_filename", user_filename)
//                                    .field("code", code)
//                                    .endObject();
//                            bulkProcessor.add(new IndexRequest("test2").source(xContentBuilder));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }finally {
//                            if (xContentBuilder != null){
//                                xContentBuilder.close();
//                            }
//                        }

                        n++;
                    }
                }

            }

        }
    }

    /**
     * 执行批处理操作
     * @param path
     */
    public void excuteBulkprocessor(String path){
        System.out.println("开始批处理操作");
        boolean finished = false;
        long startTime = System.currentTimeMillis();
        try {
            addTobulkProcessor(path);
        } catch (Exception e) {
            System.out.println("添加bulkProcessor请求出错");
            e.printStackTrace();
        }
//        } finally {
//            System.out.println("刷新剩余bulkProcessor请求");
//            bulkProcessor.flush(); // 刷新剩余的请求
//        }
        try {
            System.out.println("等待剩余的bulk执行完毕");
            bulkProcessor.flush(); // 刷新剩余的请求
            finished = bulkProcessor.awaitClose(30, TimeUnit.SECONDS);   //等待所有bulk都执行完毕再关闭
        } catch (InterruptedException e) {
            System.out.println("无法执行剩余的bulk");
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        if(finished){
            System.out.println("批量处理操作完成");
        }else {
            System.out.println("批量处理操作失败");
        }

        System.out.println("已上传文件："+n);
        System.out.println("用时："+time/60000+"分钟");
    }

    @Test
    public void testExcuteBulkprocessor(){
        excuteBulkprocessor("F:\\dql\\data\\html\\coding\\C_C++");
    }
}
