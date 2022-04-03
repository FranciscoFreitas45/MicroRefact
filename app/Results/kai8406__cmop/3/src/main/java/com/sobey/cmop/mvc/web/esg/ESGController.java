package com.sobey.cmop.mvc.web.esg;
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
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.framework.utils.Servlets;
@Controller
@RequestMapping(value = "/esg")
public class ESGController extends BaseController{

 private  String REDIRECT_SUCCESS_URL;


@RequestMapping(value = "/save", method = RequestMethod.GET)
public String createForm(Model model){
    return "esg/esgForm";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(String description,String[] protocols,String[] portRanges,String[] visitSources,String[] visitTargets,RedirectAttributes redirectAttributes){
    NetworkEsgItem networkEsgItem = comm.esgService.saveESG(description, protocols, portRanges, visitSources, visitTargets);
    redirectAttributes.addFlashAttribute("message", "创建 " + networkEsgItem.getIdentifier() + " 成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Integer id,String description,String[] protocols,String[] portRanges,String[] visitSources,String[] visitTargets,RedirectAttributes redirectAttributes){
    NetworkEsgItem networkEsgItem = comm.esgService.updateESG(id, description, protocols, portRanges, visitSources, visitTargets);
    redirectAttributes.addFlashAttribute("message", "修改 " + networkEsgItem.getIdentifier() + " 成功.");
    return REDIRECT_SUCCESS_URL;
}


@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
public String updateForm(Integer id,Model model){
    model.addAttribute("esg", comm.esgService.getNetworkEsgItem(id));
    return "esg/esgForm";
}


@RequestMapping(value = { "list", "" })
public String list(int pageNumber,int pageSize,Model model,ServletRequest request){
    Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, REQUEST_PREFIX);
    model.addAttribute("page", comm.esgService.getNetworkEsgItemPageable(searchParams, pageNumber, pageSize));
    return "esg/esgList";
}


@RequestMapping(value = "/delete/{id}")
public String delete(Integer id,RedirectAttributes redirectAttributes){
    comm.esgService.delete(id);
    return REDIRECT_SUCCESS_URL;
}


}