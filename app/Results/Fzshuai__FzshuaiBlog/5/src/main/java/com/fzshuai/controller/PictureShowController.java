package com.fzshuai.controller;
 import com.fzshuai.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PictureShowController {

@Autowired
 private  PictureService pictureService;


@GetMapping("/picture")
public String picture(Model model){
    model.addAttribute("pictures", pictureService.listPicture());
    return "picture";
}


}