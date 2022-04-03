package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.dao.TypeDao;
import com.example.steam.service.TypeService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.steam.Interface.TypeService;
@Controller
public class TypeController {

@Autowired
 private TypeService typeService;


@ResponseBody
@RequestMapping("/type/add")
public String addType(String kindName){
    return JSON.toJSONString(ResultMsg.SUCCESS(typeService.addType(kindName)));
}


@RequestMapping("/type/all")
@ResponseBody
public String findAllType(){
    return JSON.toJSONString(ResultMsg.SUCCESS(typeService.findAllType()));
}


@ResponseBody
@RequestMapping("/type/delete/{id}")
public String deleteType(long typeId){
    return JSON.toJSONString(ResultMsg.SUCCESS(typeService.deleteTypeById(typeId)));
}


}