package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.MedicalHistory;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.enums.Gender;
import org.jugbd.mnet.service.MedicalHistoryService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.jugbd.mnet.Interface.RegisterService;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/medicalhistory")
public class MedicalHistoryController {

 private  Logger log;

@Autowired
 private  MedicalHistoryService medicalHistoryService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "cancel/{registerId}", method = RequestMethod.GET)
public String cancel(Long registerId){
    return "redirect:/patient/show/" + registerService.findOne(registerId).getPatient().getId();
}


@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
public String edit(Long id,Model uiModel){
    MedicalHistory medicalHistory = medicalHistoryService.findOne(id);
    uiModel.addAttribute("medicalHistory", medicalHistory);
    Gender gender = medicalHistory.getRegister().getPatient().getGender();
    boolean isFemale = gender == Gender.FEMALE;
    uiModel.addAttribute("isFemale", isFemale);
    return "medicalhistory/edit";
}


@RequestMapping(value = "create", method = RequestMethod.POST)
public String save(MedicalHistory medicalHistory,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "medicalhistory/create";
    }
    MedicalHistory saveMedicalHistory = medicalHistoryService.save(medicalHistory);
    redirectAttributes.addFlashAttribute("message", "Medical History successfully created");
    return "redirect:/register/medicalhistory/" + saveMedicalHistory.getRegister().getId();
}


@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
public String show(Long id,Model uiModel){
    uiModel.addAttribute("medicalHistory", medicalHistoryService.findOne(id));
    return "medicalhistory/show";
}


@RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
public String create(Long registerId,MedicalHistory medicalHistory,Model uiModel){
    Register register = registerService.findOne(registerId);
    medicalHistory.setRegister(register);
    Gender gender = register.getPatient().getGender();
    boolean isFemale = gender == Gender.FEMALE;
    uiModel.addAttribute("isFemale", isFemale);
    return "medicalhistory/create";
}


@RequestMapping(value = "edit", method = RequestMethod.POST)
public String update(MedicalHistory medicalHistory,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "medicalhistory/edit";
    }
    MedicalHistory saveMedicalHistory = medicalHistoryService.save(medicalHistory);
    redirectAttributes.addFlashAttribute("message", "Medical History successfully updated");
    return "redirect:/register/medicalhistory/" + saveMedicalHistory.getRegister().getId();
}


}