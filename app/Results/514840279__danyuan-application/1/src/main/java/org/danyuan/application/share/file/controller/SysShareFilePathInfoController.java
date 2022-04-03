package org.danyuan.application.share.file.controller;
 import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.common.base.BaseResult;
import org.danyuan.application.common.base.ResultUtil;
import org.danyuan.application.share.file.po.SysShareFilePathInfo;
import org.danyuan.application.share.file.service.SysShareFilePathInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.danyuan.application.Interface.SysShareFilePathInfoService;
@RestController
@RequestMapping("/sysShareFilePathInfo")
public class SysShareFilePathInfoController extends BaseControllerImpl<SysShareFilePathInfo>implements BaseController<SysShareFilePathInfo>{

 private  Logger logger;

@Autowired
 private SysShareFilePathInfoService sysShareFilePathInfoService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysShareFilePathInfoController.class);
    ModelAndView modelAndView = new ModelAndView("share/file/syssharefilepathinfodetail");
    SysShareFilePathInfo info = new SysShareFilePathInfo();
    info.setUuid(uuid);
    modelAndView.addObject("sysShareFilePathInfo", sysShareFilePathInfoService.findOne(info));
    return modelAndView;
}


@RequestMapping("/resave")
public BaseResult<SysShareFilePathInfo> resave(SysShareFilePathInfo info){
    info.setDeleteFlag(0);
    sysShareFilePathInfoService.save(info);
    return ResultUtil.success();
}


}