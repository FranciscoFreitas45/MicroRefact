package org.danyuan.application.dbms.tabs.controller;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsTypeInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsUserIndexInfo;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsColsInfoService;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsInfoService;
import org.danyuan.application.dbms.tabs.service.SysDbmsTabsTypeInfoService;
import org.danyuan.application.dbms.tabs.service.SysDbmsUserIndexInfoService;
import org.danyuan.application.dbms.tabs.service.ZhcxService;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsColsInfoVo;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.danyuan.application.Interface.SysDbmsTabsTypeInfoService;
import org.danyuan.application.Interface.SysDbmsUserIndexInfoService;
@RestController
@RequestMapping("/zhcx")
public class ZhcxController {

 private  Logger logger;

@Autowired
 private  SysDbmsTabsTypeInfoService sysDbmsTabsTypeInfoService;

@Autowired
 private  SysDbmsTabsInfoService sysDbmsTabsInfoService;

@Autowired
 private  SysDbmsTabsColsInfoService sysDbmsTabsColsInfoService;

@Autowired
 private SysDbmsUserIndexInfoService sysDbmsUserIndexInfoService;

@Autowired
 private  ZhcxService zhcxService;


@RequestMapping(path = "/findAllTable", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllTable(SysDbmsTabsInfoVo vo){
    logger.info("findAllTable", ZhcxController.class);
    List<SysDbmsTabsInfo> list = sysDbmsTabsInfoService.findAllTable(vo);
    if (list != null) {
        return list;
    } else {
        return new ArrayList<>();
    }
}


@RequestMapping(path = "/findAllTableByTypeUuidAndUsername", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllTableByTypeUuidAndUsername(SysDbmsTabsInfoVo vo){
    logger.info("findAllTableByTypeUuidAndUsername", ZhcxController.class);
    List<SysDbmsTabsInfo> list = new ArrayList<>();
    if (vo.getInfo() != null) {
        list = sysDbmsTabsInfoService.findAllTableByTypeUuidAndUsername(vo);
    }
    return list;
}


@RequestMapping(path = "/findAllType", method = RequestMethod.POST)
public List<SysDbmsTabsTypeInfo> findAllType(SysDbmsTabsInfoVo vo){
    logger.info("findAllType", ZhcxController.class);
    List<SysDbmsTabsTypeInfo> list = sysDbmsTabsTypeInfoService.findAll();
    return list;
}


@RequestMapping(path = "/findAllColumn", method = RequestMethod.POST)
public List<SysDbmsTabsColsInfo> findAllColumn(SysDbmsTabsColsInfoVo vo){
    logger.info("findAllColumn", ZhcxController.class);
    List<SysDbmsTabsColsInfo> list = sysDbmsTabsColsInfoService.findAll(vo.getInfo());
    return list;
}


@RequestMapping(path = "/forwardZhlb", method = RequestMethod.POST)
public ModelAndView forwardZhlb(SysDbmsTabsInfoVo vo){
    logger.info("forwardZhlb", ZhcxController.class);
    ModelAndView view = new ModelAndView("zhcx/search/zhlb");
    view.addObject("tabsUuid", vo.getTabsuuid());
    view.addObject("jdbcUuid", vo.getJdbcUuid());
    view.addObject("tabsDesc", vo.getTabsDesc());
    view.addObject("tabsName", vo.getTabsName());
    view.addObject("dbType", vo.getDbType());
    view.addObject("esName", vo.getEsName());
    view.addObject("tabsRows", vo.getTabsRows());
    view.addObject("type", vo.getType());
    view.addObject("paramString", vo.getParamString());
    return view;
}


@RequestMapping(path = "/findAllTableByUser", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllTableByUser(SysDbmsTabsInfoVo vo){
    logger.info("findAllTable", ZhcxController.class);
    List<SysDbmsTabsInfo> list = sysDbmsTabsInfoService.findAllTableByUser(vo);
    if (list != null) {
        return list;
    } else {
        return new ArrayList<>();
    }
}


@RequestMapping(path = "/forwardYjcx", method = RequestMethod.POST)
public ModelAndView forwardYjcx(SysDbmsTabsInfoVo vo){
    logger.info("forwardYjcx", ZhcxController.class);
    ModelAndView view = new ModelAndView("zhcx/search/yjcx");
    List<SysDbmsUserIndexInfo> list = sysDbmsUserIndexInfoService.findAll();
    view.addObject("type", vo.getType());
    view.addObject("list", list);
    view.addObject("paramString", vo.getParamString());
    return view;
}


@RequestMapping(path = "/findAllTableRow", method = { RequestMethod.GET, RequestMethod.POST })
@JsonIgnore
public Map<String,Object> findAllTableRow(SysDbmsTabsColsInfoVo vo) throws JsonParseException{
    logger.info("findAllTableRow", ZhcxController.class);
    Map<String, Object> map = new HashMap<>();
    if ("oracle".equals(vo.getDbType()) || "mysql".equals(vo.getDbType())) {
        // if ("单表多条件查询".equals(vo.getType())) {
        // map = zhcxService.findAllSigleTableByMulteityParam(vo);
        // } else if ("一键查询单表多个不同索引拼接".equals(vo.getType()) || "单表多条件更多查询".equals(vo.getType())) {
        // 一键查询单表多个不同索引拼接
        map = zhcxService.findBySingleTableByGroupsAndMulteityParam(vo);
    // }
    } else if ("elastic".equals(vo.getDbType())) {
    // // elasticsearch
    // map = zhcxService.findByElasticsearchByGroupsAndMulteityParam(vo);
    }
    return map;
}


@RequestMapping(path = "/findAllTableByTypeUuid", method = RequestMethod.POST)
public List<SysDbmsTabsInfo> findAllTableByTypeUuid(SysDbmsTabsInfoVo vo){
    logger.info("findAllTableByTypeUuid", ZhcxController.class);
    List<SysDbmsTabsInfo> list = new ArrayList<>();
    if (vo.getInfo() != null) {
        list = sysDbmsTabsInfoService.findAll(vo.getInfo());
    }
    return list;
}


@RequestMapping(path = "/forwardChart", method = RequestMethod.POST)
public ModelAndView forwardChart(SysDbmsTabsColsInfoVo vo){
    logger.info("forwardChart", ZhcxController.class);
    ModelAndView view = new ModelAndView("zhcx/search/chart");
    view.addObject("paramString", vo.getParamString());
    return view;
}


@RequestMapping(path = "/findAllTypeByUser", method = RequestMethod.POST)
public List<SysDbmsTabsTypeInfo> findAllTypeByUser(SysDbmsTabsInfoVo vo){
    logger.info("findAllType", ZhcxController.class);
    List<SysDbmsTabsTypeInfo> list = sysDbmsTabsTypeInfoService.findAllTypeByUser(vo.getUsername());
    return list;
}


@RequestMapping(path = "/forwardZhxx", method = RequestMethod.POST)
public ModelAndView forwardZhxx(SysDbmsTabsColsInfoVo vo){
    logger.info("forwardZhxx", ZhcxController.class);
    ModelAndView view = new ModelAndView("zhcx/search/zhxx");
    view.addObject("mapString", vo.getMapString());
    view.addObject("tabsName", vo.getTabsName());
    view.addObject("tabsDesc", vo.getTabsDesc());
    view.addObject("paramString", vo.getParamString());
    return view;
}


}