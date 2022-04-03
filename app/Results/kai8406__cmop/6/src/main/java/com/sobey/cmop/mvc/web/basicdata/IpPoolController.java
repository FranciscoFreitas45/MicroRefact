package com.sobey.cmop.mvc.web.basicdata;
 import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sobey.cmop.mvc.comm.BaseController;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/basicdata/ippool")
public class IpPoolController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "basicdata/ippool/ippoolForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String ipAddress,Integer poolType,Integer vlanId,RedirectAttributes redirectAttributes){
    Vlan vlan = comm.vlanService.getVlan(vlanId);
    String message = comm.ipPoolService.saveIpPool(ipAddress, poolType, vlan);
    redirectAttributes.addFlashAttribute("message", message);
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,Integer status,RedirectAttributes redirectAttributes){
    IpPool ipPool = comm.ipPoolService.getIpPool(id);
    ipPool.setStatus(status);
    comm.ipPoolService.saveIpPool(ipPool);
    redirectAttributes.addFlashAttribute("message", "修改IP成功");
    return "redirect:/basicdata/ippool/";
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("ipPool", comm.ipPoolService.getIpPool(id));
    return createForm(model);
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    if (searchParams.size() != 0) {
        // 默认不显示数据.除非有搜索条件
        model.addAttribute("page", comm.ipPoolService.getIpPoolPageable(searchParams, pageNumber, pageSize));
    }
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "basicdata/ippool/ippoolList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    comm.ipPoolService.deleteIpPool(id);
    redirectAttributes.addFlashAttribute("message", "删除IP成功");
    return REDIRECT_SUCCESS_URL;
}


}