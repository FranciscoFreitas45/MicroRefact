package com.fosun.fc.projects.creepers.web.controller;
 import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersTourBlackListDTO;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;
import com.fosun.fc.projects.creepers.DTO.CreepersTourBlackListDTO;
@Controller
@RequestMapping("/touristBlackList")
public class TouristBlackListController extends BaseController{

 private  Logger logger;


@RequestMapping(value = "/init", method = RequestMethod.GET)
public String init(Model model){
    logger.info("========>TouristBlackListController-->init<========");
    model.addAttribute("typeList", BaseConstant.getTouristBlackListType());
    return "touristBlackList/touristBlackList";
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/list")
public String list(int pageNumber,int pageSize,String sortType,Model model,CreepersTourBlackListDTO paramDTO){
    Map<String, Object> map = new HashMap<String, Object>();
    map.put(CreepersConstant.TCreepersTourBlackListColumn.GUIDE_NO.getValue(), paramDTO.getGuideNo());
    map.put(CreepersConstant.TCreepersTourBlackListColumn.TYPE.getValue(), paramDTO.getType());
    map.put(CreepersConstant.TCreepersTourBlackListColumn.NAME.getValue(), paramDTO.getName());
    Map<String, Object> searchParams = TranslateMethodUtil.pageMapToEqSearchMap(map);
    logger.info("========>TouristBlackListController-->list<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersTourBlackListDTO> resultList = (Page<CreepersTourBlackListDTO>) creepersListServiceImpl.findList(searchParams, pageNumber, pageSize, sortType, BaseConstant.TaskListType.TOUR_BLACK_LIST.getValue());
    model.addAllAttributes(BeanMapper.toMap(paramDTO));
    model.addAttribute("resultList", resultList);
    model.addAttribute("typeList", BaseConstant.getTouristBlackListType());
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(map));
    return "touristBlackList/touristBlackList";
}


}