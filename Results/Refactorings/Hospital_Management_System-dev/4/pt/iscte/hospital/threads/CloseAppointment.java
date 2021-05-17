import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
import pt.iscte.hospital.repositories.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import pt.iscte.hospital.entities.states.AppointmentState;
@Component
public class CloseAppointment {

 private  long MAX_HOURS;

 private  long TOLERANCIA_MINUTES;

 private  AppointmentRepository appointmentRepository;


@Scheduled(fixedRate = 60000)
public void fecharConsultasAntigas(){
    changeAppointmentsState(MARCADA.getStateNr(), NAO_REALIZADA.getStateNr());
    changeAppointmentsState(EM_CURSO.getStateNr(), REALIZADA.getStateNr());
    System.out.println("Limpeza do estado das consultas");
}


public void changeAppointmentsState(int initialState,int finalState){
    LocalDateTime todayDate = LocalDateTime.now();
    LocalDateTime appointmentDateLimit = todayDate.minusHours(MAX_HOURS);
    List<Appointment> appointments = appointmentRepository.findAllByAppointmentStatus(initialState);
    for (Appointment appointment : appointments) {
        LocalDate appDate = appointment.getSlot().getDate();
        LocalTime appTime = appointment.getSlot().getTimeBegin();
        LocalDateTime appointmentDate = LocalDateTime.of(appDate, appTime);
        if (appointmentDate.isBefore(appointmentDateLimit)) {
            appointment.setAppointmentStatus(finalState);
            appointmentRepository.save(appointment);
            System.out.println(appointment);
        }
    }
}


@Scheduled(fixedRate = 60000)
public void marcarAtrasos(){
    LocalDateTime todayDateTime = LocalDateTime.now();
    List<Appointment> appointments = appointmentRepository.findAllByAppointmentStatus(MARCADA.getStateNr());
    for (Appointment appointment : appointments) {
        DoctorWaitingPatient doctorWaitingPatient = appointment.getDoctorWaitingPatient();
        if (doctorWaitingPatient != null) {
            LocalDate firstCallDate = doctorWaitingPatient.getDate();
            LocalTime firstCallTime = doctorWaitingPatient.getTimeFirstCall();
            LocalDateTime timeLimit = LocalDateTime.of(firstCallDate, firstCallTime).plusMinutes(TOLERANCIA_MINUTES);
            if (todayDateTime.isAfter(timeLimit)) {
                appointment.setAppointmentStatus(NAO_REALIZADA.getStateNr());
                appointmentRepository.save(appointment);
                System.out.println("Registado atraso: " + appointment);
            }
        }
    }
}


}