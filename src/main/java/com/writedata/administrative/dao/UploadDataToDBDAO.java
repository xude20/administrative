package com.writedata.administrative.dao;

import com.writedata.administrative.entity.uploaddo.UploadDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Auth xude
 * @Date 2020-08-30 11:13
 * @Version 1.0
 */
@Mapper()
@Component(value = "uploadDataToDBDAO")
public interface UploadDataToDBDAO {

    int selectCount();


    void insert(UploadDataDO uploadDataDO);
}
