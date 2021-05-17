import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils;
import pt.iscte.hospital.services;
import pt.iscte.hospital.services.user.DoctorService;
import pt.iscte.hospital.services.user.PatientService;
import pt.iscte.hospital.services.user.UserService;
import pt.iscte.hospital.services.validation.SlotValidationService;
import pt.iscte.hospital.services.validation.SpecialityValidationService;
import pt.iscte.hospital.services.validation.UserValidationService;
import pt.iscte.hospital.services.waiting.PatientWaitingAppointmentService;
import pt.iscte.hospital.threads.MakeAppointment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import pt.iscte.hospital.entities.states.AppointmentState.MARCADA;
import pt.iscte.hospital.objects.utils.Calendar.FORMATTER;
@Controller
public class ReceptionistController {

 private  SpecialityService specialityService;

 private  UserService userService;

 private  NationalityService nationalityService;

 private  AppointmentService appointmentService;

 private  SpecialityValidationService specialityValidationService;

 private  UserValidationService userValidationService;

 private  RegistrationService registrationService;

 private  DoctorService doctorService;

 private  SlotService slotService;

 private  PatientService patientService;

 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

 private  Common common;

 private  MakeAppointment makeAppointment;

 private  MessageService messageService;

 private  SlotValidationService slotValidationService;

 private  Long WAITING_PERIOD_HOURS;

 private  Long WAITING_PERIOD_FOR_NEXT_DAY_HOURS;


@GetMapping(value = "/receptionist/make-extraAppointment/{userIdStr}/{patientWaitingAppointmentId}")
public String showMakeExtraAppointment(ModelMap modelMap,String userIdStr,Long patientWaitingAppointmentId){
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
    modelMap.put("userIdStr", userIdStr);
    modelMap.put("patientWaitingAppointmentId", patientWaitingAppointmentId);
    modelMap.addAllAttributes(common.sideNavMap());
    return "receptionist/make-extraAppointment";
}


@GetMapping(value = "/receptionist/invoice-form")
public String showInvoiceForm(ModelMap modelMap){
    User userLogged = userService.currentUser();
    modelMap.put("user_logged", userLogged);
    return "receptionist/invoice-form";
}


@PostMapping(value = "/receptionist/add-user")
public String addUser(Doctor doctor,Patient patient,Receptionist receptionist,ModelMap mpError,String confirmarPassword2,String account,String specialityName){
    List<Nationality> nationalities = nationalityService.findAll();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    mpError.addAllAttributes(common.sideNavMap());
    mpError.put("nationalities", nationalities);
    mpError.put("specialities", specialities);
    mpError.put("specialityName", specialityName);
    mpError.put("account", account);
    if (account.equals("Médico")) {
        mpError.put("medicalCondition", true);
    } else {
        mpError.put("medicalCondition", false);
    }
    // add doctor account
    if (doctor.getAccount().equals("Médico")) {
        valitationAddAccount(doctor, confirmarPassword2);
        userValidationService.setUser(doctor).validLicenseNumber();
        userValidationService.setUser(doctor).validSpeciality(specialityName);
        if (!userValidationService.isValid()) {
            mpError.addAllAttributes(userValidationService.getErrorModelMap());
            mpError.put("user", doctor);
            return "receptionist/add-user";
        }
        // Add user to database
        registrationService.encryptPassword(doctor);
        Speciality speciality = specialityService.findByName(specialityName);
        doctor.setSpeciality(speciality);
        userService.addUser(doctor);
        // generate slots for doctor
        generateSlotsDoctor(doctor);
    }
    // add patient account
    if (patient.getAccount().equals("Utente")) {
        valitationAddAccount(patient, confirmarPassword2);
        if (!userValidationService.isValid()) {
            mpError.addAllAttributes(userValidationService.getErrorModelMap());
            mpError.put("user", patient);
            return "receptionist/add-user";
        }
        // Add user to database
        registrationService.encryptPassword(patient);
        userService.addUser(patient);
    }
    // add recepcionist account
    if (receptionist.getAccount().equals("Recepcionista")) {
        valitationAddAccount(receptionist, confirmarPassword2);
        if (!userValidationService.isValid()) {
            mpError.addAllAttributes(userValidationService.getErrorModelMap());
            mpError.put("user", receptionist);
            return "receptionist/add-user";
        }
        // Add user to database
        registrationService.encryptPassword(receptionist);
        userService.addUser(receptionist);
    }
    return "redirect:/receptionist/main";
}


@GetMapping(value = "/receptionist/waiting-list")
public String showWaitingList(ModelMap modelMap){
    LocalDate date = LocalDate.now();
    List<Appointment> appointments = appointmentService.findAllBySlotDateAndAppointmentStatus(date, MARCADA.getStateNr());
    appointments.sort(null);
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("appointments", appointments);
    return "receptionist/waiting-list";
}


@GetMapping(value = "/receptionist/main")
public String showReceptionistMain(ModelMap modelMap){
    LocalDate dateToday = LocalDate.now();
    modelMap.addAllAttributes(infoForTopMain(dateToday));
    modelMap.addAllAttributes(common.sideNavMap());
    return "receptionist/main";
}


@GetMapping(value = "/receptionist/add-speciality")
public String addSpecialityPage(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "receptionist/add-speciality";
}


public void generateSlotsDoctor(Doctor doctor){
    int duration = 60;
    List<TimeInterval> timeIntervalList = new ArrayList<>();
    List<DayOfWeek> weekDaysList = new ArrayList<>();
    LocalDate todayDate = LocalDate.now();
    LocalDate nextMonth = todayDate.plusMonths(1);
    int year1 = todayDate.getYear();
    int month1 = todayDate.getMonth().getValue();
    int year2 = nextMonth.getYear();
    int month2 = nextMonth.getMonth().getValue();
    List<Doctor> doctors = new ArrayList<>();
    doctors.add(doctor);
    timeIntervalList.add(new TimeInterval(LocalTime.of(9, 0), LocalTime.of(12, 0)));
    timeIntervalList.add(new TimeInterval(LocalTime.of(13, 0), LocalTime.of(17, 0)));
    weekDaysList.add(DayOfWeek.MONDAY);
    weekDaysList.add(DayOfWeek.TUESDAY);
    weekDaysList.add(DayOfWeek.WEDNESDAY);
    weekDaysList.add(DayOfWeek.THURSDAY);
    weekDaysList.add(DayOfWeek.FRIDAY);
    weekDaysList.add(DayOfWeek.SATURDAY);
    weekDaysList.add(DayOfWeek.SUNDAY);
    slotService.generateSlots(duration, timeIntervalList, weekDaysList, year1, month1, doctors);
    slotService.generateSlots(duration, timeIntervalList, weekDaysList, year2, month2, doctors);
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


@PostMapping(value = "/receptionist/imprimir")
public String doImprimir(Doctor user,Patient patient,Receptionist receptionist,String account,String specialityName,ModelMap modelMap){
    List<Nationality> nationalities = nationalityService.findAll();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("nationalities", nationalities);
    modelMap.put("specialities", specialities);
    modelMap.put("specialityName", specialityName);
    modelMap.put("account", account);
    if (account.equals("Médico")) {
        modelMap.put("medicalCondition", true);
    } else {
        modelMap.put("medicalCondition", false);
    }
    modelMap.addAllAttributes(common.sideNavMap());
    if (user.getAccount().equals("Médico")) {
        modelMap.put("user", user);
    } else if (patient.getAccount().equals("Utente")) {
        modelMap.put("user", patient);
    } else if (receptionist.getAccount().equals("Recepcionista")) {
        modelMap.put("user", receptionist);
    }
    return "receptionist/add-user";
}


public LocalDateTime replyTime(Slot slot){
    LocalDate slotDate = slot.getDate();
    LocalTime slotTime = slot.getTimeBegin();
    LocalDateTime slotDateTime = LocalDateTime.of(slotDate, slotTime);
    LocalDateTime todayDateTime = LocalDateTime.now();
    LocalDateTime result = todayDateTime;
    if (slotDateTime.isBefore(todayDateTime.plusHours(24L))) {
        result = todayDateTime.plusHours(WAITING_PERIOD_FOR_NEXT_DAY_HOURS);
    } else {
        result = todayDateTime.plusHours(WAITING_PERIOD_HOURS);
    }
    return result;
}


@GetMapping(value = "/receptionist/add-user")
public String addUserPage(ModelMap modelMap){
    List<Nationality> nationalities = nationalityService.findAll();
    List<Speciality> specialities = specialityService.findAll(Sort.by(Sort.Direction.ASC, "name"));
    modelMap.put("nationalities", nationalities);
    modelMap.put("specialities", specialities);
    modelMap.addAllAttributes(common.sideNavMap());
    return "receptionist/add-user";
}


public User currentUser(){
    return userService.currentUser();
}


public ModelMap infoForTopMain(LocalDate dateToday){
    ModelMap modelMap = new ModelMap();
    long pacientes_confirmadosEspera = appointmentService.countBySlotDateAndAppointmentStatusAndHasChecked(dateToday, AppointmentState.MARCADA.getStateNr(), true);
    long pacientes_faltaram = appointmentService.countBySlotDateAndAppointmentStatus(dateToday, AppointmentState.NAO_REALIZADA.getStateNr());
    modelMap.put("pacientes_confirmadosEspera", pacientes_confirmadosEspera);
    modelMap.put("pacientes_faltaram", pacientes_faltaram);
    return modelMap;
}


public void valitationAddAccount(User user,String confirmarPassword2){
    userValidationService.clear().setUser(user).validName().validPassword().samePassword(confirmarPassword2).validPhone().validPostCode().validSex().validEmail().validEmail2().validUsername().validDocumentType().validDocumentNumber().validDocumentNumberUnique().validPatientNumber().validPatientNumberUnique().validNif().validNifUnique().validCity().validAccount().validBirthday().validNationality().validAddress();
    // add default image.
    if (user.getSex().equals("Masculino")) {
        user.setPhotoURL("user-male.jpg");
    } else {
        user.setPhotoURL("user-female.jpg");
    }
}


@PostMapping(value = "/receptionist/add-speciality")
public String addSpecialityService(String name_speciality,double price,ModelMap mpError){
    Speciality speciality = new Speciality(name_speciality, price);
    mpError.addAllAttributes(common.sideNavMap());
    // Check if speciality is valid
    specialityValidationService.clear().setSpeciality(speciality).validName().validLength().validPrice();
    if (!specialityValidationService.isValid()) {
        mpError.addAllAttributes(specialityValidationService.getErrorModelMap());
        return "receptionist/add-speciality";
    }
    specialityService.addSpeciality(speciality);
    return ("redirect:/receptionist/main");
}


public void saveAppointment(String slotId,String userIdStr,Long patientWaitingAppointmentId){
    // Encontrar slot por id
    Slot slot = slotService.findBySlotId(Long.parseLong(slotId));
    // Marcar slot como marcada/indisponível
    slot.setAvailable(false);
    // Actualizar slot na base de dados
    slotService.saveSlot(slot);
    // Adicionar consulta à base de dados
    Appointment appointment = new Appointment();
    Patient patient = patientService.findByUserId(Long.parseLong(userIdStr));
    appointment.setPatient(patient);
    appointment.setSlot(slot);
    appointment.setAppointmentStatus(MARCADA.getStateNr());
    appointmentService.saveAppointment(appointment);
    // remover pedido de marcacao de consulta em lista de espera
    PatientWaitingAppointment patientWaitingAppointment = patientWaitingAppointmentService.findByPatientWaitingAppointmentId(patientWaitingAppointmentId);
    patientWaitingAppointment.setClosed(true);
    patientWaitingAppointment.setLimitDateToReply(replyTime(slot));
    patientWaitingAppointment.setPosition(0L);
    patientWaitingAppointment.setSlot(slot);
    patientWaitingAppointmentService.save(patientWaitingAppointment);
    LocalDateTime dateTime = LocalDateTime.of(slot.getDate(), slot.getTimeBegin());
    Message message = makeAppointment.mensagemConfirmacao(patientWaitingAppointment, dateTime);
    messageService.save(message);
    System.out.println("Sucesso: consulta marcada - " + appointment + slot);
}


@PostMapping(value = { "/receptionist/make-extraAppointment/{userIdStr}/{patientWaitingAppointmentId}/{saveOption}", "/receptionist/make-extraAppointment/{userIdStr}/{patientWaitingAppointmentId}" })
public String makeExtraAppointmentService(ModelMap modelMap,String specialityName,String doctorId,String slotId,String chosenDay,String arrowMonth,String saveOption,String userIdStr,Long patientWaitingAppointmentId,LocalTime timeBegin,LocalTime timeEnd){
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
    boolean hasSlotForDoctorDate = slotService.hasDisponibilidadeNoDia(chosenDate, doctor);
    modelMap.put("hasSlotForDoctor", hasSlotForDoctor);
    modelMap.put("hasSelectDoctor", hasSelectDoctor);
    modelMap.put("hasSlotForDoctorDate", hasSlotForDoctorDate);
    if (saveOption.equals("extra")) {
        // criar slot extra
        Slot extraSlot = new Slot(doctor, chosenDate, timeBegin, timeEnd);
        // validar slot extra
        slotValidationService.clear().setSlot(extraSlot).validSlot();
        if (!slotValidationService.isValid()) {
            modelMap.addAllAttributes(slotValidationService.getErrorModelMap());
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
            modelMap.put("userIdStr", userIdStr);
            modelMap.addAllAttributes(common.sideNavMap());
            return "receptionist/make-extraAppointment";
        }
        // salvar slot extra
        extraSlot.setAvailable(true);
        slotService.saveSlot(extraSlot);
        String newSlotId = extraSlot.getSlotId().toString();
        // marcar consulta para o slot extra criado
        saveAppointment(newSlotId, userIdStr, patientWaitingAppointmentId);
        modelMap.put("message", "A consulta do utente foi marcada com sucesso.");
        modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
        modelMap.addAllAttributes(common.sideNavMap());
        return "components/alert-message";
    }
    // Marcar consulta
    if (slotId != null && !slotId.isEmpty() && saveOption.equals("save")) {
        saveAppointment(slotId, userIdStr, patientWaitingAppointmentId);
        modelMap.put("message", "A consulta do utente foi marcada com sucesso.");
        modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
        modelMap.addAllAttributes(common.sideNavMap());
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
    modelMap.put("userIdStr", userIdStr);
    modelMap.addAllAttributes(common.sideNavMap());
    return ("receptionist/make-extraAppointment");
}


}