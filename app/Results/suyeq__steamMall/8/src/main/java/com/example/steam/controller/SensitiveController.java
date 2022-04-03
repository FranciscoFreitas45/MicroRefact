package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.service.SensitiveWordService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SensitiveController {

@Autowired
 private SensitiveWordService sensitiveWordService;


@ResponseBody
@RequestMapping("/sensitive/save")
public String saveSensitive(String sensitive){
    String[] words = sensitive.split("\\|");
    return JSON.toJSONString(ResultMsg.SUCCESS(sensitiveWordService.handleSaveWord(words)));
}


}