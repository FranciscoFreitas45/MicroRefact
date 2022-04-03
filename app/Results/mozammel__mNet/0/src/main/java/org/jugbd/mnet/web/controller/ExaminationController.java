package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.Examination;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.ExaminationService;
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
@RequestMapping("examination")
public class ExaminationController {

@Autowired
 private  ExaminationService examinationService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "cancel/{registerId}", method = RequestMethod.GET)
public String cancel(Long registerId,RegistrationType registrationType){
    return "redirect:/register/examination/" + registerService.findOne(registerId).getId() + "?registrationType=" + registrationType;
}


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,RegistrationType registrationType,Model uiModel){
    Examination examination = examinationService.findOne(id);
    uiModel.addAttribute("examination", examination);
    uiModel.addAttribute("registrationType", registrationType);
    return "examination/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(RegistrationType registrationType,Examination examination,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "examination/create";
    }
    Examination examinationFromDb = examinationService.save(examination, registrationType);
    redirectAttributes.addFlashAttribute("message", "Diagnosis successfully created!");
    return getRedirectUrl(registrationType, examinationFromDb);
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,RegistrationType registrationType,Examination examination,Model uiModel){
    uiModel.addAttribute("registrationType", registrationType);
    registerService.findRegisterEither(registerId, registrationType).map(examination::setRegister, examination::setOutdoorRegister);
    return "examination/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(RegistrationType registrationType,Examination examination,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "examination/edit";
    }
    Examination examinationFromDb = examinationService.save(examination, registrationType);
    redirectAttributes.addFlashAttribute("message", "Examination successfully updated");
    return getRedirectUrl(registrationType, examinationFromDb);
}


public String getRedirectUrl(RegistrationType registrationType,Examination examination){
    String redirectUrl = "redirect:/register/examination/";
    String appender = "?registrationType=" + registrationType;
    return (registrationType == RegistrationType.OUTDOOR) ? (String.format("%s%d%s", redirectUrl, examination.getOutdoorRegister().getId(), appender)) : (String.format("%s%d%s", redirectUrl, examination.getRegister().getId(), appender));
}


}