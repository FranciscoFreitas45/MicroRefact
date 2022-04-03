package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.DiagnosisService;
import org.jugbd.mnet.service.RegisterService;
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
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/diagnosis")
public class DiagnosisController {

 private  Logger log;

 public  String REDIRECT_REGISTER_DIAGNOSIS;

@Autowired
 private  DiagnosisService diagnosisService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "/edit/{diagnosisId}", method = RequestMethod.GET)
public String editDiagnosis(Long diagnosisId,RegistrationType registrationType,Model uiModel){
    Diagnosis diagnosis = diagnosisService.findOne(diagnosisId);
    uiModel.addAttribute("diagnosis", diagnosis);
    uiModel.addAttribute("registrationType", registrationType);
    return "diagnosis/edit";
}


@RequestMapping(value = "/create", method = RequestMethod.POST)
public String save(RegistrationType registrationType,Diagnosis diagnosis,BindingResult result,RedirectAttributes redirectAttrs){
    if (result.hasErrors()) {
        return "diagnosis/create";
    }
    Diagnosis diagnosisFromDb = diagnosisService.save(diagnosis, registrationType);
    redirectAttrs.addFlashAttribute("message", "Diagnosis successfully created!");
    return getRedirectUrl(registrationType, diagnosisFromDb);
}


@RequestMapping(value = "/create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,RegistrationType registrationType,Diagnosis diagnosis,Model uiModel){
    uiModel.addAttribute("registrationType", registrationType);
    registerService.findRegisterEither(registerId, registrationType).map(diagnosis::setRegister, diagnosis::setOutdoorRegister);
    return "diagnosis/create";
}


@RequestMapping(value = "/edit", method = RequestMethod.POST)
public String update(RegistrationType registrationType,Diagnosis diagnosis,BindingResult result,Model uiModel,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        uiModel.addAttribute("registrationType", registrationType);
        return "diagnosis/create";
    }
    Diagnosis diagnosisFromDb = diagnosisService.save(diagnosis, registrationType);
    redirectAttributes.addFlashAttribute("message", "Diagnosis successfully updated!");
    return getRedirectUrl(registrationType, diagnosisFromDb);
}


@RequestMapping(value = "/back/{registerId}", method = RequestMethod.GET)
public String back(Long registerId,RegistrationType registrationType){
    return REDIRECT_REGISTER_DIAGNOSIS + registerId + "?registrationType=" + registrationType;
}


public String getRedirectUrl(RegistrationType registrationType,Diagnosis diagnosis){
    String appender = "?registrationType=" + registrationType;
    return (registrationType == RegistrationType.OUTDOOR) ? (String.format("%s%d%s", REDIRECT_REGISTER_DIAGNOSIS, diagnosis.getOutdoorRegister().getId(), appender)) : (String.format("%s%d%s", REDIRECT_REGISTER_DIAGNOSIS, diagnosis.getRegister().getId(), appender));
}


}