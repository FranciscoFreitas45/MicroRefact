package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.service.SystemNeedService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SystemNeedController {

@Autowired
 private SystemNeedService systemNeedService;


@ResponseBody
@RequestMapping("/systemneed/{id}")
public String findSystemNeedById(long id){
    return JSON.toJSONString(ResultMsg.SUCCESS(systemNeedService.findSystemNeedById(id)));
}


}