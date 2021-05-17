import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.states.AppointmentState;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.repositories.AppointmentRepository;
import pt.iscte.hospital.repositories.user.DoctorRepository;
import pt.iscte.hospital.repositories.SpecialityRepository;
import pt.iscte.hospital.repositories.waiting.DoctorWaitingPatientRepository;
import pt.iscte.hospital.repositories.waiting.PatientWaitingAppointmentRepository;
import pt.iscte.hospital.services.MessageService;
import pt.iscte.hospital.services.invoice.InvoiceService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorServiceImpl implements pt.iscte.hospital.services.user.DoctorService,DoctorService{

 private  DoctorRepository doctorRepository;

 private  SpecialityRepository specialityRepository;

 private  AppointmentRepository appointmentRepository;

 private  PatientWaitingAppointmentRepository patientWaitingAppointmentRepository;

 private  DoctorWaitingPatientRepository doctorWaitingPatientRepository;

 private  InvoiceService invoiceService;

 private  MessageService messageService;


@Override
public void desmarcarConsultaByDoctor(Appointment appointment){
    LocalDateTime dataToday = LocalDateTime.now();
    Doctor doctor = appointment.getDoctor();
    Patient patient = appointment.getPatient();
    appointment.setAppointmentStatus(AppointmentState.DESMARCADA_PELO_MEDICO.getStateNr());
    appointmentRepository.save(appointment);
    // Enviar utente para lista de espera de consulta
    PatientWaitingAppointment patientWaitingAppointment = new PatientWaitingAppointment(dataToday, doctor, patient);
    patientWaitingAppointmentRepository.save(patientWaitingAppointment);
    // Enviar mensagem ao utente que a consulta foi cancelada e que este foi colocado em lista de espera
    Message message = mensagemCancelarConsultaListaEspera(appointment);
    messageService.save(message);
}


@Override
public List<Doctor> findAllByFirstAndLastNameAndSpeciality(String name,String specialityName){
    Speciality speciality = specialityRepository.findByName(specialityName);
    List<Doctor> doctors = doctorRepository.findAllByNameContainingIgnoreCaseAndSpeciality(name, speciality);
    return filterByFirstAndLastName(name, doctors);
}


@Override
public void marcarFalta(Appointment appointment){
    appointment.setAppointmentStatus(AppointmentState.NAO_REALIZADA.getStateNr());
    appointmentRepository.save(appointment);
}


@Override
public List<Doctor> findAllByNameContainingIgnoreCase(String name){
    return doctorRepository.findAllByNameContainingIgnoreCase(name);
}


@Override
public void removerFalta(Appointment appointment){
    appointment.setAppointmentStatus(AppointmentState.EM_CURSO.getStateNr());
    appointment.setTimeBegin(LocalTime.now());
    appointmentRepository.save(appointment);
}


@Override
public List<Doctor> findAllBySpecialityOrderByNameAsc(Speciality speciality){
    return doctorRepository.findAllBySpecialityOrderByNameAsc(speciality);
}


@Override
public List<Doctor> findAll(){
    return doctorRepository.findAll();
}


@Override
public List<Doctor> findAllBySpeciality(String specialityName){
    Speciality speciality = specialityRepository.findByName(specialityName);
    return doctorRepository.findAllBySpeciality(speciality);
}


@Override
public void startAppointment(Appointment appointment){
    appointment.setAppointmentStatus(AppointmentState.EM_CURSO.getStateNr());
    appointment.setDate(LocalDate.now());
    appointment.setTimeBegin(LocalTime.now());
    appointmentRepository.save(appointment);
}


@Override
public List<Doctor> findAllByFirstAndLastName(String name){
    List<Doctor> doctors = doctorRepository.findAll();
    return filterByFirstAndLastName(name, doctors);
}


public Message mensagemCancelarConsultaListaEspera(Appointment appointment){
    Doctor doctor = appointment.getDoctor();
    Patient patient = appointment.getPatient();
    Slot slot = appointment.getSlot();
    String artigo = "";
    if (doctor.getSex().equalsIgnoreCase("masculino")) {
        artigo = "o";
    } else {
        artigo = "a";
    }
    String drName = doctor.getTitleAndName() + " " + doctor.getFirstAndLastName();
    String especialidade = doctor.getSpeciality().getName();
    String data = slot.getDate().format(Calendar.FORMATTER);
    String horas = slot.getTimeBegin().format(Calendar.TIME_FORMATTER);
    String messageStr = String.format("A sua consulta com %s %s, %s, para dia data %s às %s horas foi desmarcada. " + "Encontra-se neste momento em lista de espera", artigo, drName, especialidade, data, horas);
    return new Message("Desmarcação de consulta", messageStr, patient);
}


public List<Doctor> filterByFirstAndLastName(String name,List<Doctor> doctors){
    List<Doctor> result = new ArrayList<>();
    for (Doctor doctor : doctors) {
        if (doctor.getFirstAndLastName().toLowerCase().contains(name.toLowerCase())) {
            result.add(doctor);
        }
    }
    return result;
}


@Override
public void chamarUtente(Appointment appointment){
    // Coloca ou actualiza chamada na base de dados
    DoctorWaitingPatient doctorWaitingPatient = appointment.getDoctorWaitingPatient();
    if (doctorWaitingPatient == null) {
        doctorWaitingPatient = new DoctorWaitingPatient(appointment);
        appointment.setDoctorWaitingPatient(doctorWaitingPatient);
    }
    doctorWaitingPatient.setTimeLatestCall(LocalTime.now());
    doctorWaitingPatientRepository.save(doctorWaitingPatient);
}


@Override
public void endAppointment(Appointment appointment){
    appointment.setAppointmentStatus(AppointmentState.REALIZADA.getStateNr());
    appointment.setTimeEnd(LocalTime.now());
    appointmentRepository.save(appointment);
    invoiceService.createInvoice(appointment);
}


@Override
public Doctor findByUserId(Long doctorId){
    return doctorRepository.findByUserId(doctorId);
}


@Override
public List<Doctor> findAllByNameContainingIgnoreCaseAndSpeciality(String name,String specialityName){
    Speciality speciality = specialityRepository.findByName(specialityName);
    return doctorRepository.findAllByNameContainingIgnoreCaseAndSpeciality(name, speciality);
}


}