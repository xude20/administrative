package com.writedata.administrative.entity.datenum;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auth xude
 * @Date 2020-08-30 14:03
 * @Version 1.0
 */
public enum ProvinceEnum {
    PROVINCE("province", "省", 1),
    CITY("city", "市", 2),
    AREA("area", "区/县", 3),
    STREET("street", "街道", 4),
    VILLAGE("village", "乡/镇", 5);

    private String code;
    private String name;
    private int level;

    public static String getCode(String param) {
        if (StringUtils.isEmpty(param)) {
            return null;
        }

        for (ProvinceEnum provinceEnum :
             ProvinceEnum.values()) {
            if (provinceEnum.getCode().equalsIgnoreCase(param) || provinceEnum.getName().equals(param)) {
                return provinceEnum.getCode();
            }
        }
        return null;
    }

    public static String getCode(int param) {
        if (0 == param) {
            return null;
        }

        for (ProvinceEnum provinceEnum :
                ProvinceEnum.values()) {
            if (provinceEnum.getLevel() == param) {
                return provinceEnum.getCode();
            }
        }
        return null;
    }

    public static String getName(String param) {
        if (StringUtils.isEmpty(param)) {
            return null;
        }

        for (ProvinceEnum provinceEnum :
                ProvinceEnum.values()) {
            if (provinceEnum.getCode().equalsIgnoreCase(param) || provinceEnum.getName().equals(param)) {
                return provinceEnum.getName();
            }
        }
        return null;
    }

    public static String getName(int param) {
        if (0 == param) {
            return null;
        }

        for (ProvinceEnum provinceEnum :
                ProvinceEnum.values()) {
            if (provinceEnum.getLevel() == param) {
                return provinceEnum.getName();
            }
        }
        return null;
    }

    public static Integer getLevel(String param) {
        if (StringUtils.isEmpty(param)) {
            return null;
        }

        for (ProvinceEnum provinceEnum :
                ProvinceEnum.values()) {
            if (provinceEnum.getCode().equalsIgnoreCase(param) || provinceEnum.getName().equals(param)) {
                return provinceEnum.getLevel();
            }
        }
        return null;
    }

    ProvinceEnum(String code, String name, int level) {
        this.code = code;
        this.name = name;
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
