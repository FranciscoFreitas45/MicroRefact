package net.shangtech.weixin.controller;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.custom.entity.CustomService;
import net.shangtech.weixin.custom.entity.CustomServiceGroup;
import net.shangtech.weixin.custom.service.CustomServiceService;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/weixin/application/custom")
public class CustomController extends BaseController{

@Autowired
 private  CustomServiceService service;


@RequestMapping("/index")
public String index(HttpServletRequest request){
    SysUser user = getUser(request);
    List<CustomServiceGroup> list = service.findGroupsByUser(user.getId());
    request.setAttribute("list", list);
    Map<Integer, List<CustomService>> map = new HashMap<Integer, List<CustomService>>();
    for (CustomServiceGroup group : list) {
        map.put(group.getId(), service.findServiceByGroup(group.getId()));
    }
    request.setAttribute("map", map);
    return "weixin/custom/custom-service";
}


}