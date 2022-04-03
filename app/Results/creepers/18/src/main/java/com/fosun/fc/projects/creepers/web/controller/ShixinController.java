package com.fosun.fc.projects.creepers.web.controller;
 import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
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
import com.fosun.fc.modules.web.Servlets;
import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersErrorListDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListShixinDTO;
import com.fosun.fc.projects.creepers.dto.CreepersShixinDTO;
import com.fosun.fc.projects.creepers.service.ICreepersShixinService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;
import com.fosun.fc.projects.creepers.Interface.ICreepersShixinService;
@Controller
@RequestMapping(value = "/shixin")
public class ShixinController extends BaseController{

 private  Logger logger;

@Autowired
 private  ICreepersShixinService creepersShixinServiceImpl;


@RequestMapping(value = "/init", method = RequestMethod.GET)
public String init(){
    logger.info("========>ShixinController-->init<========");
    return "shixin/shixinList";
}


@ResponseBody
@RequestMapping(value = "/doRecycle")
public Map<String,String> doRecycle(String merName){
    logger.info("========>ShixinController-->doRecycle<========");
    logger.info("========>重爬参数merName：" + merName + "<========");
    creepersListServiceImpl.doRecycleByMerName(BaseConstant.TaskListType.SHI_XIN_LIST.getValue(), merName);
    Map<String, String> resultMap = new HashMap<String, String>();
    resultMap.put("message", "success");
    return resultMap;
}


@RequestMapping(value = "/queryLog")
public String queryLog(int pageNumber,int pageSize,String sortType,Model model,String merName){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_merName", merName);
    searchParams.put("EQ_taskType", BaseConstant.TaskListType.SHI_XIN_LIST.getValue());
    logger.info("========>ShixinController-->queryLog<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
    return "creeperTask/errorLog";
}


@SuppressWarnings("unchecked")
@RequestMapping(value = "/list")
public String list(int pageNumber,int pageSize,String sortType,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    logger.info("========>ShixinController-->list<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersListShixinDTO> resultList = (Page<CreepersListShixinDTO>) creepersListServiceImpl.findList(searchParams, pageNumber, pageSize, sortType, BaseConstant.TaskListType.SHI_XIN_LIST.getValue());
    model.addAttribute("resultList", resultList);
    model.addAttribute("sortType", sortType);
    model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
    return "shixin/shixinList";
}


@RequestMapping(value = "/queryInfo")
public String queryInfo(int pageNumber,int pageSize,String sortType,Model model,String merName){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_name", merName);
    logger.info("========>ShixinController-->queryInfo<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersShixinDTO> resultList = creepersShixinServiceImpl.findList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
    return "shixin/shixinDetail";
}


}