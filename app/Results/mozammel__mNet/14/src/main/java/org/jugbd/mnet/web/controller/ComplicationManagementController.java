package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.domain.ComplicationManagement;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.service.ComplicationManagementService;
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
import org.jugbd.mnet.Interface.RegisterDao;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("complicationmanagement")
public class ComplicationManagementController {

@Autowired
 private  ComplicationManagementService complicationManagementService;

@Autowired
 private  RegisterService registerService;

@Autowired
 private  RegisterDao registerDao;


@RequestMapping(value = "cancel/{registerId}", method = RequestMethod.GET)
public String cancel(Long registerId){
    return "redirect:/register/complicationmanagement/" + registerDao.findOne(registerId).getId();
}


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,Model uiModel){
    ComplicationManagement complicationManagement = complicationManagementService.findOne(id);
    uiModel.addAttribute("complicationManagement", complicationManagement);
    return "complicationmanagement/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(ComplicationManagement complicationManagement,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "complicationmanagement/create";
    }
    ComplicationManagement complicationManagementSaved = complicationManagementService.save(complicationManagement);
    redirectAttributes.addFlashAttribute("message", "Complication Management successfully created");
    return "redirect:/register/complicationmanagement/" + complicationManagementSaved.getRegister().getId();
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,ComplicationManagement complicationManagement){
    Register register = registerService.findOne(registerId);
    complicationManagement.setRegister(register);
    return "complicationmanagement/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(ComplicationManagement complicationManagement,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "complicationmanagement/edit";
    }
    ComplicationManagement complicationManagementSaved = complicationManagementService.save(complicationManagement);
    redirectAttributes.addFlashAttribute("message", "Complication Management successfully updated");
    return "redirect:/register/complicationmanagement/" + complicationManagementSaved.getRegister().getId();
}


@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
public String delete(Long id){
    ComplicationManagement one = complicationManagementService.findOne(id);
    Long registerId = one.getRegister().getId();
    complicationManagementService.delete(one);
    return "redirect:/register/complicationmanagement/" + registerId;
}


}