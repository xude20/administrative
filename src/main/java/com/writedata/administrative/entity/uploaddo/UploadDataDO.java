package com.writedata.administrative.entity.uploaddo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auth xude
 * @Date 2020-08-30 13:20
 * @Version 1.0
 */
@Data
public class UploadDataDO implements Serializable {
    private static final long serialVersionUID = -4235928124022747119L;

    private Long id;

    private String code;

    private String name;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private String streetCode;

    private String parentCode;

    private Integer level;
}
