package com.writedata.administrative.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.writedata.administrative.dao.UploadDataToDBDAO;
import com.writedata.administrative.entity.datenum.ProvinceEnum;
import com.writedata.administrative.entity.uploaddo.UploadDataDO;
import com.writedata.administrative.service.UploadDataToDBManagerService;
import com.writedata.administrative.utils.ReadJsonFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Auth xude
 * @Date 2020-08-30 10:54
 * @Version 1.0
 */
@Component
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
    public String uploadData() {
        StringBuilder stringBuilder = new StringBuilder();
        String provinces = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\provinces.json";
        String cities = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\cities.json";
        String areas = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\areas.json";
        String streets = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\streets.json";
        String villages = "H:\\xude work\\xude20-Administrative-divisions-of-China-master\\Administrative-divisions-of-China\\dist\\villages.json";
        List<String> urls = new ArrayList<>();
        urls.add(provinces);
        urls.add(cities);
        urls.add(areas);
        urls.add(streets);
        urls.add(villages);
        for (String url :
                urls) {
            String s = insertIntoDB(url);
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }

    private String insertIntoDB(String url) {
        String s = ReadJsonFileUtil.readJsonFile(url);
        System.out.println(s);
        StringBuilder stringBuilder = new StringBuilder();

        List<UploadDataDO> dataModels = JSON.parseArray(s, UploadDataDO.class);
        if (CollectionUtils.isEmpty(dataModels)) {
            LOGGER.error("啥都没有！");
            System.out.println("啥都没有");
        } else {
            stringBuilder.append("转换后的数组size：").append(dataModels.size());
            System.out.println("包含了" + dataModels.size() + "个省份，第一个置是：" + dataModels.get(0).getName());
            //逻辑处理
            List<UploadDataDO> collect = dataModels.stream()
                    .filter(Objects::nonNull)
                    .map(this::convert)
                    .collect(Collectors.toList());
            collect.forEach(i -> {
                uploadDataToDBDAO.insert(i);
            });
        }
        int i = uploadDataToDBDAO.selectCount();
        stringBuilder.append("查询数据库现在的数量：").append(i).append("\r\n");
        return stringBuilder.toString();
    }

    private UploadDataDO convert(UploadDataDO model) {
        UploadDataDO uploadDataDO = new UploadDataDO();
        BeanUtils.copyProperties(model, uploadDataDO);
        //parentCode 赋值
        if (StringUtils.isNotEmpty(uploadDataDO.getStreetCode())) {
            //那么这条数据就是 乡村的数据，parentCode就应该为streetCode的值
            uploadDataDO.setParentCode(uploadDataDO.getStreetCode());
            uploadDataDO.setLevel(ProvinceEnum.VILLAGE.getLevel());

        } else if (StringUtils.isNotEmpty(uploadDataDO.getAreaCode())) {
            //同理往下推 街道的父级
            uploadDataDO.setParentCode(uploadDataDO.getAreaCode());
            uploadDataDO.setLevel(ProvinceEnum.STREET.getLevel());

        } else if (StringUtils.isNotEmpty(uploadDataDO.getCityCode())) {
            //同理往下推，县的父级
            uploadDataDO.setParentCode(uploadDataDO.getCityCode());
            uploadDataDO.setLevel(ProvinceEnum.AREA.getLevel());

        } else if (StringUtils.isNotEmpty(uploadDataDO.getProvinceCode())) {
            //同理往下推，市的父级
            uploadDataDO.setParentCode(uploadDataDO.getProvinceCode());
            uploadDataDO.setLevel(ProvinceEnum.CITY.getLevel());
        } else {
            //省
            uploadDataDO.setParentCode("1");
            uploadDataDO.setLevel(ProvinceEnum.PROVINCE.getLevel());
        }
        return uploadDataDO;
    }
}
