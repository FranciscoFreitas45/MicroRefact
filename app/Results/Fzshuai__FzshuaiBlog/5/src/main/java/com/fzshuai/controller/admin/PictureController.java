package com.fzshuai.controller.admin;
 import com.fzshuai.po.Picture;
import com.fzshuai.service.PictureService;
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
public class PictureController {

@Autowired
 private  PictureService pictureService;


@GetMapping("/pictures/input")
public String input(Model model){
    model.addAttribute("picture", new Picture());
    return "admin/pictures-input";
}


@PostMapping("/pictures/{id}")
public String editPost(Picture picture,BindingResult result,Long id,RedirectAttributes attributes){
    Picture P = pictureService.updatePicture(id, picture);
    if (P == null) {
        attributes.addFlashAttribute("message", "编辑失败");
    } else {
        attributes.addFlashAttribute("message", "编辑成功");
    }
    return "redirect:/admin/pictures";
}


@PostMapping("/pictures")
public String post(Picture picture,BindingResult result,RedirectAttributes attributes){
    if (result.hasErrors()) {
        return "admin/pictures-input";
    }
    Picture P = pictureService.savePicture(picture);
    if (P == null) {
        attributes.addFlashAttribute("message", "新增失败");
    } else {
        attributes.addFlashAttribute("message", "新增成功");
    }
    return "redirect:/admin/pictures";
}


@GetMapping("/pictures/{id}/delete")
public String delete(Long id,RedirectAttributes attributes){
    pictureService.deletePicture(id);
    attributes.addFlashAttribute("message", "删除成功");
    return "redirect:/admin/pictures";
}


@GetMapping("/pictures")
public String pictures(Pageable pageable,Model model){
    model.addAttribute("page", pictureService.listPicture(pageable));
    return "admin/pictures";
}


@GetMapping("/pictures/{id}/input")
public String editInput(Long id,Model model){
    model.addAttribute("picture", pictureService.getPicture(id));
    return "admin/pictures-input";
}


}