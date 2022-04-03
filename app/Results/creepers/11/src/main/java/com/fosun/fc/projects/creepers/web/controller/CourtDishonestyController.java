package com.fosun.fc.projects.creepers.web.controller;
 import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fosun.fc.projects.creepers.dto.CreepersCourtDisInfoDTO;
import com.fosun.fc.projects.creepers.dto.CreepersCourtDishonestDTO;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDisInfoService;
import com.fosun.fc.projects.creepers.service.ICreepersCourtDishonestyService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;
@Controller
@RequestMapping(value = "/courtDishonesty")
public class CourtDishonestyController extends BaseController{

 private  Logger logger;

@Autowired
 private  ICreepersCourtDishonestyService creepersCourtDishonestyServiceImpl;

@Autowired
 private  ICreepersCourtDisInfoService creepersCourtDisInfoServiceImpl;


@RequestMapping(value = "/init", method = RequestMethod.GET)
public String init(){
    logger.info("========>CourtDishonestyController-->init<========");
    return "courtDishonesty/courtDishonestyList";
}


@RequestMapping(value = "/list")
public String list(int pageNumber,int pageSize,String sortType,Model model,String name){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_name", name);
    logger.info("========>CourtDishonestyController-->list<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersCourtDishonestDTO> resultList = (Page<CreepersCourtDishonestDTO>) creepersCourtDishonestyServiceImpl.findList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAttribute("name", name);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams));
    return "courtDishonesty/courtDishonestyList";
}


@RequestMapping(value = "/queryInfo")
public String queryInfo(int pageNumber,int pageSize,String sortType,Model model,String name){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_name", name);
    logger.info("========>CourtDishonestyController-->queryInfo<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersCourtDisInfoDTO> resultList = creepersCourtDisInfoServiceImpl.findList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("name", name));
    return "courtDishonesty/courtDishonestyDetail";
}


}