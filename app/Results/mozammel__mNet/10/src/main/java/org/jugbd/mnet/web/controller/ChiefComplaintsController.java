package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.ChiefComplaint;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.ChiefComplaintService;
import org.jugbd.mnet.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.jugbd.mnet.Interface.RegisterService;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("chiefcomplaints")
public class ChiefComplaintsController {

 public  String REDIRECT_REGISTER_CHIEFCOMPLAINTS;

@Autowired
 private  RegisterService registerService;

@Autowired
 private  ChiefComplaintService chiefComplaintService;


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,RegistrationType registrationType,Model uiModel){
    ChiefComplaint chiefComplaint = chiefComplaintService.findOne(id);
    uiModel.addAttribute("chiefComplaint", chiefComplaint);
    uiModel.addAttribute("registrationType", registrationType);
    return "chiefcomplaints/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(RegistrationType registrationType,ChiefComplaint chiefComplaint,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "chiefcomplaints/create";
    }
    ChiefComplaint savedChiefComplaint = chiefComplaintService.save(chiefComplaint, registrationType);
    redirectAttributes.addFlashAttribute("message", "Chief Complaint successfully created");
    return getRedirectUrl(registrationType, savedChiefComplaint);
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,RegistrationType registrationType,ChiefComplaint chiefComplaint,Model uiModel){
    uiModel.addAttribute("registrationType", registrationType);
    registerService.findRegisterEither(registerId, registrationType).map(chiefComplaint::setRegister, chiefComplaint::setOutdoorRegister);
    return "chiefcomplaints/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(RegistrationType registrationType,ChiefComplaint chiefComplaint,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "chiefcomplaints/edit";
    }
    ChiefComplaint savedChiefComplaint = chiefComplaintService.save(chiefComplaint, registrationType);
    redirectAttributes.addFlashAttribute("message", "Chief Complaints successfully updated");
    return getRedirectUrl(registrationType, savedChiefComplaint);
}


@RequestMapping(value = "back/{registerId}", method = RequestMethod.GET)
public String back(Long registerId,RegistrationType registrationType){
    String redirectUrl = REDIRECT_REGISTER_CHIEFCOMPLAINTS;
    String appender = "?registrationType=" + registrationType;
    return redirectUrl + registerId + appender;
}


public String getRedirectUrl(RegistrationType registrationType,ChiefComplaint chiefComplaint){
    String redirectUrl = REDIRECT_REGISTER_CHIEFCOMPLAINTS;
    String appender = "?registrationType=" + registrationType;
    return (registrationType == RegistrationType.OUTDOOR) ? (String.format("%s%d%s", redirectUrl, chiefComplaint.getOutdoorRegister().getId(), appender)) : (String.format("%s%d%s", redirectUrl, chiefComplaint.getRegister().getId(), appender));
}


}