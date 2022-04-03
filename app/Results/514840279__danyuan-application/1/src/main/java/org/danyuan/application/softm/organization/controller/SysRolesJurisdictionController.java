package org.danyuan.application.softm.organization.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.common.base.ResultUtil;
import org.danyuan.application.softm.organization.po.SysRolesJurisdictionInfo;
import org.danyuan.application.softm.organization.service.SysRolesJurisdictionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/sysRolesJurisdiction")
public class SysRolesJurisdictionController extends BaseControllerImpl<SysRolesJurisdictionInfo>implements BaseController<SysRolesJurisdictionInfo>{

 private  Logger logger;

@Autowired
 private  SysRolesJurisdictionInfoService sysRolesJurisdictionInfoService;


@Override
@RequestMapping(path = "/saveAll", method = RequestMethod.POST)
@ResponseBody
public BaseResult<SysRolesJurisdictionInfo> saveAll(Pagination<SysRolesJurisdictionInfo> vo){
    logger.info("saveAll", SysRolesJurisdictionController.class);
    try {
        sysRolesJurisdictionInfoService.saveAll(vo.getList());
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(e.getMessage());
    }
}


@RequestMapping(path = "/findAllBySearchText", method = RequestMethod.POST)
public Page<SysRolesJurisdictionInfo> findAllBySearchText(int pageNumber,int pageSize,SysRolesJurisdictionInfo sysRolesJurisdictionInfo){
    logger.info("findAllBySearchText", SysRolesJurisdictionController.class);
    return sysRolesJurisdictionInfoService.findAllBySearchText(pageNumber, pageSize, sysRolesJurisdictionInfo);
}


@RequestMapping(path = "/sysRolesJurisdictionList", method = RequestMethod.GET)
public List<SysRolesJurisdictionInfo> findAll(){
    logger.info("sysRolesJurisdictionList", SysRolesJurisdictionController.class);
    return sysRolesJurisdictionInfoService.findAll();
}


}