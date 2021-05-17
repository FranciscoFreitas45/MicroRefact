import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import pt.iscte.hospital.exceptions.ImageSizeException;
import pt.iscte.hospital.exceptions.ImageTypeException;
import pt.iscte.hospital.objects.utils.AlertMessageImage;
import pt.iscte.hospital.services;
import pt.iscte.hospital.services.invoice.InvoiceService;
import pt.iscte.hospital.services.user.DoctorService;
import pt.iscte.hospital.services.user.PatientService;
import pt.iscte.hospital.services.user.UserService;
import pt.iscte.hospital.services.validation.UserValidationService;
import pt.iscte.hospital.services.waiting.DoctorWaitingPatientService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import pt.iscte.hospital.entities.states.AppointmentState.DESMARCADA_PELO_UTENTE;
@Controller
public class UserController {

 private  AppointmentService appointmentService;

 private  PatientService patientService;

 private  DoctorService doctorService;

 private  SpecialityService specialityService;

 private  UserService userService;

 private  ImageUploadService imageUploadService;

 private  NationalityService nationalityService;

 private  UserValidationService userValidationService;

 private  SlotService slotService;

 private  DoctorWaitingPatientService doctorWaitingPatientService;

 private  InvoiceService invoiceService;

 private  Common common;


@GetMapping(value = "/userToMain")
public String showMainPage(){
    return "redirect:/";
}


@GetMapping(value = "/user/doctor-list")
public String showDoctorList(ModelMap modelMap){
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    List<Doctor> doctors = doctorService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("specialities", specialities);
    modelMap.put("doctors", doctors);
    modelMap.addAllAttributes(common.sideNavMap());
    return "user/doctor-list";
}


public void cancelAppointment(int stateNr,Appointment appointment){
    appointment.setAppointmentStatus(stateNr);
    appointmentService.saveAppointment(appointment);
    Slot slot = appointment.getSlot();
    slot.setAvailable(true);
    slotService.saveSlot(slot);
}


@GetMapping(value = "/user/change-profile-data")
public String showChangeProfileData(ModelMap modelMap){
    List<Nationality> nationalities = nationalityService.findAll();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("nationalities", nationalities);
    modelMap.put("specialities", specialities);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("user", userService.currentUser());
    return "user/change-profile-data";
}


@PostMapping(value = "/user/change-profile-data")
public String changeDataPage(Patient patient,Doctor doctor,Receptionist receptionist,ModelMap modelMap,MultipartFile file){
    // Update user info
    // utente
    User connectedUser = userService.currentUser();
    if (userService.currentUser().getAccount().equals("Utente")) {
        validation(patient, file);
        if (!userValidationService.isValid()) {
            // case error in info validation
            List<Nationality> nationalities = nationalityService.findAll();
            modelMap.addAllAttributes(userValidationService.getErrorModelMap());
            modelMap.put("nationalities", nationalities);
            modelMap.addAllAttributes(common.sideNavMap());
            modelMap.put("user", patient);
            return "user/change-profile-data";
        }
        patient.setUserId(connectedUser.getUserId());
        patient.setEmail(connectedUser.getEmail());
        patient.setUsername(connectedUser.getUsername());
        patient.setPassword(connectedUser.getPassword());
        patient.setAccount(connectedUser.getAccount());
        userService.addUser(patient);
    // médico
    } else if (userService.currentUser().getAccount().equals("Médico")) {
        validation(doctor, file);
        userValidationService.setUser(doctor).validLicenseNumber();
        if (!userValidationService.isValid()) {
            // case error in info validation
            List<Nationality> nationalities = nationalityService.findAll();
            modelMap.addAllAttributes(userValidationService.getErrorModelMap());
            modelMap.put("nationalities", nationalities);
            modelMap.addAllAttributes(common.sideNavMap());
            modelMap.put("user", doctor);
            return "user/change-profile-data";
        }
        doctor.setUserId(connectedUser.getUserId());
        doctor.setEmail(connectedUser.getEmail());
        doctor.setUsername(connectedUser.getUsername());
        doctor.setPassword(connectedUser.getPassword());
        doctor.setAccount(connectedUser.getAccount());
        Speciality speciality = specialityService.findByName(((Doctor) connectedUser).getSpeciality().getName());
        doctor.setSpeciality(speciality);
        userService.addUser(doctor);
    // recepcionista
    } else if (userService.currentUser().getAccount().equals("Recepcionista")) {
        validation(receptionist, file);
        if (!userValidationService.isValid()) {
            // case error in info validation
            List<Nationality> nationalities = nationalityService.findAll();
            modelMap.addAllAttributes(userValidationService.getErrorModelMap());
            modelMap.put("nationalities", nationalities);
            modelMap.addAllAttributes(common.sideNavMap());
            modelMap.put("user", receptionist);
            return "user/change-profile-data";
        }
        receptionist.setUserId(connectedUser.getUserId());
        receptionist.setEmail(connectedUser.getEmail());
        receptionist.setUsername(connectedUser.getUsername());
        receptionist.setPassword(connectedUser.getPassword());
        receptionist.setAccount(connectedUser.getAccount());
        userService.addUser(receptionist);
    }
    return "redirect:/user/user-profile";
}


@GetMapping(value = "/receptionist/appointment-details/resume/{appointmentId}/ask-invoice")
public String showAskInvoice(ModelMap modelMap,Long appointmentId){
    Appointment appointment = appointmentService.findByAppointmentId(appointmentId);
    invoiceService.createInvoice(appointment);
    modelMap.put("message", "Efectuado pedido da Factura.");
    modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
    modelMap.put("hasButton2", true);
    modelMap.put("button2_text", "Regressar ao detalhe da consulta");
    modelMap.put("button2_url", "/receptionist/appointment-details/resume/" + appointmentId);
    modelMap.addAllAttributes(common.sideNavMap());
    return "components/alert-message";
}


@GetMapping(value = "/{userType}/appointment-details/{tempo}/{appointmentId}")
public String showAppointmentDetails(ModelMap modelMap,String userType,String tempo,Long appointmentId){
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    Appointment appointment = appointmentService.findByAppointmentId(appointmentId);
    String appointmentDescription = AppointmentState.searchState(appointment.getAppointmentStatus());
    Patient patient = patientService.findByUserId(appointment.getPatient().getUserId());
    modelMap.put("specialities", specialities);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("patient", patient);
    modelMap.put("appointment", appointment);
    modelMap.put("appointmentDescription", appointmentDescription);
    modelMap.put("userType", userType);
    modelMap.put("tempo", tempo);
    return "user/appointment-details";
}


@PostMapping(value = "/search-doctors")
public String searchDoctors(String name,String speciality,ModelMap modelMap){
    List<Doctor> doctors;
    if (speciality == null || speciality.isEmpty()) {
        speciality = "";
        doctors = doctorService.findAllByFirstAndLastName(name);
    } else {
        doctors = doctorService.findAllByFirstAndLastNameAndSpeciality(name, speciality);
    }
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("search_name", name);
    modelMap.put("search_speciality", speciality);
    modelMap.put("specialities", specialities);
    modelMap.put("doctors", doctors);
    modelMap.addAllAttributes(common.sideNavMap());
    return "user/doctor-list";
}


@GetMapping(value = "/user/user-profile")
public String showUserProfile(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "user/user-profile";
}


@GetMapping(value = "/{userType}/appointment-details/{tempo}/{appointmentId}/cancel")
public String showAppointmentDetailsAfterCancel(ModelMap modelMap,String userType,Long appointmentId){
    Appointment appointment = appointmentService.findByAppointmentId(appointmentId);
    if (userType.equals("patient")) {
        cancelAppointment(DESMARCADA_PELO_UTENTE.getStateNr(), appointment);
    } else if (userType.equals("doctor")) {
        doctorService.desmarcarConsultaByDoctor(appointment);
    }
    modelMap.put("message", "A consulta foi cancelada com sucesso.");
    modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
    modelMap.addAllAttributes(common.sideNavMap());
    return "components/alert-message";
}


@GetMapping(value = "/user/lista-chamada")
public String showListaChamada(ModelMap modelMap){
    // Top 10 e ordenar
    LocalDate todayDate = LocalDate.now();
    List<DoctorWaitingPatient> listaChamada = doctorWaitingPatientService.findAllByDate(todayDate);
    // Últimas 10 chamadas
    int minLength = Math.min(9, Math.max(listaChamada.size() - 1, 0));
    Collections.sort(listaChamada, Collections.reverseOrder());
    listaChamada.subList(0, minLength);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("listaChamada", listaChamada);
    return "user/lista-chamada";
}


public void validation(User user,MultipartFile file){
    User connectedUser = userService.currentUser();
    userValidationService.clear().setUser(user).validName().validPhone().validPostCode().validSex().validDocumentType().validDocumentNumber().validPatientNumber().validNif().validCity().validBirthday().validNationality().validAddress();
    if (!user.getDocumentNumber().equals(connectedUser.getDocumentNumber())) {
        userValidationService.validDocumentNumberUnique();
    }
    if (user.getPatientNumber() != null) {
        if (!user.getPatientNumber().equals(connectedUser.getPatientNumber())) {
            userValidationService.validPatientNumberUnique();
        }
    }
    if (!user.getNif().equals(connectedUser.getNif())) {
        userValidationService.validNifUnique();
    }
    if (file != null && !file.isEmpty() && !file.getContentType().equals("application/octet-stream")) {
        try {
            String photoURL = imageUploadService.uploadImage(file, userService.currentUser().getUsername());
            user.setPhotoURL(photoURL);
        } catch (IOException e) {
            userValidationService.notValidPhotoUpload();
        } catch (ImageTypeException e) {
            userValidationService.notValidImageType();
        } catch (ImageSizeException e) {
            userValidationService.notValidImageSize();
        }
    } else {
        user.setPhotoURL(userService.currentUser().getPhotoURL());
    }
}


}