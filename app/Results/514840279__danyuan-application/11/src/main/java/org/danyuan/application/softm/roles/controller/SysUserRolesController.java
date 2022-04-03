package org.danyuan.application.softm.roles.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.common.base.ResultUtil;
import org.danyuan.application.softm.roles.po.SysUserRolesInfo;
import org.danyuan.application.softm.roles.service.SysUserRolesInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysUserRoles")
public class SysUserRolesController extends BaseControllerImpl<SysUserRolesInfo>implements BaseController<SysUserRolesInfo>{

 private  Logger logger;

@Autowired
 private  SysUserRolesInfoService sysUserRolesInfoService;


@RequestMapping(path = "/findAllBySearchText", method = RequestMethod.POST)
public Page<SysUserRolesInfo> findAllBySearchText(int pageNumber,int pageSize,SysUserRolesInfo sysUserRolesInfo){
    logger.info("findAllBySearchText", SysUserRolesController.class);
    return sysUserRolesInfoService.findAllBySearchText(pageNumber, pageSize, sysUserRolesInfo);
}


@RequestMapping(path = "/save", method = RequestMethod.POST)
@ResponseBody
public BaseResult<SysUserRolesInfo> save(SysUserRolesInfo info){
    logger.info("save", SysUserRolesController.class);
    try {
        sysUserRolesInfoService.save(info);
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(e.getMessage());
    }
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysUserRolesController.class);
    ModelAndView modelAndView = new ModelAndView("softm/roles/sysuserrolesinfodetail");
    SysUserRolesInfo info = new SysUserRolesInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysUserRolesInfo", sysUserRolesInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping(path = "/sysUserRolesList", method = RequestMethod.GET)
public List<SysUserRolesInfo> findAll(){
    logger.info("sysUserRolesList", SysUserRolesController.class);
    return sysUserRolesInfoService.findAll();
}


}