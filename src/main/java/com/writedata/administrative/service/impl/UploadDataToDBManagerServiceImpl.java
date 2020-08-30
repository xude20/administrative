package com.writedata.administrative.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.writedata.administrative.dao.UploadDataToDBDAO;
import com.writedata.administrative.entity.uploaddata.VillagesModel;
import com.writedata.administrative.service.UploadDataToDBManagerService;
import com.writedata.administrative.utils.ReadJsonFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auth xude
 * @Date 2020-08-30 10:54
 * @Version 1.0
 */
@Service
public class UploadDataToDBManagerServiceImpl implements UploadDataToDBManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataToDBManagerServiceImpl.class);

    @Autowired
    private UploadDataToDBDAO uploadDataToDBDAO;

    @Override
    public String test() {
        return "成功进入到了service";
    }

    /**
     * @see UploadDataToDBManagerService#uploadData()
     */
    @Override
    public int uploadData() {
        String failString = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\provinces.json";
        String s = ReadJsonFileUtil.readJsonFile(failString);
        System.out.println(s);
        List<VillagesModel> dataModels = JSON.parseArray(s, VillagesModel.class);
        if (CollectionUtils.isEmpty(dataModels)) {
            LOGGER.error("啥都没有！");
            System.out.println("啥都没有");
            return 0;
        } else {
            System.out.println("包含了" + dataModels.size() + "个省份，第一个置是：" + dataModels.get(0).getName());
            //逻辑处理

        }
        return 0;
    }
}
