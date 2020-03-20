package com.yuqiliu.community.niuliucommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuqiliu
 * @create 2020-03-19  11:58
 */


@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
