package org.danyuan.application.softm.sysmenu.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.danyuan.application.common.base.BaseController;
import org.danyuan.application.common.base.BaseControllerImpl;
import org.danyuan.application.softm.sysmenu.po.SysMenuInfo;
import org.danyuan.application.softm.sysmenu.service.SysMenuInfoService;
import org.danyuan.application.softm.sysmenu.vo.AuthorityzTreeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/sysMenuInfo")
public class SysMenuController extends BaseControllerImpl<SysMenuInfo>implements BaseController<SysMenuInfo>{

 private  Logger logger;

@Autowired
 private  SysMenuInfoService sysMenuInfoService;


@RequestMapping(path = "/addbatch", method = RequestMethod.POST)
public void addbatch(List<SysMenuInfo> sysMenuInfoList){
    logger.info(sysMenuInfoList.toString());
    for (SysMenuInfo authority : sysMenuInfoList) {
        sysMenuInfoService.save(authority);
    }
}


@RequestMapping(path = "/findzTreeByUser", method = RequestMethod.POST)
public List<AuthorityzTreeVO> findzTreeByUser(String username){
    return sysMenuInfoService.findzTreeByUser("0", username.replace("\"", ""));
}


@RequestMapping(path = "/findzTreeRole", method = RequestMethod.POST)
public List<AuthorityzTreeVO> findzTreeRole(String roleUuid){
    return sysMenuInfoService.findzTreeRole("0", roleUuid.replace("\"", ""));
}


@RequestMapping(path = "/deleteSysMenuInfo", method = RequestMethod.POST)
public Map<String,String> deleteAuthority(SysMenuInfo sysMenuInfo){
    sysMenuInfoService.deleteAuthority(sysMenuInfo);
    Map<String, String> map = new HashMap<>();
    map.put("code", "0");
    return map;
}


@RequestMapping(path = "/findzTree", method = RequestMethod.POST)
public List<AuthorityzTreeVO> findzTree(){
    return sysMenuInfoService.findzTreeByF_ParentId("0");
}


@RequestMapping(path = "/updateSysMenuInfoName", method = RequestMethod.POST)
public AuthorityzTreeVO updateAuthorityName(SysMenuInfo sysMenuInfo){
    return sysMenuInfoService.updateAuthorityName(sysMenuInfo);
}


@RequestMapping(path = "/onDropSysMenuInfo", method = RequestMethod.POST)
public AuthorityzTreeVO onDropAuthority(SysMenuInfo sysMenuInfo){
    return sysMenuInfoService.onDropAuthority(sysMenuInfo);
}


}