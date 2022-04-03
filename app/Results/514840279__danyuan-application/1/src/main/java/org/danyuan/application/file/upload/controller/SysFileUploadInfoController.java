package org.danyuan.application.file.upload.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.file.upload.po.SysFileUploadInfo;
import org.danyuan.application.file.upload.service.SysFileUploadInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysFileUploadInfoService;
@RestController
@RequestMapping("/sysFileUploadInfo")
public class SysFileUploadInfoController extends BaseControllerImpl<SysFileUploadInfo>implements BaseController<SysFileUploadInfo>{

 private  Logger logger;

@Autowired
 private SysFileUploadInfoService sysFileUploadInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysFileUploadInfoController.class);
    ModelAndView modelAndView = new ModelAndView("file/upload/sysfileuploadinfodetail");
    SysFileUploadInfo info = new SysFileUploadInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysFileUploadInfo", sysFileUploadInfoService.findOne(info));
    return modelAndView;
}


}