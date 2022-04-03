package org.danyuan.application.dbms.tabs.controller;
 import java.sql.SQLException;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.dbms.tabs.po.VSysDbmsTableDis;
import org.danyuan.application.dbms.tabs.service.VSysDbmsTableDisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/vSysDbmsTableDis")
public class VSysDbmsTableDisController extends BaseControllerImpl<VSysDbmsTableDis>implements BaseController<VSysDbmsTableDis>{

@Autowired
 private VSysDbmsTableDisService vSysDbmsTableDisService;


@GetMapping("/detail/{uuid}")
public ModelAndView name(String uuid){
    ModelAndView modelAndView = new ModelAndView("dbms/tabs/vsysdbmstabledisdetail");
    VSysDbmsTableDis info = new VSysDbmsTableDis();
    info.setUuid(uuid);
    modelAndView.addObject("vSysDbmsTableDis", vSysDbmsTableDisService.findOne(info));
    return modelAndView;
}


@RequestMapping("runsql")
public String runsql(VSysDbmsTableDis sVSysDbmsTableDis) throws SQLException{
    return vSysDbmsTableDisService.runsql(sVSysDbmsTableDis);
}


}