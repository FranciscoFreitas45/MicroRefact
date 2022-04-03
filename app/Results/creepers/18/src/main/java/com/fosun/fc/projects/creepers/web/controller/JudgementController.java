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
import com.fosun.fc.projects.creepers.dto.CreepersJudgementDTO;
import com.fosun.fc.projects.creepers.dto.CreepersListJudgementDTO;
import com.fosun.fc.projects.creepers.service.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.service.ICreepersListJudgementService;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;
import com.fosun.fc.projects.creepers.Interface.ICreepersJudgementService;
import com.fosun.fc.projects.creepers.Interface.ICreepersListJudgementService;
@Controller
@RequestMapping(value = "/judgement")
public class JudgementController extends BaseController{

 private  Logger logger;

@Autowired
 private  ICreepersJudgementService creepersJudgementServiceImpl;

@Autowired
 private  ICreepersListJudgementService creepersListJudgementServiceImpl;


@RequestMapping(value = "/init", method = RequestMethod.GET)
public String init(){
    logger.info("========>judgementController-->init<========");
    return "judgement/judgementList";
}


@ResponseBody
@RequestMapping(value = "/doRecycle")
public Map<String,String> doRecycle(String merName){
    logger.info("========>judgementController-->doRecycle<========");
    logger.info("========>查询参数：merName=" + merName + "<========");
    creepersListServiceImpl.doRecycleByMerName(BaseConstant.TaskListType.JUDGEMENT_LIST.getValue(), merName);
    Map<String, String> resultMap = new HashMap<String, String>();
    resultMap.put("message", "success");
    return resultMap;
}


@RequestMapping(value = "/queryLog")
public String queryLog(int pageNumber,int pageSize,String sortType,Model model,String merName){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_merName", merName);
    searchParams.put("EQ_taskType", BaseConstant.TaskListType.JUDGEMENT_LIST.getValue());
    logger.info("========>judgementController-->queryLog<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersErrorListDTO> resultList = creepersErrorListServiceImpl.queryErrorList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
    return "creeperTask/errorLog";
}


@RequestMapping(value = "/list")
public String list(int pageNumber,int pageSize,String sortType,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
    logger.info("========>judgementController-->list<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersListJudgementDTO> resultList = creepersListJudgementServiceImpl.findListJudgementList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAttribute("sortType", sortType);
    model.addAttribute("param", TranslateMethodUtil.buildPageSearchParam(searchParams, "search_"));
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap(searchParams, "search_"));
    return "judgement/judgementList";
}


@RequestMapping(value = "/queryInfo")
public String queryInfo(int pageNumber,int pageSize,String sortType,Model model,String merName){
    Map<String, Object> searchParams = new HashMap<String, Object>();
    searchParams.put("EQ_merName", merName);
    logger.info("========>judgementController-->queryInfo<========");
    logger.info("========>查询参数：" + searchParams + "<========");
    Page<CreepersJudgementDTO> resultList = creepersJudgementServiceImpl.queryJudgementList(searchParams, pageNumber, pageSize, sortType);
    model.addAttribute("resultList", resultList);
    model.addAllAttributes(TranslateMethodUtil.buildPageSearchParamMap("merName", merName));
    return "judgement/judgementDetail";
}


}