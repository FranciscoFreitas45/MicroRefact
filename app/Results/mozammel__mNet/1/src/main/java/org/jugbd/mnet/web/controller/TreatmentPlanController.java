package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.TreatmentPlan;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.service.TreatmentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "treatmentplan")
public class TreatmentPlanController {

 private  Logger LOGGER;

 public  String REDIRECT_REGISTER_TREATMENTPLAN;

@Autowired
 private  RegisterService registerService;

@Autowired
 private  TreatmentPlanService treatmentPlanService;


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,RegistrationType registrationType,Model uiModel){
    TreatmentPlan treatmentPlan = treatmentPlanService.findOne(id);
    uiModel.addAttribute("treatmentPlan", treatmentPlan);
    uiModel.addAttribute("registrationType", registrationType);
    return "treatmentplan/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(RegistrationType registrationType,TreatmentPlan treatmentPlan,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "treatmentplan/create";
    }
    TreatmentPlan treatmentPlanSaved = treatmentPlanService.save(treatmentPlan, registrationType);
    redirectAttributes.addFlashAttribute("message", "Diagnosis successfully created!");
    return getRedirectUrl(registrationType, treatmentPlanSaved);
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,RegistrationType registrationType,TreatmentPlan treatmentPlan,Model uiModel){
    uiModel.addAttribute("registrationType", registrationType);
    registerService.findRegisterEither(registerId, registrationType).map(treatmentPlan::setRegister, treatmentPlan::setOutdoorRegister);
    return "treatmentplan/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(RegistrationType registrationType,TreatmentPlan treatmentPlan,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "treatmentplan/edit";
    }
    TreatmentPlan treatmentPlanSaved = treatmentPlanService.save(treatmentPlan, registrationType);
    redirectAttributes.addFlashAttribute("message", "Treatment Plan successfully updated!");
    return getRedirectUrl(registrationType, treatmentPlanSaved);
}


@RequestMapping(value = "back/{registerId}", method = RequestMethod.GET)
public String back(Long registerId,RegistrationType registrationType){
    return REDIRECT_REGISTER_TREATMENTPLAN + registerId + "?registrationType=" + registrationType;
}


public String getRedirectUrl(RegistrationType registrationType,TreatmentPlan treatmentPlan){
    String appender = "?registrationType=" + registrationType;
    return (registrationType == RegistrationType.OUTDOOR) ? (String.format("%s%d%s", REDIRECT_REGISTER_TREATMENTPLAN, treatmentPlan.getOutdoorRegister().getId(), appender)) : (String.format("%s%d%s", REDIRECT_REGISTER_TREATMENTPLAN, treatmentPlan.getRegister().getId(), appender));
}


}