package com.example.hugh.life.commmon.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class FileUtil {
    public static String readFile(String pathName) {
//        String pathname = "C:\\Users\\zhengliy\\Desktop\\a.html";
        if(StringUtils.isEmpty(pathName)){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        FileReader reader = null;
        try {
            reader = new FileReader(pathName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void writeFile(String fileContent){

        FileWriter fileWriter = null;
        try {
            File writeName = new File("C:\\Users\\LKB\\Desktop\\baidu_hahaha.html"); //
            fileWriter = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(fileContent); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
