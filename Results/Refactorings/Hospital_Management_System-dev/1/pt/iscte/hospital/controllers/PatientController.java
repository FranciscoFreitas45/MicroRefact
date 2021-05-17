import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils.AlertMessageImage;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.objects.utils.Day;
import pt.iscte.hospital.objects.utils.Month;
import pt.iscte.hospital.services;
import pt.iscte.hospital.services.user.DoctorService;
import pt.iscte.hospital.services.user.PatientService;
import pt.iscte.hospital.services.user.UserService;
import pt.iscte.hospital.services.waiting.PatientWaitingAppointmentService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.objects.utils.Calendar.FORMATTER;
@Controller
public class PatientController {

@Autowired
 private  AppointmentService appointmentService;

@Autowired
 private  SpecialityService specialityService;

@Autowired
 private  UserService userService;

@Autowired
 private  PatientService patientService;

@Autowired
 private  DoctorService doctorService;

@Autowired
 private  SlotService slotService;

@Autowired
 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

@Autowired
 private  Common common;


@GetMapping(value = { "/patient/make-appointment", "/patient/reschedule/{appointmentId}" })
public String showMakeAppointment(ModelMap modelMap,Long appointmentId){
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    LocalDate todayDate = LocalDate.now();
    String chosenDay = todayDate.format(FORMATTER);
    int dayOfToday = todayDate.getDayOfMonth();
    int year = todayDate.getYear();
    int monthOfTodayNr = todayDate.getMonth().getValue();
    List<Day> calendar = Calendar.calendarList(year, monthOfTodayNr);
    String strMonth = Month.searchMonth(monthOfTodayNr);
    String nextMonth = todayDate.plusMonths(1).format(FORMATTER);
    String previousMonth = todayDate.minusMonths(1).format(FORMATTER);
    modelMap.put("specialities", specialities);
    modelMap.put("calendarDays", calendar);
    modelMap.put("nextMonth", nextMonth);
    modelMap.put("previousMonth", previousMonth);
    modelMap.put("previousArrowState", 0);
    modelMap.put("nextArrowState", 1);
    modelMap.put("dayOfToday", dayOfToday);
    modelMap.put("year", year);
    modelMap.put("strMonth", strMonth);
    modelMap.put("chosenDay", chosenDay);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("appointmentId", appointmentId);
    if (appointmentId == null) {
        modelMap.put("isToBeReschedule", false);
    } else {
        modelMap.put("isToBeReschedule", true);
    }
    return "patient/make-appointment";
}


@PostMapping(value = "/patient/waitingAppointment/ask")
public String askAppointmentPost(ModelMap modelMap,Long doctorId){
    User userLogged = userService.currentUser();
    LocalDateTime dataToday = LocalDateTime.now();
    Long userId = userLogged.getUserId();
    Patient patient = patientService.findByUserId(userId);
    Doctor doctor = doctorService.findByUserId(doctorId);
    PatientWaitingAppointment patientWaitingAppointment = new PatientWaitingAppointment(dataToday, doctor, patient);
    patientWaitingAppointmentService.save(patientWaitingAppointment);
    modelMap.put("message", "O seu pedido de consulta ficou registado.");
    modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
    modelMap.addAllAttributes(common.sideNavMap());
    return "components/alert-message";
}


public List<Slot> filterPastHour(List<Slot> slotList){
    LocalDateTime dateTimeToday = LocalDateTime.now();
    List<Slot> result = new ArrayList<>();
    for (Slot slot : slotList) {
        LocalDateTime localDateTime = LocalDateTime.of(slot.getDate(), slot.getTimeBegin());
        if (localDateTime.isAfter(dateTimeToday)) {
            result.add(slot);
        }
    }
    return result;
}


public void cancelAppointment(int stateNr,Appointment appointment){
    appointment.setAppointmentStatus(stateNr);
    appointmentService.saveAppointment(appointment);
    Slot slot = appointment.getSlot();
    slot.setAvailable(true);
    slotService.saveSlot(slot);
}


@GetMapping(value = "/patient/waitingAppointment/ask")
public String askAppointment(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "components/alert-message";
}


public void saveAppointment(String slotId){
    // Encontrar slot por id
    Slot slot = slotService.findBySlotId(Long.parseLong(slotId));
    // Marcar slot como marcada/indisponível
    slot.setAvailable(false);
    // Actualizar slot na base de dados
    slotService.saveSlot(slot);
    // Adicionar consulta à base de dados
    Patient patient = patientService.findByUsername(userService.currentUser().getUsername());
    Appointment appointment = new Appointment(patient, slot);
    appointmentService.saveAppointment(appointment);
    System.out.println("Sucesso: consulta marcada - " + appointment + slot);
}


@GetMapping(value = { "/patient/main", "/patient" })
public String showPatientMain(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "patient/main";
}


@PostMapping(value = { "/patient/make-appointment", "/patient/make-appointment/{saveOption}", "/patient/reschedule/{appointmentId}/{saveOption}", "/patient/reschedule/{appointmentId}" })
public String makeAppointmentService(ModelMap modelMap,String specialityName,String doctorId,String slotId,String chosenDay,String arrowMonth,String saveOption,Long appointmentId){
    // **********
    LocalDate todayDate = LocalDate.now();
    LocalDate chosenDate;
    int dayOfToday;
    int calYear;
    int calMonth;
    String strMonth;
    String nextMonthDate;
    String previousMonthDate;
    int previousArrowState;
    int nextArrowState;
    // Foi selecionado um mês
    if (arrowMonth != null) {
        chosenDate = LocalDate.parse(arrowMonth, FORMATTER);
        if (chosenDate.getMonthValue() != todayDate.getMonthValue()) {
            chosenDay = chosenDate.withDayOfMonth(1).format(FORMATTER);
        } else {
            chosenDay = todayDate.format(FORMATTER);
        }
    // Foi selecionado um dia
    } else if (chosenDay != null && arrowMonth == null) {
        chosenDate = LocalDate.parse(chosenDay, FORMATTER);
    // Não existe dia selecionado
    } else {
        chosenDate = todayDate;
        chosenDay = todayDate.format(FORMATTER);
    }
    calYear = chosenDate.getYear();
    calMonth = chosenDate.getMonthValue();
    strMonth = Month.searchMonth(chosenDate.getMonthValue());
    nextMonthDate = chosenDate.plusMonths(1).format(FORMATTER);
    previousMonthDate = chosenDate.minusMonths(1).format(FORMATTER);
    // Lógica das setas + Condição para limitar a selecção de dias de calendário, dias anteriores ao dia actual
    if (chosenDate.getMonthValue() == todayDate.getMonthValue()) {
        previousArrowState = 0;
        nextArrowState = 1;
        dayOfToday = todayDate.getDayOfMonth();
    } else {
        previousArrowState = 1;
        // colocar a 1 se não existir limitação para mostrar meses
        nextArrowState = 0;
        dayOfToday = 0;
    }
    // **********
    // Se campos vazios
    Doctor doctor = null;
    if (doctorId == null || doctorId.isEmpty()) {
        doctorId = "";
    } else {
        doctor = doctorService.findByUserId(Long.parseLong(doctorId));
    }
    if (specialityName == null || specialityName.isEmpty()) {
        specialityName = "";
    }
    if (saveOption == null) {
        saveOption = "";
    }
    // envio de dados para a página
    // alterar a página para receber dados
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    Speciality speciality = specialityService.findByName(specialityName);
    List<Doctor> doctors = doctorService.findAllBySpecialityOrderByNameAsc(speciality);
    List<Slot> slots = slotService.findAllByDoctorAndIsAvailableAndDateOrderByTimeBeginAsc(doctor, true, chosenDate);
    slots = filterPastHour(slots);
    List<Day> calendar = Calendar.calendarList(calYear, calMonth);
    boolean hasSelectDoctor = !doctorId.isEmpty();
    if (!doctorId.isEmpty()) {
        calendar = slotService.calendarColor(calendar, doctor);
    } else if (!specialityName.isEmpty()) {
        calendar = slotService.calendarColor(calendar, specialityName);
    }
    boolean hasSlotForDoctor = slotService.hasDisponibilidadeNoMes(calendar, doctor);
    modelMap.put("hasSlotForDoctor", hasSlotForDoctor);
    modelMap.put("hasSelectDoctor", hasSelectDoctor);
    // Marcar consulta
    if (slotId != null && !slotId.isEmpty() && saveOption.equals("save")) {
        if (appointmentId == null) {
            saveAppointment(slotId);
            modelMap.put("message", "A consulta foi marcada com sucesso.");
            modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
            modelMap.addAllAttributes(common.sideNavMap());
        } else {
            saveAppointment(slotId);
            Appointment appointmentForCancel = appointmentService.findByAppointmentId(appointmentId);
            cancelAppointment(DESMARCADA_PELO_UTENTE.getStateNr(), appointmentForCancel);
            modelMap.put("message", "A consulta foi reagendada com sucesso.");
            modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
            modelMap.addAllAttributes(common.sideNavMap());
        }
        return "components/alert-message";
    }
    modelMap.put("specialities", specialities);
    modelMap.put("doctors", doctors);
    modelMap.put("slots", slots);
    modelMap.put("search_speciality", specialityName);
    modelMap.put("search_doctor", doctorId);
    modelMap.put("search_slot", slotId);
    modelMap.put("calendarDays", calendar);
    modelMap.put("nextMonth", nextMonthDate);
    modelMap.put("previousMonth", previousMonthDate);
    modelMap.put("previousArrowState", previousArrowState);
    modelMap.put("nextArrowState", nextArrowState);
    modelMap.put("dayOfToday", dayOfToday);
    modelMap.put("year", calYear);
    modelMap.put("strMonth", strMonth);
    modelMap.put("chosenDay", chosenDay);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("appointmentId", appointmentId);
    if (appointmentId == null) {
        modelMap.put("isToBeReschedule", false);
    } else {
        modelMap.put("isToBeReschedule", true);
    }
    return ("patient/make-appointment");
}


}