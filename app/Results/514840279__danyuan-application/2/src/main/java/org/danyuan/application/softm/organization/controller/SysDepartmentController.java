package org.danyuan.application.softm.organization.controller;
 import java.util.List;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.organization.po.SysDepartmentInfo;
import org.danyuan.application.softm.organization.service.SysDepartmentInfoService;
import org.danyuan.application.softm.organization.vo.SysDepartmentInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/sysDepartment")
public class SysDepartmentController extends BaseControllerImpl<SysDepartmentInfo>implements BaseController<SysDepartmentInfo>{

 private  Logger logger;

@Autowired
 private  SysDepartmentInfoService sysDepartmentService;


@RequestMapping(path = "/findAllBySearchText", method = RequestMethod.POST)
public Page<SysDepartmentInfo> findAllBySearchText(SysDepartmentInfoVo sysDepartmentInfoVo){
    logger.info("findAllBySearchText", SysDepartmentController.class);
    SysDepartmentInfo info = new SysDepartmentInfo();
    info.setOrganizationId(sysDepartmentInfoVo.getOrganizationId());
    return sysDepartmentService.findAllBySearchText(sysDepartmentInfoVo.getPageNumber(), sysDepartmentInfoVo.getPageSize(), info);
}


@RequestMapping(path = "/sysDepartmentList", method = RequestMethod.GET)
public List<SysDepartmentInfo> findAll(){
    logger.info("sysSystemList", SysDepartmentController.class);
    return sysDepartmentService.findAll();
}


}