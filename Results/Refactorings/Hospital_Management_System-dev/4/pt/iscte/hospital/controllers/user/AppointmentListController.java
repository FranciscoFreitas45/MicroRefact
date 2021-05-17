import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.entities.states.InvoiceState;
import pt.iscte.hospital.services;
import pt.iscte.hospital.services.user.PatientService;
import pt.iscte.hospital.services.user.UserService;
import java.time.LocalDate;
import java.util;
import pt.iscte.hospital.entities.states.AppointmentState;
@Controller
public class AppointmentListController {

 private  String PATIENT_CURRENT_TYPE_URL;

 private  String PATIENT_PAST_TYPE_URL;

 private  String DOCTOR_TYPE_URL;

 private  String RECEPTIONIST_TYPE_URL;

 private  String USER_TYPE_URL;

@Autowired
 private  AppointmentService appointmentService;

@Autowired
 private  SpecialityService specialityService;

@Autowired
 private  UserService userService;

@Autowired
 private  PatientService patientService;

@Autowired
 private  Common common;


@PostMapping(value = { "/patient/appointment-list/current" })
public String showAppointmentListPatientCurrentPost(ModelMap modelMap,LocalDate date,String specialityName,String stateAppointment,String stateInvoice,String doctorName){
    User userLogged = userService.currentUser();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    Long userId = userLogged.getUserId();
    if (specialityName == null) {
        specialityName = "";
    }
    Integer appointmentStateNr;
    if (stateAppointment == null) {
        appointmentStateNr = null;
    } else {
        appointmentStateNr = Integer.parseInt(stateAppointment);
    }
    Integer invoiceStateNr;
    if (stateInvoice == null) {
        invoiceStateNr = null;
    } else {
        invoiceStateNr = Integer.parseInt(stateInvoice);
    }
    List<Appointment> appointmentListBeforeFilter = appointmentService.findAllByPatientUserId(userId);
    List<Appointment> appointments = filterAppointments(appointmentListBeforeFilter, date, null, specialityName, null, appointmentStateNr, invoiceStateNr);
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.addAllAttributes(appointmentListView(appointments, PATIENT_CURRENT_TYPE_URL, date, specialityName, doctorName, "patient", "current", null));
    return USER_TYPE_URL;
}


public List<Appointment> filterAppointments(List<Appointment> appointmentList,LocalDate date,String patientName,String specialityName,String doctorName,Integer appointmentStateNr,Integer invoiceStateNr){
    List<Appointment> result = appointmentList;
    // Filter by date
    if (date != null) {
        Set<Appointment> tempList = new HashSet<>();
        for (Appointment appointment : result) {
            if (appointment.getSlot().getDate().equals(date)) {
                tempList.add(appointment);
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Filter by Patient Name
    if (patientName != null && !patientName.isEmpty()) {
        Set<Appointment> tempList = new HashSet<>();
        String[] patientNamesSearch = patientName.split(" ");
        for (String nameSearch : patientNamesSearch) {
            for (Appointment appointment : result) {
                if (appointment.getPatient().getFirstAndLastName().toLowerCase().contains(nameSearch.toLowerCase())) {
                    tempList.add(appointment);
                }
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Filter by Speciality
    if (specialityName != null && !specialityName.isEmpty()) {
        Set<Appointment> tempList = new HashSet<>();
        for (Appointment appointment : result) {
            if (appointment.getSlot().getDoctor().getSpeciality().getName().equals(specialityName)) {
                tempList.add(appointment);
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Doctor Name
    if (doctorName != null && !doctorName.isEmpty()) {
        Set<Appointment> tempList = new HashSet<>();
        String[] doctorNamesSearch = doctorName.split(" ");
        for (String nameSearch : doctorNamesSearch) {
            for (Appointment appointment : result) {
                if (appointment.getSlot().getDoctor().getFirstAndLastName().toLowerCase().contains(nameSearch.toLowerCase())) {
                    tempList.add(appointment);
                }
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    // Estado da consulta
    if (appointmentStateNr != null) {
        List<Integer> appointmentStateNrs = new ArrayList<>();
        if (appointmentStateNr.equals(1001)) {
            appointmentStateNrs = Arrays.asList(DESMARCADA_PELO_UTENTE.getStateNr(), DESMARCADA_PELO_MEDICO.getStateNr(), NAO_REALIZADA.getStateNr());
        } else if (appointmentStateNr.equals(1002)) {
            appointmentStateNrs = Arrays.asList(DESMARCADA_PELO_UTENTE.getStateNr(), DESMARCADA_PELO_MEDICO.getStateNr());
        } else {
            appointmentStateNrs.add(appointmentStateNr);
        }
        result = filterAppointmentsByState(result, appointmentStateNrs);
    }
    // Estado da facturação
    if (invoiceStateNr != null) {
        Set<Appointment> tempList = new HashSet<>();
        for (Appointment appointment : result) {
            if (appointment.getInvoice() != null && appointment.getInvoice().getInvoiceState() == invoiceStateNr) {
                tempList.add(appointment);
            }
        }
        result.clear();
        result.addAll(tempList);
    }
    return result;
}


@PostMapping(value = { "/receptionist/appointment-list/resume" })
public String showAppointmentListReceptionistPost(ModelMap modelMap,LocalDate date,String specialityName,String patientName,String stateAppointment,String stateInvoice,String doctorName){
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    if (specialityName == null) {
        specialityName = "";
    }
    Integer appointmentStateNr;
    if (stateAppointment == null) {
        appointmentStateNr = null;
    } else {
        appointmentStateNr = Integer.parseInt(stateAppointment);
    }
    Integer invoiceStateNr;
    if (stateInvoice == null) {
        invoiceStateNr = null;
    } else {
        invoiceStateNr = Integer.parseInt(stateInvoice);
    }
    List<Appointment> appointmentListBeforeFilter = appointmentService.findAll();
    List<Appointment> appointments = filterAppointments(appointmentListBeforeFilter, date, patientName, specialityName, doctorName, appointmentStateNr, invoiceStateNr);
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.put("stateAppointment", stateAppointment);
    modelMap.addAllAttributes(appointmentListView(appointments, RECEPTIONIST_TYPE_URL, date, specialityName, doctorName, "receptionist", "resume", patientName));
    return USER_TYPE_URL;
}


@GetMapping(value = { "/patient/appointment-list/past" })
public String showAppointmentListPatientPast(ModelMap modelMap){
    User userLogged = userService.currentUser();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    List<Appointment> appointments = new ArrayList<>();
    List<AppointmentState> appointmentStates = Arrays.asList(DESMARCADA_PELO_UTENTE, DESMARCADA_PELO_MEDICO, REALIZADA, NAO_REALIZADA);
    Patient patient = patientService.findByUserId(userLogged.getUserId());
    for (AppointmentState appointmentState : appointmentStates) {
        appointments.addAll(appointmentService.findAllByPatientAndAppointmentStatus(patient, appointmentState.getStateNr()));
    }
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.addAllAttributes(appointmentListView(appointments, PATIENT_PAST_TYPE_URL, null, null, null, "patient", "past", null));
    return USER_TYPE_URL;
}


@GetMapping(value = { "/doctor/appointment-list/resume" })
public String showAppointmentListDoctor(ModelMap modelMap){
    User userLogged = userService.currentUser();
    Long userId = userLogged.getUserId();
    List<Appointment> appointments = new ArrayList<>();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<AppointmentState> appointmentStates = Arrays.asList(MARCADA, EM_CURSO, DESMARCADA_PELO_MEDICO, REALIZADA, NAO_REALIZADA);
    for (AppointmentState appointmentState : appointmentStates) {
        appointments.addAll(appointmentService.findAllBySlotDoctorUserIdAndAppointmentStatus(userId, appointmentState.getStateNr()));
    }
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.addAllAttributes(appointmentListView(appointments, DOCTOR_TYPE_URL, null, null, null, "doctor", "resume", null));
    return USER_TYPE_URL;
}


@GetMapping(value = { "/receptionist/appointment-list/resume" })
public String showAppointmentListReceptionist(ModelMap modelMap){
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    List<Appointment> appointments = new ArrayList<>();
    List<AppointmentState> appointmentStates = Arrays.asList(MARCADA, EM_CURSO, DESMARCADA_PELO_MEDICO, REALIZADA, NAO_REALIZADA);
    for (AppointmentState appointmentState : appointmentStates) {
        appointments.addAll(appointmentService.findAllByAppointmentStatus(appointmentState.getStateNr()));
    }
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.addAllAttributes(appointmentListView(appointments, RECEPTIONIST_TYPE_URL, null, null, null, "receptionist", "resume", null));
    return USER_TYPE_URL;
}


@GetMapping(value = { "/patient/appointment-list/current" })
public String showAppointmentListPatientCurrent(ModelMap modelMap){
    User userLogged = userService.currentUser();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    List<Appointment> appointments = new ArrayList<>();
    List<AppointmentState> appointmentStates = Arrays.asList(MARCADA);
    Patient patient = patientService.findByUserId(userLogged.getUserId());
    for (AppointmentState appointmentState : appointmentStates) {
        appointments.addAll(appointmentService.findAllByPatientAndAppointmentStatus(patient, appointmentState.getStateNr()));
    }
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.addAllAttributes(appointmentListView(appointments, PATIENT_CURRENT_TYPE_URL, null, null, null, "patient", "current", null));
    return USER_TYPE_URL;
}


public ModelMap appointmentListView(List<Appointment> appointments,String userTypeURL,LocalDate date,String specialityName,String doctorName,String userType,String tempo,String patientName){
    ModelMap modelMap = new ModelMap();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("specialities", specialities);
    modelMap.put("appointments", appointments);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("userTypeURL", userTypeURL);
    modelMap.put("date", date);
    modelMap.put("specialityName", specialityName);
    modelMap.put("doctorName", doctorName);
    modelMap.put("userType", userType);
    modelMap.put("tempo", tempo);
    modelMap.put("patientName", patientName);
    return modelMap;
}


@PostMapping(value = { "/patient/appointment-list/past" })
public String showAppointmentListPatientPastPost(ModelMap modelMap,LocalDate date,String specialityName,String stateAppointment,String stateInvoice,String doctorName){
    User userLogged = userService.currentUser();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    List<InvoiceState> invoiceStates = Arrays.asList(InvoiceState.values());
    if (specialityName == null) {
        specialityName = "";
    }
    Long userId = userLogged.getUserId();
    Integer appointmentStateNr;
    if (stateAppointment == null) {
        appointmentStateNr = null;
    } else {
        appointmentStateNr = Integer.parseInt(stateAppointment);
    }
    Integer invoiceStateNr;
    if (stateInvoice == null) {
        invoiceStateNr = null;
    } else {
        invoiceStateNr = Integer.parseInt(stateInvoice);
    }
    List<Appointment> appointmentListBeforeFilter = appointmentService.findAllByPatientUserId(userId);
    List<Appointment> appointments = filterAppointments(appointmentListBeforeFilter, date, null, specialityName, null, appointmentStateNr, invoiceStateNr);
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.put("invoiceStates", invoiceStates);
    modelMap.addAllAttributes(appointmentListView(appointments, PATIENT_PAST_TYPE_URL, date, specialityName, doctorName, "patient", "past", null));
    return USER_TYPE_URL;
}


public List<Appointment> filterAppointmentsByState(List<Appointment> appointmentList,List<Integer> appointmentStateNrs){
    List<Appointment> result = appointmentList;
    List<Appointment> tempList = new ArrayList<>();
    for (Integer appointmentStateNr : appointmentStateNrs) {
        for (Appointment appointment : result) {
            if (appointment.getAppointmentStatus() == appointmentStateNr) {
                tempList.add(appointment);
            }
        }
    }
    result = tempList;
    return result;
}


@PostMapping(value = { "/doctor/appointment-list/resume" })
public String showAppointmentListDoctorPost(ModelMap modelMap,LocalDate date,String specialityName,String stateAppointment,String patientName){
    User userLogged = userService.currentUser();
    List<AppointmentState> appointmentStatesAll = Arrays.asList(AppointmentState.values());
    if (specialityName == null) {
        specialityName = "";
    }
    Long userId = userLogged.getUserId();
    Integer appointmentStateNr;
    if (stateAppointment == null) {
        appointmentStateNr = null;
    } else {
        appointmentStateNr = Integer.parseInt(stateAppointment);
    }
    List<Appointment> appointmentListBeforeFilter = appointmentService.findAllBySlotDoctorUserId(userId);
    List<Appointment> appointments = filterAppointments(appointmentListBeforeFilter, date, patientName, specialityName, null, appointmentStateNr, null);
    appointments.sort(null);
    modelMap.put("appointmentStates", appointmentStatesAll);
    modelMap.addAllAttributes(appointmentListView(appointments, DOCTOR_TYPE_URL, date, specialityName, null, "doctor", "resume", patientName));
    return USER_TYPE_URL;
}


}