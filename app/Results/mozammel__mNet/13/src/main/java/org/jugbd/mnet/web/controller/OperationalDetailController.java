package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.OperationalDetail;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.enums.Gender;
import org.jugbd.mnet.domain.enums.Relationship;
import org.jugbd.mnet.service.OperationalDetailService;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.web.editor.GenderEditor;
import org.jugbd.mnet.web.editor.RelationshipEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jugbd.mnet.Interface.RegisterService;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("operationaldetail")
public class OperationalDetailController {

@Autowired
 private  RegisterService registerService;

@Autowired
 private  OperationalDetailService operationalDetailService;


@RequestMapping(value = "cancel/{registerId}", method = RequestMethod.GET)
public String cancel(Long registerId){
    return "redirect:/register/operationaldetail/" + registerService.findOne(registerId).getId();
}


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,Model uiModel){
    OperationalDetail operationalDetail = operationalDetailService.findOne(id);
    uiModel.addAttribute("operationalDetail", operationalDetail);
    return "operationaldetail/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(OperationalDetail operationalDetail,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "operationaldetail/create";
    }
    OperationalDetail operationalDetailSaved = operationalDetailService.save(operationalDetail);
    redirectAttributes.addFlashAttribute("message", "Operational Detail successfully created");
    return "redirect:/operationaldetail/show/" + operationalDetailSaved.getId();
}


@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
public String show(Long id,Model uiModel){
    OperationalDetail operationalDetail = operationalDetailService.findOne(id);
    uiModel.addAttribute("operationalDetail", operationalDetail);
    return "operationaldetail/show";
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,OperationalDetail operationalDetail,Model uiModel){
    Register register = registerService.findOne(registerId);
    operationalDetail.setRegister(register);
    return "operationaldetail/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(OperationalDetail operationalDetail,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "operationaldetail/edit";
    }
    OperationalDetail operationalDetailSaved = operationalDetailService.save(operationalDetail);
    redirectAttributes.addFlashAttribute("message", "Operational Detail successfully updated");
    return "redirect:/operationaldetail/show/" + operationalDetailSaved.getId();
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy hh:mm a"), true));
}


}