package com.writedata.administrative.entity.uploaddata;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auth xude
 * @Date 2020-08-27 23:03
 * @Version 1.0
 */
@Data
public class ProvinceModel implements Serializable {
    private static final long serialVersionUID = -7510389295468919586L;

    private String code;

    private String name;

}
