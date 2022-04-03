package org.jugbd.mnet.web.controller;
 import org.jugbd.mnet.domain.OutdoorRegister;
import org.jugbd.mnet.domain.Patient;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.Vital;
import org.jugbd.mnet.domain.enums.Gender;
import org.jugbd.mnet.domain.enums.Relationship;
import org.jugbd.mnet.domain.enums.Status;
import org.jugbd.mnet.service.PatientService;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.utils.PageWrapper;
import org.jugbd.mnet.utils.StringUtils;
import org.jugbd.mnet.web.editor.GenderEditor;
import org.jugbd.mnet.web.editor.RelationshipEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.jugbd.mnet.utils.StringUtils.isEmpty;
import org.jugbd.mnet.Interface.RegisterService;
import org.jugbd.mnet.DTO.PageWrapper;
@Controller
@Secured({ "ROLE_ADMIN", "ROLE_USER" })
@RequestMapping("/patient")
public class PatientController {

 private  Logger log;

@Autowired
 private  PatientService patientService;

@Autowired
 private  RegisterService registerService;


@RequestMapping(value = "cancel", method = RequestMethod.GET)
public String cancel(){
    log.debug("cancel()");
    return "redirect:/patient/list";
}


@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
public String edit(Long id,Model uiModel){
    Patient selectedPatient = patientService.findOne(id);
    uiModel.addAttribute("patient", selectedPatient);
    return "patient/edit";
}


@RequestMapping(value = "/display", method = RequestMethod.GET)
public String display(PatientSearchCmd patientSearchCmd,Pageable pageable,Model uiModel,HttpServletRequest request){
    log.info("display() patientSearchCmd ={}", patientSearchCmd);
    if (isEmpty(patientSearchCmd.getHealthId()) && isEmpty(patientSearchCmd.getPhoneNumber()) && isEmpty(patientSearchCmd.getName()) && isEmpty(patientSearchCmd.getRegisterId()) && isEmpty(patientSearchCmd.getDiagnosis())) {
        uiModel.addAttribute("patientSearchCmd", patientSearchCmd);
        uiModel.addAttribute("error", "You can't leave these fields empty");
        return "patient/search";
    }
    Page patients = patientService.findPatientBySearchCmd(patientSearchCmd, pageable);
    if (patients.getTotalElements() == 0) {
        uiModel.addAttribute("patientSearchCmd", patientSearchCmd);
        uiModel.addAttribute("notFound", "The patient Information you are looking for, doesn't exist!");
        return "patient/search";
    }
    PageWrapper<Patient> page = new PageWrapper<>(patients, "/patient/display" + getQueryString(patientSearchCmd));
    log.info("size: {}, getOffset: {}, pageNumber:{}", page.getContent().size(), pageable.getOffset(), pageable.getPageNumber());
    uiModel.addAttribute("page", page);
    return "patient/index";
}


@RequestMapping(value = "/create", method = RequestMethod.POST)
public String save(Patient patient,BindingResult result,RedirectAttributes redirectAttributes){
    validatePatient(patient, result);
    if (result.hasErrors()) {
        return "patient/create";
    }
    patientService.create(patient);
    redirectAttributes.addFlashAttribute("message", "Patient successfully created");
    return "redirect:/patient/show/" + patient.getId().toString();
}


@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
public String show(Long id,Long registerId,Model uiModel){
    Patient patient = patientService.findOne(id);
    List<Register> allRegisterByPatientId = registerService.findAllRegisterByPatientId(patient.getId());
    List<OutdoorRegister> allOutdoorRegisterByPatientId = registerService.findAllOutdoorRegisterByPatientId(patient.getId());
    boolean hasActiveRegister = allRegisterByPatientId.stream().anyMatch(register -> register.getStatus() == Status.ACTIVE);
    boolean hasActiveOutdoorRegister = allOutdoorRegisterByPatientId.stream().anyMatch(outdoorRegister -> outdoorRegister.getStatus() == Status.ACTIVE);
    if (hasActiveRegister) {
        Optional<Register> first = allRegisterByPatientId.stream().filter(register -> register.getStatus() == Status.ACTIVE).findFirst();
        first.ifPresent(register -> uiModel.addAttribute("activeIndoor", register));
    }
    if (hasActiveOutdoorRegister) {
        Optional<OutdoorRegister> first = allOutdoorRegisterByPatientId.stream().filter(register -> register.getStatus() == Status.ACTIVE).findFirst();
        first.ifPresent(register -> uiModel.addAttribute("activeOutdoor", register));
    }
    uiModel.addAttribute("patient", patient);
    uiModel.addAttribute("registers", allRegisterByPatientId);
    uiModel.addAttribute("outdoorRegisters", allOutdoorRegisterByPatientId);
    uiModel.addAttribute("hasActiveIndoor", hasActiveRegister);
    uiModel.addAttribute("hasActiveOutdoor", hasActiveOutdoorRegister);
    return "patient/show";
}


public String getQueryString(PatientSearchCmd patientSearchCmd){
    String queryString = "";
    if (StringUtils.isNotEmpty(patientSearchCmd.getHealthId())) {
        queryString += "?healthId=" + patientSearchCmd.getHealthId();
    } else {
        queryString += "?healthId=";
    }
    if (StringUtils.isNotEmpty(patientSearchCmd.getPhoneNumber())) {
        queryString += "&phoneNumber=" + patientSearchCmd.getPhoneNumber();
    } else {
        queryString += "&phoneNumber=";
    }
    if (StringUtils.isNotEmpty(patientSearchCmd.getName())) {
        queryString += "&name=" + patientSearchCmd.getName();
    } else {
        queryString += "&name=";
    }
    if (StringUtils.isNotEmpty(patientSearchCmd.getRegisterId())) {
        queryString += "&registerId=" + patientSearchCmd.getRegisterId();
    } else {
        queryString += "&registerId=";
    }
    if (StringUtils.isNotEmpty(patientSearchCmd.getDiagnosis())) {
        queryString += "&diagnosis=" + patientSearchCmd.getDiagnosis();
    } else {
        queryString += "&diagnosis=";
    }
    return queryString;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST)
public String update(Patient patient,BindingResult result,RedirectAttributes redirectAttributes){
    validatePatient(patient, result);
    if (result.hasErrors()) {
        return "patient/edit";
    }
    patientService.update(patient);
    redirectAttributes.addFlashAttribute("message", String.format("Patient successfully updated"));
    return "redirect:/patient/show/" + patient.getId().toString();
}


@RequestMapping(value = { "/", "/index", "/list" }, method = RequestMethod.GET)
public String index(Model uiModel,Pageable pageable){
    Page<Patient> patients = patientService.findAll(pageable);
    PageWrapper<Patient> page = new PageWrapper<>(patients, "/patient/list");
    uiModel.addAttribute("page", page);
    return "patient/index";
}


@InitBinder
public void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(Gender.class, new GenderEditor());
    binder.registerCustomEditor(Relationship.class, new RelationshipEditor());
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
}


public Vital getLastVital(Register activeRegister){
    return activeRegister.getVitals().stream().filter(vital -> vital.getStatus() == Status.ACTIVE).sorted((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate())).findFirst().orElse(null);
}


public void validatePatient(Patient patient,BindingResult result){
    if (patient.getDateOfBirth() == null && patient.getDay() == null && patient.getMonth() == null && patient.getYear() == null)
        result.rejectValue("dateOfBirth", "error.patient.age", "Enter date of birth or an approximate age");
}


@RequestMapping(value = "search", method = RequestMethod.GET)
public String search(Model uiModel){
    log.debug("search()");
    uiModel.addAttribute("patientSearchCmd", new PatientSearchCmd());
    return "patient/search";
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public String create(Patient patient,Model uiModel){
    return "patient/create";
}


@RequestMapping(value = "details/{id}", method = RequestMethod.GET)
public String details(Long id,Model uiModel){
    uiModel.addAttribute("patient", patientService.findOne(id));
    return "patient/details";
}


}