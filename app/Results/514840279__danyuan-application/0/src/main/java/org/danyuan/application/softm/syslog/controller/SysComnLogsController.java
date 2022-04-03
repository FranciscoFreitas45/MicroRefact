package org.danyuan.application.softm.syslog.controller;
 import org.danyuan.application.softm.syslog.po.SysComnLogs;
import org.danyuan.application.softm.syslog.po.VSysComnLogs;
import org.danyuan.application.softm.syslog.service.SysComnLogsService;
import org.danyuan.application.softm.syslog.vo.SysComnLogsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/sysComnLogs")
@Api(value = "/SysComnLogs", description = "索引字典")
public class SysComnLogsController {

 private  Logger logger;

@Autowired
 private SysComnLogsService sysComnLogsService;


@ApiOperation(value = "查询综合查询信息", notes = "")
@RequestMapping(path = "/findAllZhcx", method = RequestMethod.POST)
public Page<VSysComnLogs> findAllZhcx(SysComnLogsVo vo){
    logger.info("findAllZhcx", SysComnLogsController.class);
    return sysComnLogsService.findAllZhcx(vo);
}


@ApiOperation(value = "查询响应时间信息", notes = "")
@RequestMapping(path = "/findAllLongtime", method = RequestMethod.POST)
public Page<SysComnLogs> findAllLongtime(SysComnLogsVo vo){
    logger.info("findAllLongtime", SysComnLogsController.class);
    return sysComnLogsService.findAllLongtime(vo);
}


@ApiOperation(value = "查询错误信息", notes = "")
@RequestMapping(path = "/findAllError", method = RequestMethod.POST)
public Page<SysComnLogs> findAllError(SysComnLogsVo vo){
    logger.info("findAllError", SysComnLogsController.class);
    return sysComnLogsService.findAllError(vo);
}


@ApiOperation(value = "查询全部信息", notes = "")
@RequestMapping(path = "/page", method = RequestMethod.POST)
public Page<SysComnLogs> page(SysComnLogsVo vo){
    logger.info("findAllError", SysComnLogsController.class);
    return sysComnLogsService.findAll(vo);
}


}