package org.danyuan.application.softm.roles.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.common.base.ResultUtil;
import org.danyuan.application.softm.roles.po.SysRolesInfo;
import org.danyuan.application.softm.roles.service.SysRolesInfoService;
import org.danyuan.application.softm.roles.vo.SysRolesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysRoles")
public class SysRolesController extends BaseControllerImpl<SysRolesInfo>implements BaseController<SysRolesInfo>{

 private  Logger logger;

@Autowired
 private  SysRolesInfoService sysRolesInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysRolesController.class);
    ModelAndView modelAndView = new ModelAndView("softm/roles/sysrolesinfodetail");
    SysRolesInfo info = new SysRolesInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysRolesInfo", sysRolesInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping(path = "/findAllRoleBySearchText", method = RequestMethod.POST)
public List<SysRolesInfo> findAllRoleBySearchText(SysRolesVo info){
    logger.info("findAllBySearchText", SysRolesController.class);
    return sysRolesInfoService.findAllRoleBySearchText(info.getUserId());
}


@Override
@RequestMapping(path = "/findAll", method = RequestMethod.POST)
public BaseResult<List<SysRolesInfo>> findAll(SysRolesInfo info){
    logger.info("sysRolesList", SysRolesController.class);
    return ResultUtil.success(sysRolesInfoService.findAll());
}


}