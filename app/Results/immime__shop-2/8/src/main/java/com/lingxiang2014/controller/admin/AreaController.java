package com.lingxiang2014.controller.admin;
 import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.lingxiang2014.Message;
import com.lingxiang2014.entity.Area;
import com.lingxiang2014.service.AreaService;
@Controller("adminAreaController")
@RequestMapping("/admin/area")
public class AreaController extends BaseController{

@Resource(name = "areaServiceImpl")
 private  AreaService areaService;


@RequestMapping(value = "/add", method = RequestMethod.GET)
public String add(Long parentId,ModelMap model){
    model.addAttribute("parent", areaService.find(parentId));
    return "/admin/area/add";
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public String edit(Long id,ModelMap model){
    model.addAttribute("area", areaService.find(id));
    return "/admin/area/edit";
}


@RequestMapping(value = "/save", method = RequestMethod.POST)
public String save(Area area,Long parentId,RedirectAttributes redirectAttributes){
    area.setParent(areaService.find(parentId));
    if (!isValid(area)) {
        return ERROR_VIEW;
    }
    area.setFullName(null);
    area.setTreePath(null);
    area.setChildren(null);
    area.setMembers(null);
    areaService.save(area);
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update(Area area,RedirectAttributes redirectAttributes){
    if (!isValid(area)) {
        return ERROR_VIEW;
    }
    areaService.update(area, "fullName", "treePath", "parent", "children", "members");
    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
    return "redirect:list.jhtml";
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public String list(Long parentId,ModelMap model){
    Area parent = areaService.find(parentId);
    if (parent != null) {
        model.addAttribute("parent", parent);
        model.addAttribute("areas", new ArrayList<Area>(parent.getChildren()));
    } else {
        model.addAttribute("areas", areaService.findRoots());
    }
    return "/admin/area/list";
}


@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public Message delete(Long id){
    areaService.delete(id);
    return SUCCESS_MESSAGE;
}


}