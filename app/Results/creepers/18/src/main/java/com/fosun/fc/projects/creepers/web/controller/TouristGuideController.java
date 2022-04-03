package com.fosun.fc.projects.creepers.web.controller;
 import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fosun.fc.modules.mapper.BeanMapper;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListTourGuideDTO;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;
import com.fosun.fc.projects.creepers.Interface.ICreepersTouristGuideService;
import com.fosun.fc.projects.creepers.DTO.CreepersListTourGuideDTO;
@Controller
@RequestMapping("/touristGuide")
public class TouristGuideController extends BaseController{

 private  Logger logger;

@Autowired
 private  ICreepersTouristGuideService creepersTouristGuideServiceImpl;


@RequestMapping(value = "/init", method = RequestMethod.GET)
public String init(){
    logger.info("========>TouristGuideController-->init<========");
    return "touristGuide/touristGuideList";
}


@ResponseBody
@RequestMapping(value = "/doRecycle")
public Map<String,String> doRecycle(String guideNo,String cardNo,String certNo){
    logger.info("========>TouristGuideController-->doRecycle<========");
    logger.info("========>查询参数guideNo：" + guideNo + "<========");
    logger.info("========>查询参数cardNo：" + cardNo + "<========");
    logger.info("========>查询参数certNo：" + certNo + "<========");
    CreepersLoginParamDTO param = new CreepersLoginParamDTO();
    param.putSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.GUIDE_NO.getValue(), guideNo);
    param.putSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CARD_NO.getValue(), cardNo);
    param.putSearchKeyWord(CreepersConstant.TCreepersListTourGuideColumn.CERT_NO.getValue(), certNo);
    param.setTaskType(BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
    creepersListServiceImpl.doRecycleByParam(param);
    Map<String, String> resultMap = new HashMap<String, String>();
    resultMap.put("message", "success");
    return resultMap;
}


@RequestMapping(value = "/queryLog")
public String queryLog(int pageNumber,int pageSize,String sortType,Model model,CreepersListTourGuideDTO paramDTO){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    String merName = "";
    if (StringUtils.isNotBlank(paramDTO.getGuideNo())) {
        merName = paramDTO.getGuideNo();
    } else {
        merName = paramDTO.getCardNo();
    }
    searchParams.put("EQ_merName", merName);
    searchParams.put("EQ_taskType", BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
    logger.info("========>TouristGuideController-->queryLog<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(BeanMapper.toMap(paramDTO));
    return "creeperTask/errorLog";
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/list")
public String list(int pageNumber,int pageSize,String sortType,Model model,CreepersListTourGuideDTO paramDTO){
    Map<String, Object> map = new HashMap<String, Object>();
    BeanMapper.copy(paramDTO, map);
    Map<String, Object> searchParams = TranslateMethodUtil.pageMapToEqSearchMap(map);
    logger.info("========>TouristGuideController-->list<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersListTourGuideDTO> resultList = (Page<CreepersListTourGuideDTO>) creepersListServiceImpl.findList(searchParams, pageNumber, pageSize, sortType, BaseConstant.TaskListType.TOUR_GUIDE_LIST.getValue());
    model.addAllAttributes(BeanMapper.toMap(paramDTO));
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(map));
    return "touristGuide/touristGuideList";
}


@RequestMapping(value = "/queryInfo")
public String queryInfo(Model model,String guideNo){
    logger.info("========>TouristGuideController-->queryInfo<========");
    logger.info("========>查询参数LoginName：" + guideNo + "<========");
    CreepersLoginParamDTO param = new CreepersLoginParamDTO();
    param.setLoginName(guideNo);
    Map<String, Object> resultMap = creepersTouristGuideServiceImpl.findByParamForMap(param);
    model.addAttribute("tourGuideList", resultMap.get(CreepersConstant.TableNamesOthers.T_CREEPERS_TOUR_GUIDE.getMapKey()));
    return "touristGuide/touristGuideDetail";
}


}