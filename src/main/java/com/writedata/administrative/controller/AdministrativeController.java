package com.writedata.administrative.controller;

import com.alibaba.fastjson.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.writedata.administrative.entity.uploaddata.VillagesModel;
import com.writedata.administrative.service.UploadDataToDBManagerService;
import com.writedata.administrative.utils.ReadJsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auth xude
 * @Date ${DATE} ${TIME}
 * @Version 1.0
 */
@Controller
public class AdministrativeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministrativeController.class);

    @Autowired
    private UploadDataToDBManagerService uploadDataToDBManagerService;

    @GetMapping("/writeToDB.json")
    @ResponseBody
    public String writeToDB() {
        int result = uploadDataToDBManagerService.uploadData();

        return "插入成功条数：" + result;
    }

    @GetMapping("/uploadFileToDB.json")
    @ResponseBody
    public String uploadFileToDB() {
        String fileStr = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\provinces.json";
        String jsonStr = ReadJsonFileUtil.readJsonFile(fileStr);
        System.out.println(jsonStr);

//        List<ProvinceModel> provinceModels = JSON.parseArray(jsonStr, ProvinceModel.class);
        List<VillagesModel> provinceModels = JSON.parseArray(jsonStr, VillagesModel.class);
        if (CollectionUtils.isEmpty(provinceModels)) {
            LOGGER.error("啥都没有！");
            return "啥都没有！";
        }
        return "包含了" + provinceModels.size() + "个省份，第一个置是：" + provinceModels.get(0).getName();
    }


}
