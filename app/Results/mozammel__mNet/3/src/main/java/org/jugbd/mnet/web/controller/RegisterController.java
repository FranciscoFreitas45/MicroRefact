package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.service.PatientService;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.jugbd.mnet.Interface.PatientService;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("register")
public class RegisterController {

 private  Logger log;

@Autowired
 private  PatientService patientService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "cancel/{patientId}", method = RequestMethod.GET)
public String cancel(Long patientId){
    log.debug("cancel()");
    return "redirect:/patient/show/" + patientId;
}


@RequestMapping(value = "/lifestyle/{registerId}", method = RequestMethod.GET)
public String lifeStyle(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    return "register/life-style";
}


@RequestMapping(value = "/edit-remarks/{registerId}", method = RequestMethod.GET)
public String editRemarks(Long registerId,RegistrationType registrationType,Model uiModel){
    prepareData(registerId, registrationType, uiModel);
    uiModel.addAttribute("edit", true);
    uiModel.addAttribute("registerId", registerId);
    return "register/remarks";
}


@RequestMapping(value = "/edit/{registrationId}", method = RequestMethod.GET)
public String editRegistration(Long registrationId,String type,Model uiModel){
    if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("opd")) {
        OutdoorRegister outdoorRegister = registerService.findOpdRegister(registrationId);
        uiModel.addAttribute("outdoorRegister", outdoorRegister);
        return "register/opd-edit";
    } else {
        Register register = registerService.findOne(registrationId);
        uiModel.addAttribute("register", register);
        return "register/ipd-edit";
    }
}


@RequestMapping(value = "/visits/{registerId}", method = RequestMethod.GET)
public String visitNotes(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("visits", registerService.getVisits(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/visit-note";
}


@RequestMapping(value = "opd/save", method = RequestMethod.POST)
public String saveOpd(OutdoorRegister outdoorRegister,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "register/opd";
    }
    registerService.save(outdoorRegister);
    return "redirect:/register/opd/" + outdoorRegister.getId();
}


@RequestMapping(value = "save", method = RequestMethod.POST)
public String save(Register register,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "register/create";
    }
    registerService.save(register);
    return "redirect:/patient/show/" + register.getPatient().getId();
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
}


@RequestMapping(value = "/chiefcomplaints/{registerId}", method = RequestMethod.GET)
public String chiefcomplaints(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("chiefcomplaints", registerService.findChiefcomplaints(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/chiefcomplaints";
}


@RequestMapping(value = "opd/update", method = RequestMethod.POST)
public String updateOpd(OutdoorRegister outdoorRegister,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "register/opd-edit";
    }
    registerService.save(outdoorRegister);
    return "redirect:/register/opd/" + outdoorRegister.getId();
}


@RequestMapping(value = "/convert-to-ipd/{registerId}", method = RequestMethod.GET)
public String convert(Long registerId,RegistrationType registrationType,Model uiModel){
    OutdoorRegister outdoorRegister = registerService.findOpdRegister(registerId);
    Register register = new Register();
    register.setRegistrationId(outdoorRegister.getRegistrationId());
    register.setPatientContact(outdoorRegister.getPatientContact());
    register.setPatient(outdoorRegister.getPatient());
    uiModel.addAttribute("register", register);
    uiModel.addAttribute("registerId", registerId);
    return "register/convert";
}


@RequestMapping(value = "/edit-remarks/{registerId}", method = RequestMethod.POST)
public String saveRemarks(Long registerId,RegistrationType registrationType,String remarks,Model uiModel){
    registerService.saveRemarks(remarks, registerId, registrationType);
    return "redirect:/register/remarks/" + registerId + "?registrationType=" + registrationType;
}


@RequestMapping(value = "/examination/{registerId}", method = RequestMethod.GET)
public String examination(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("examination", registerService.findExamination(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/examination";
}


@RequestMapping(value = "patient/{patientId}", method = RequestMethod.GET)
public String create(Long patientId,Model uiModel){
    Patient patient = patientService.findOne(patientId);
    Register register = new Register();
    register.setPatient(patient);
    uiModel.addAttribute("register", register);
    return "register/create";
}


@RequestMapping(value = "close/{registerId}", method = RequestMethod.POST)
public String close(Long registerId,RegistrationType registrationType,RedirectAttributes redirectAttributes){
    registerService.closeRegister(registerId, registrationType);
    redirectAttributes.addFlashAttribute("message", "Registration has been closed!");
    Patient patient = registerService.findRegisterEither(registerId, registrationType).fold(Register::getPatient, OutdoorRegister::getPatient);
    return "redirect:/patient/show/" + patient.getId();
}


@RequestMapping(value = "/outcome/{registerId}", method = RequestMethod.GET)
public String outcome(Long registerId,RegistrationType registrationType,Model uiModel){
    prepareData(registerId, registrationType, uiModel);
    return "register/outcome";
}


@RequestMapping(value = "/operationaldetail/{registerId}", method = RequestMethod.GET)
public String operationalDetails(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    Set<OperationalDetail> detailList = registerService.findOperationalDetailList(registerId);
    uiModel.addAttribute("operationaldetails", detailList);
    return "register/operational-detail";
}


@RequestMapping(value = "/edit-outcome/{registerId}", method = RequestMethod.GET)
public String editOutcome(Long registerId,RegistrationType registrationType,Model uiModel){
    prepareData(registerId, registrationType, uiModel);
    uiModel.addAttribute("edit", true);
    uiModel.addAttribute("registerId", registerId);
    return "register/outcome";
}


@RequestMapping(value = "/edit-outcome/{registerId}", method = RequestMethod.POST)
public String saveOutcome(Long registerId,RegistrationType registrationType,String outcome,Model uiModel){
    registerService.saveOutcome(outcome, registerId, registrationType);
    return "redirect:/register/outcome/" + registerId + "?registrationType=" + registrationType;
}


@RequestMapping(value = "/diagnosis/{registerId}", method = RequestMethod.GET)
public String diagnosis(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("diagnosis", registerService.findDiagnosis(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/diagnosis";
}


@RequestMapping(value = "ipd/save", method = RequestMethod.POST)
public String saveInpatient(Register register,BindingResult result,RedirectAttributes redirectAttributes){
    if (result.hasErrors()) {
        return "register/ipd";
    }
    registerService.save(register);
    return "redirect:/register/ipd/" + register.getId();
}


public void prepareData(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("register", registerService.findRegister(registerId, registrationType));
    uiModel.addAttribute("registrationType", registrationType);
    Patient patient = registerService.findRegisterEither(registerId, registrationType).fold(Register::getPatient, OutdoorRegister::getPatient);
    uiModel.addAttribute("patient", patient);
}


@RequestMapping(value = "/convert-to-ipd/{registerId}", method = RequestMethod.POST)
public String completeConversion(Long registerId,Register register,BindingResult result,Model uiModel){
    if (result.hasErrors()) {
        uiModel.addAttribute("registerId", registerId);
        return "register/convert";
    }
    Register savedRegister = registerService.convertOutdoorRegisterToIndoorRegister(registerId, register);
    return "redirect:/patient/show/" + savedRegister.getPatient().getId();
}


@RequestMapping(value = "/picture/{registerId}", method = RequestMethod.GET)
public String pictureInformation(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    return "register/picture";
}


@RequestMapping(value = "opd/{patientId}/new", method = RequestMethod.GET)
public String createOutPatient(Long patientId,Model uiModel){
    Patient patient = patientService.findOne(patientId);
    OutdoorRegister outdoorRegister = new OutdoorRegister();
    outdoorRegister.setPatient(patient);
    uiModel.addAttribute("outdoorRegister", outdoorRegister);
    return "register/opd";
}


@RequestMapping(value = "ipd/{id}", method = RequestMethod.GET)
public String openIpdRegistration(Long id,Model uiModel){
    prepareData(id, RegistrationType.INDOOR, uiModel);
    return "register/ipd-registration";
}


@RequestMapping(value = "opd/{id}", method = RequestMethod.GET)
public String openOpdRegistration(Long id,Model uiModel){
    prepareData(id, RegistrationType.OUTDOOR, uiModel);
    return "register/opd-registration";
}


@RequestMapping(value = "ipd/{patientId}/new", method = RequestMethod.GET)
public String createInpatient(Long patientId,Model uiModel){
    Patient patient = patientService.findOne(patientId);
    Register register = new Register();
    register.setPatient(patient);
    uiModel.addAttribute("register", register);
    return "register/ipd";
}


@RequestMapping(value = "ipd/update", method = RequestMethod.POST)
public String updateRegistration(Register register){
    Register savedRegister = registerService.save(register);
    return "redirect:/register/ipd/" + savedRegister.getId();
}


@RequestMapping(value = "/investigation/{registerId}", method = RequestMethod.GET)
public String investigation(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    Set<Investigation> investigations = registerService.findInvestigations(registerId);
    uiModel.addAttribute("investigations", investigations);
    return "register/investigation";
}


@RequestMapping(value = "/vitals/{registerId}", method = RequestMethod.GET)
public String vital(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("lastVital", registerService.getLastVital(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/vital";
}


@RequestMapping(value = "/medicalhistory/{registerId}", method = RequestMethod.GET)
public String pastMedicalHistory(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    return "register/medical-history";
}


@RequestMapping(value = "/remarks/{registerId}", method = RequestMethod.GET)
public String remarks(Long registerId,RegistrationType registrationType,Model uiModel){
    prepareData(registerId, registrationType, uiModel);
    return "register/remarks";
}


@RequestMapping(value = "/complicationmanagement/{registerId}", method = RequestMethod.GET)
public String complicationmanagement(Long registerId,Model uiModel){
    prepareData(registerId, RegistrationType.INDOOR, uiModel);
    return "register/complicationmanagement";
}


@RequestMapping(value = "/treatmentplan/{registerId}", method = RequestMethod.GET)
public String treatmentPlan(Long registerId,RegistrationType registrationType,Model uiModel){
    uiModel.addAttribute("treatmentPlan", registerService.findTreatmentPlan(registerId, registrationType));
    prepareData(registerId, registrationType, uiModel);
    return "register/treatmentplan";
}


}