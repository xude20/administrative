package com.writedata.administrative.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auth xude
 * @Date ${DATE} ${TIME}
 * @Version 1.0
 */
@Controller
public class AdministrativeController {

    @GetMapping("writeToDB.json")
    public String writeToDB() {

        return "测试一下结果";
    }

}
