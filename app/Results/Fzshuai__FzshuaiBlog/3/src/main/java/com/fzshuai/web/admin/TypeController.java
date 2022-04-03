package com.fzshuai.web.admin;
 import com.fzshuai.po.Type;
import com.fzshuai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
@Controller
@RequestMapping("/admin")
public class TypeController {

@Autowired
 private  TypeService typeService;


@GetMapping("/types/input")
public String input(Model model){
    model.addAttribute("type", new Type());
    return "admin/types-input";
}


@PostMapping("/types/{id}")
public String editPost(Type type,BindingResult result,Long id,RedirectAttributes attributes){
    Type t = typeService.getTypeByName(type.getName());
    if (t != null) {
        result.rejectValue("name", "nameError", "不能添加重复的分类！");
    }
    if (result.hasErrors()) {
        return "admin/types-input";
    }
    Type t2 = typeService.updateType(id, type);
    if (t2 == null) {
        attributes.addFlashAttribute("message", "更新失败！");
    } else {
        attributes.addFlashAttribute("message", "更新成功！");
    }
    return "redirect:/admin/types";
}


@GetMapping("/types")
public String types(Pageable pageable,Model model){
    model.addAttribute("page", typeService.listType(pageable));
    return "admin/types";
}


@PostMapping("/types")
public String post(Type type,BindingResult result,RedirectAttributes attributes){
    Type t = typeService.getTypeByName(type.getName());
    if (t != null) {
        result.rejectValue("name", "nameError", "分类名称不能重复！");
    }
    if (result.hasErrors()) {
        return "admin/types-input";
    }
    Type t2 = typeService.saveType(type);
    if (t2 == null) {
        attributes.addFlashAttribute("message", "新增失败！");
    } else {
        attributes.addFlashAttribute("message", "新增成功！");
    }
    return "redirect:/admin/types";
}


@GetMapping("/types/{id}/delete")
public String delete(Long id,RedirectAttributes attributes){
    typeService.deleteType(id);
    attributes.addFlashAttribute("message", "删除成功！");
    return "redirect:/admin/types";
}


@GetMapping("/types/{id}/input")
public String editInput(Long id,Model model){
    model.addAttribute("type", typeService.getType(id));
    return "admin/types-input";
}


}