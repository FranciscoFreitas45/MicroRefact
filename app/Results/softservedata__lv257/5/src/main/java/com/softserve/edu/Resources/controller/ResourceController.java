package com.softserve.edu.Resources.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.softserve.edu.Resources.service.ResourceService;
@Controller
public class ResourceController {

@Autowired
 private ResourceService resourceService;


@RequestMapping(value = "/resource/type/{typeId}/id/{resourceId}", method = RequestMethod.GET)
public String lookUpResult(long typeId,long resourceId,Model model){
    model.addAttribute("genericResource", resourceService.findResourceByTypeAndId(typeId, resourceId));
    return "resourceInfo";
}


}