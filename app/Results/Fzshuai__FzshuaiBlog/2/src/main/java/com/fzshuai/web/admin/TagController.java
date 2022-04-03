package com.fzshuai.web.admin;
 import com.fzshuai.po.Tag;
import com.fzshuai.service.TagService;
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
public class TagController {

@Autowired
 private  TagService tagService;


@GetMapping("/tags/input")
public String input(Model model){
    model.addAttribute("tag", new Tag());
    return "admin/tags-input";
}


@PostMapping("/tags/{id}")
public String editPost(Tag tag,BindingResult result,Long id,RedirectAttributes attributes){
    Tag t = tagService.getTagByName(tag.getName());
    if (t != null) {
        result.rejectValue("name", "nameError", "不能添加重复的分类！");
    }
    if (result.hasErrors()) {
        return "admin/tags-input";
    }
    Tag t2 = tagService.updateTag(id, tag);
    if (t2 == null) {
        attributes.addFlashAttribute("message", "更新失败！");
    } else {
        attributes.addFlashAttribute("message", "更新成功！");
    }
    return "redirect:/admin/tags";
}


@PostMapping("/tags")
public String post(Tag tag,BindingResult result,RedirectAttributes attributes){
    Tag t = tagService.getTagByName(tag.getName());
    if (t != null) {
        result.rejectValue("name", "nameError", "不能添加重复的分类！");
    }
    if (result.hasErrors()) {
        return "admin/tags-input";
    }
    Tag t2 = tagService.saveTag(tag);
    if (t2 == null) {
        attributes.addFlashAttribute("message", "新增失败！");
    } else {
        attributes.addFlashAttribute("message", "新增成功！");
    }
    return "redirect:/admin/tags";
}


@GetMapping("/tags/{id}/delete")
public String delete(Long id,RedirectAttributes attributes){
    tagService.deleteTag(id);
    attributes.addFlashAttribute("message", "删除成功！");
    return "redirect:/admin/tags";
}


@GetMapping("/tags/{id}/input")
public String editInput(Long id,Model model){
    model.addAttribute("tag", tagService.getTag(id));
    return "admin/tags-input";
}


@GetMapping("/tags")
public String tags(Pageable pageable,Model model){
    model.addAttribute("page", tagService.listTag(pageable));
    return "admin/tags";
}


}