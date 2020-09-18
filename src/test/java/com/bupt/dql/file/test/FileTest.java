package com.bupt.dql.file.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: mai
 * @date: 2019/7/10
 * 文件处理操作
 */
public class FileTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private int n = 0;
    private String filename;

    /**
     * 打印文件内容
     * 要将各种输入流和输出流关闭，否则程序运行速度很慢，或者有问题
     * @param file
     */
    public StringBuilder readFile(File file){
        StringBuilder stringBuilder = null;
        if (file.isFile()) {
            stringBuilder = new StringBuilder();
            InputStreamReader inputStreamreader = null;
            BufferedReader bufferedReader = null;

            try {
                inputStreamreader = new InputStreamReader(new FileInputStream(file), "utf-8");
                bufferedReader = new BufferedReader(inputStreamreader);
                String lineStr = null;
                while ((lineStr = bufferedReader.readLine()) != null) {
                    stringBuilder.append(lineStr + "\n");
                }
            } catch (IOException e) {
                logger.error("文件IO异常：{}", e);
            } finally {
                if (inputStreamreader != null) {
                    try {
                        inputStreamreader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return stringBuilder;
    }

    /**
     * 循环打印file
     * @param filePath
     */
    public void print(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            File[] files = file.listFiles();
            if(files == null || files.length == 0){
                System.out.println("文件夹为空");
                return;
            }else {
                for(File file1 :files){
                    if(file1.isDirectory()){
                        print(file1.getAbsolutePath());
                    }else if(file1.isFile()){
                        n++;
                        if(n%1000 == 0){
                            System.out.println(n);
                        }
//                        String filePath1 = file1.getAbsolutePath();
//                        String[] a = filePath1.split("\\\\");
//                        String user = a[6];
//                        String type = a[5];
//                        String code = readFile(file1).toString();
//                        String filename = file1.getName();
//                        System.out.println(filePath1);
//                        System.out.println(user);
//                        System.out.println(type);
//                        System.out.println(filename);
//                        System.out.println(code);
                    }
                }
            }
        }

    }

    @Test
    public void testReadFile(){
        File file1 = new File("F:\\dql\\data\\test1.java");

        File file = new File("F:\\dql\\data\\html\\coding\\Java\\0xC000005\\BigData\\1491790210000\\BigData-master\\src\\org\\apache\\hadoop\\io\\nativeio\\NativeIO.java");
        readFile(file);
    }

    @Test
    public void testPrint(){
        long start_time = System.currentTimeMillis();
        print("F:\\dql\\data\\html\\coding\\C_C++");
        long end_time = System.currentTimeMillis();
        long time = end_time - start_time;
        System.out.println("消耗时间为："+time/1000+"s");
        System.out.println("文件数量："+n);
    }
}
