package com.bupt.dql.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author: mai
 * @date: 2019/7/10
 */
public class FileAPI {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
}
