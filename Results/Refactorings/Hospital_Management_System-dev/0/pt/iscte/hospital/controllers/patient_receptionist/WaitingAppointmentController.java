import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities.Speciality;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils.AlertMessageImage;
import pt.iscte.hospital.services.SpecialityService;
import pt.iscte.hospital.services.user.UserService;
import pt.iscte.hospital.services.waiting.PatientWaitingAppointmentService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Controller
public class WaitingAppointmentController {

 private  String PATIENT_TYPE_URL;

 private  String RECEPTIONIST_TYPE_URL;

 private  String USER_TYPE_URL;

@Autowired
 private  SpecialityService specialityService;

@Autowired
 private  UserService userService;

@Autowired
 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

@Autowired
 private  Common common;


@PostMapping(value = "patient/waitingAppointment")
public String showMakeApplyForPatientWaitingAppointment(ModelMap modelMap,String specialityName,String doctorName){
    User userLogged = userService.currentUser();
    Long userId = userLogged.getUserId();
    if (specialityName == null) {
        specialityName = "";
    }
    List<PatientWaitingAppointment> patientWaitingAppointmentListBeforeFilter = patientWaitingAppointmentService.findAllByPatientUserIdAndClosed(userId, false);
    List<PatientWaitingAppointment> patientWaitingAppointments = filterWaitingAppointments(patientWaitingAppointmentListBeforeFilter, null, specialityName, doctorName);
    patientWaitingAppointments.sort(null);
    modelMap.addAllAttributes(appointmentListView(patientWaitingAppointments, PATIENT_TYPE_URL, doctorName, specialityName));
    return USER_TYPE_URL;
}


public List<PatientWaitingAppointment> filterWaitingAppointments(List<PatientWaitingAppointment> waitingAppointments,String patientName,String specialityName,String doctorName){
    List<PatientWaitingAppointment> result = waitingAppointments;
    // Filter by Patient Name
    if (patientName != null && !patientName.isEmpty()) {
        Set<PatientWaitingAppointment> tempList = new HashSet<>();
        String[] patientNamesSearch = patientName.split(" ");
        for (String nameSearch : patientNamesSearch) {
            for (PatientWaitingAppointment patientWaitingAppointment : result) {
                if (patientWaitingAppointment.getPatient().getFirstAndLastName().toLowerCase().contains(nameSearch.toLowerCase())) {
                    tempList.add(patientWaitingAppointment);
                }
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Filter by Speciality
    if (specialityName != null && !specialityName.isEmpty()) {
        Set<PatientWaitingAppointment> tempList = new HashSet<>();
        for (PatientWaitingAppointment patientWaitingAppointment : result) {
            if (patientWaitingAppointment.getDoctor().getSpeciality().getName().equals(specialityName)) {
                tempList.add(patientWaitingAppointment);
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Doctor Name
    if (doctorName != null && !doctorName.isEmpty()) {
        Set<PatientWaitingAppointment> tempList = new HashSet<>();
        String[] doctorNamesSearch = doctorName.split(" ");
        for (String nameSearch : doctorNamesSearch) {
            for (PatientWaitingAppointment patientWaitingAppointment : result) {
                if (patientWaitingAppointment.getDoctor().getFirstAndLastName().toLowerCase().contains(nameSearch.toLowerCase())) {
                    tempList.add(patientWaitingAppointment);
                }
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    return result;
}


@GetMapping(value = "patient/waitingAppointment")
public String showPatientWaitingAppointmentList(ModelMap modelMap){
    User userLogged = userService.currentUser();
    Long userId = userLogged.getUserId();
    List<PatientWaitingAppointment> patientWaitingAppointments = patientWaitingAppointmentService.findAllByPatientUserIdAndClosed(userId, false);
    List<PatientWaitingAppointment> patientWaitingAppointmentsPosition = patientWaitingAppointmentService.findAllByClosedOrderByDate(false);
    Long i = 1L;
    for (PatientWaitingAppointment patientWaitingAppointment : patientWaitingAppointmentsPosition) {
        patientWaitingAppointment.setPosition(i);
        i++;
        patientWaitingAppointmentService.save(patientWaitingAppointment);
    }
    modelMap.addAllAttributes(appointmentListView(patientWaitingAppointments, PATIENT_TYPE_URL, null, null));
    return USER_TYPE_URL;
}


@PostMapping(value = "receptionist/waitingAppointment")
public String showMakeApplyForReceptionistWaitingAppointment(ModelMap modelMap,String specialityName,String patientName,String doctorName){
    User userLogged = userService.currentUser();
    List<PatientWaitingAppointment> patientWaitingAppointmentListBeforeFilter = patientWaitingAppointmentService.findAllByClosed(false);
    List<PatientWaitingAppointment> patientWaitingAppointments = filterWaitingAppointments(patientWaitingAppointmentListBeforeFilter, patientName, specialityName, doctorName);
    patientWaitingAppointments.sort(null);
    modelMap.addAllAttributes(appointmentListView(patientWaitingAppointments, RECEPTIONIST_TYPE_URL, doctorName, specialityName));
    return USER_TYPE_URL;
}


public ModelMap appointmentListView(List<PatientWaitingAppointment> patientWaitingAppointments,String userTypeURL,String doctorName,String specialityName){
    ModelMap modelMap = new ModelMap();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("specialities", specialities);
    modelMap.put("patientWaitingAppointments", patientWaitingAppointments);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("userTypeURL", userTypeURL);
    modelMap.put("doctorName", doctorName);
    modelMap.put("specialityName", specialityName);
    return modelMap;
}


@GetMapping(value = "receptionist/waitingAppointment")
public String showReceptionistWaitingAppointmentList(ModelMap modelMap){
    List<PatientWaitingAppointment> patientWaitingAppointments = patientWaitingAppointmentService.findAllByClosed(false);
    List<PatientWaitingAppointment> patientWaitingAppointmentsPosition = patientWaitingAppointmentService.findAllByClosedOrderByDate(false);
    Long i = 1L;
    for (PatientWaitingAppointment patientWaitingAppointment : patientWaitingAppointmentsPosition) {
        patientWaitingAppointment.setPosition(i);
        i++;
        patientWaitingAppointmentService.save(patientWaitingAppointment);
    }
    modelMap.addAllAttributes(appointmentListView(patientWaitingAppointments, RECEPTIONIST_TYPE_URL, null, null));
    return USER_TYPE_URL;
}


@GetMapping(value = "patient/waitingAppointment/{patientWaitingAppointmentId}/cancel")
public String cancelPatientWaitingAppointmentRequest(ModelMap modelMap,Long patientWaitingAppointmentId){
    User userLogged = userService.currentUser();
    PatientWaitingAppointment patientWaitingAppointment = patientWaitingAppointmentService.findByPatientWaitingAppointmentId(patientWaitingAppointmentId);
    patientWaitingAppointment.setClosed(true);
    patientWaitingAppointment.setPosition(0L);
    patientWaitingAppointmentService.save(patientWaitingAppointment);
    modelMap.put("user_logged", userLogged);
    modelMap.put("message", "O seu pedido de marcação de consulta em lista de espera foi cancelado com sucesso.");
    modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
    return "components/alert-message";
}


}