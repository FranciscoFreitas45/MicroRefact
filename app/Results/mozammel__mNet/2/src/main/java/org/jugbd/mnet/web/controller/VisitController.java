package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.Visit;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import org.jugbd.mnet.Interface.RegisterService;
@Controller
@RequestMapping("visit")
public class VisitController {

@Autowired
 private  RegisterService registerService;

@Autowired
 private  VisitService visitService;


@RequestMapping(value = "new/{registerId}", method = RequestMethod.GET)
public String createVisitNote(Visit visit,Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("registrationType", registrationType);
    uiModel.addAttribute("registerId", registerId);
    registerService.findRegisterEither(registerId, registrationType).map(visit::setRegister, visit::setOutdoorRegister);
    return "visit/create";
}


@RequestMapping(value = "new", method = RequestMethod.POST)
public String saveVisitNote(RegistrationType registrationType,Visit visit,BindingResult result,Long registerId){
    if (result.hasErrors()) {
        return "visit/create";
    }
    visitService.save(visit, registerId, registrationType);
    return "redirect:/register/visits/" + registerId + "?registrationType=" + registrationType;
}


@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
public String delete(Long id,RegistrationType registrationType){
    Long registrationId = visitService.delete(id, registrationType);
    return "redirect:/register/visits/" + registrationId + "?registrationType=" + registrationType.name().toLowerCase();
}


}