package org.danyuan.application.softm.dic.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.dic.po.SysDicKeyList;
import org.danyuan.application.softm.dic.po.SysDicName;
import org.danyuan.application.softm.dic.service.SysDicNameService;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/sysDicName")
@Api(value = "/SysDicName", description = "字典名称数据")
public class SysDicNameController extends BaseControllerImpl<SysDicName>implements BaseController<SysDicName>{

 private  Logger logger;

@Autowired
 private  SysDicNameService sysDicNameService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDicNameController.class);
    ModelAndView modelAndView = new ModelAndView("softm/dic/sysdicnamedetail");
    SysDicName info = new SysDicName();
    info.setUuid(uuid);
    modelAndView.addObject("sysDicName", sysDicNameService.findOne(info));
    return modelAndView;
}


@ApiOperation(value = "根据代码查列表值", notes = "")
@RequestMapping(path = "/findkeyList", method = RequestMethod.POST)
public List<SysDicKeyList> findkeyList(SysDicName info){
    logger.info("findkeyList", SysDicNameController.class);
    List<SysDicKeyList> list = sysDicNameService.findkeyList(info);
    return list;
}


@ApiOperation(value = "检查代码重复", notes = "")
@RequestMapping(path = "/checkCode", method = RequestMethod.POST)
public Map<String,Boolean> checkCode(String code){
    logger.info("checkCode", SysDicNameController.class);
    boolean boo = sysDicNameService.checkCode(code);
    Map<String, Boolean> map = new HashMap<>();
    map.put("valid", boo);
    return map;
}


}