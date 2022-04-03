package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.LifeStyle;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.service.LifeStyleService;
import org.jugbd.mnet.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.jugbd.mnet.Interface.RegisterService;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("lifestyle")
public class LifeStyleController {

@Autowired
 private  LifeStyleService lifeStyleService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "cancel/{registerId}", method = RequestMethod.GET)
public String cancel(Long registerId){
    return "redirect:/register/lifestyle/" + registerService.findOne(registerId).getId();
}


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,Model uiModel){
    LifeStyle lifeStyle = lifeStyleService.findOne(id);
    uiModel.addAttribute("lifeStyle", lifeStyle);
    return "lifestyle/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(LifeStyle lifeStyle,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "lifestyle/create";
    }
    LifeStyle lifeStyleSaved = lifeStyleService.save(lifeStyle);
    redirectAttributes.addFlashAttribute("message", "Life Style successfully created");
    return "redirect:/register/lifestyle/" + lifeStyleSaved.getRegister().getId();
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,LifeStyle lifeStyle){
    Register register = registerService.findOne(registerId);
    lifeStyle.setRegister(register);
    return "lifestyle/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(LifeStyle lifeStyle,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "lifestyle/edit";
    }
    LifeStyle savedLifeStyle = lifeStyleService.save(lifeStyle);
    redirectAttributes.addFlashAttribute("message", "Life Style successfully updated");
    return "redirect:/register/lifestyle/" + savedLifeStyle.getRegister().getId();
}


}