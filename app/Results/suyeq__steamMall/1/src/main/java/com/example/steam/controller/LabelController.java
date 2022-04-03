package com.example.steam.controller;
 import com.alibaba.fastjson.JSON;
import com.example.steam.entity.Label;
import com.example.steam.service.LabelService;
import com.example.steam.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LabelController {

@Autowired
 private LabelService labelService;


@ResponseBody
@RequestMapping("/label/addone")
public String addLabel(String labelName){
    Label label = new Label(0L, labelName, 0);
    return JSON.toJSONString(labelService.addLabel(label));
}


@ResponseBody
@RequestMapping("/label/updatehot")
public String updateGameLabelHotNum(long gameId,long labelId){
    return JSON.toJSONString(labelService.updateHotNumByGameId(gameId, labelId));
}


@ResponseBody
@RequestMapping("/label/delete/{labelId}")
public String deleteLabel(long labelId){
    return JSON.toJSONString(labelService.deleteLabelById(labelId));
}


@ResponseBody
@RequestMapping("/label/gameid")
public String findLabelNameByGameId(long gameId){
    return JSON.toJSONString(ResultMsg.SUCCESS(labelService.findLabelsByGameId(gameId)));
}


@ResponseBody
@RequestMapping("/label/add")
public String addLabelInGame(long gameId,String labelName){
    return JSON.toJSONString(labelService.addLabelInGame(gameId, labelName));
}


}