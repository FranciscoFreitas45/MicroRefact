package org.danyuan.application.dbms.echarts.controller;
 import javax.servlet.http.HttpServletRequest;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.echarts.po.SysDbmsChartDimensionGroup;
import org.danyuan.application.dbms.echarts.service.SysDbmsChartDimensionGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/sysDbmsChartDimensionGroup")
public class SysDbmsChartDimensionGroupController extends BaseControllerImpl<SysDbmsChartDimensionGroup>implements BaseController<SysDbmsChartDimensionGroup>{

 private  Logger logger;

@Autowired
 private SysDbmsChartDimensionGroupService sysDbmsChartDimensionGroupService;


@RequestMapping(path = "/addBefor", method = RequestMethod.GET)
public ModelAndView addBefor(HttpServletRequest request){
    logger.info("addBefor", SysDbmsChartDimensionGroupController.class);
    ModelAndView view = new ModelAndView("echarts/add");
    SysDbmsChartDimensionGroup group = new SysDbmsChartDimensionGroup();
    group.setUuid(request.getParameter("uuid"));
    if (group.getUuid() == null || "".equals(group.getUuid())) {
        view.addObject("sysPlantChartDimensionGroup", group);
    } else {
        group = sysDbmsChartDimensionGroupService.findOne(group);
        view.addObject("sysPlantChartDimensionGroup", group);
    }
    return view;
}


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    logger.info("detail", SysDbmsChartDimensionGroupController.class);
    ModelAndView modelAndView = new ModelAndView("dbms/echarts/sysdbmschartdimensiongroupdetail");
    SysDbmsChartDimensionGroup info = new SysDbmsChartDimensionGroup();
    info.setUuid(uuid);
    modelAndView.addObject("sysDbmsChartDimensionGroup", sysDbmsChartDimensionGroupService.findOne(info));
    return modelAndView;
}


}