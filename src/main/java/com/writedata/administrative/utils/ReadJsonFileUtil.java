package com.writedata.administrative.utils;


import com.alibaba.fastjson.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.writedata.administrative.entity.uploaddata.ProvinceModel;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;

/**
 * 读取文件中的json文件
 *
 * @Auth xude
 * @Date 2020-08-27 22:31
 * @Version 1.0
 */
public class ReadJsonFileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadJsonFileUtil.class);

    /*public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File file = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            char[] chars = new char[1024];
            int size = 0;
            StringBuilder stringBuffer = new StringBuilder();
            while ((size = bufferedReader.read(chars, 0, chars.length)) != -1) {
                stringBuffer.append(chars);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            LOGGER.error("读取json文件发生异常");
            return null;
        }

    }*/

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        char[] chars = new char[20];
        int size = 0;
        while ((size = bufferedReader.read(chars, 0, chars.length)) != -1) {
            System.out.println(new String(chars, 0, size));
        }
//        File json = new File("H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\provinces.json");
        String failString = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\provinces.json";
        String s = ReadJsonFileUtil.readJsonFile(failString);
        System.out.println(s);
        List<ProvinceModel> provinceModels = JSON.parseArray(s, ProvinceModel.class);
        if (CollectionUtils.isEmpty(provinceModels)) {
            LOGGER.error("啥都没有！");
            System.out.println("啥都没有");
        } else {
            System.out.println("包含了" + provinceModels.size() + "个省份，第一个置是：" + provinceModels.get(0).getName());
        }

    }
}
