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
import com.sobey.cmop.mvc.service.onecmdb.OneCmdbService;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/basicdata/serverModel")
public class ServerModelController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@ModelAttribute("companyMap")
public Map<String,String> getCompanyFromOnecmdb(){
    return OneCmdbService.findCiByText("Company", "");
}


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "basicdata/serverModel/serverModelForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String company,String name,Integer cpu,Integer memory,Integer disk,Integer pci,Integer port,RedirectAttributes redirectAttributes){
    comm.serverModelService.saveServerModel(company, name, cpu, memory, disk, pci, port);
    redirectAttributes.addFlashAttribute("message", "创建服务器型号成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String company,String name,Integer cpu,Integer memory,Integer disk,Integer pci,Integer port,RedirectAttributes redirectAttributes){
    comm.serverModelService.updateServerModel(id, company, name, cpu, memory, disk, pci, port);
    redirectAttributes.addFlashAttribute("message", "修改服务器型号成功");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("serverModel", comm.serverModelService.getServerModel(id));
    return "basicdata/serverModel/serverModelForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.serverModelService.getServerModelPageable(searchParams, pageNumber, pageSize));
    // 将搜索条件编码成字符串,分页的URL
    model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, REQUEST_PREFIX));
    return "basicdata/serverModel/serverModelList";
}


@RequestMapping(value = "delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    comm.serverModelService.delete(id);
    redirectAttributes.addFlashAttribute("message", "删除服务器型号成功");
    return REDIRECT_SUCCESS_URL;
}


}