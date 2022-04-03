package com.sobey.cmop.mvc.web.basicdata;
 import java.util.Date;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.Location;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.framework.utils.Identities;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/basicdata/vlan")
public class VlanCotroller extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "basicdata/vlan/vlanForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Vlan vlan,Integer locationId,RedirectAttributes redirectAttributes){
    Location location = comm.locationService.getLocation(locationId);
    String alias = "Vlan" + Identities.uuid2();
    vlan.setAlias(alias);
    vlan.setLocation(location);
    vlan.setCreateTime(new Date());
    comm.vlanService.saveOrUpdateVlan(vlan);
    redirectAttributes.addFlashAttribute("message", "创建Vlan成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Vlan vlan,Integer locationId,RedirectAttributes redirectAttributes){
    Location location = comm.locationService.getLocation(locationId);
    vlan.setLocation(location);
    vlan.setCreateTime(new Date());
    comm.vlanService.saveOrUpdateVlan(vlan);
    redirectAttributes.addFlashAttribute("message", "修改Vlan成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("vlan", comm.vlanService.getVlan(id));
    return "basicdata/vlan/vlanForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.vlanService.getVlanPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "basicdata/vlan/vlanList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    boolean flag = comm.vlanService.deleteVlan(id);
    redirectAttributes.addFlashAttribute("message", flag ? "删除Vlan成功" : "Vlan下有关联IP,无法删除.");
    return REDIRECT_SUCCESS_URL;
}


}