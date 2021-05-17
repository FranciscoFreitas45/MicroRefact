import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.iscte.hospital.entities;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils.Calendar;
import pt.iscte.hospital.services.MessageService;
import pt.iscte.hospital.services.SlotService;
import pt.iscte.hospital.services.waiting.PatientWaitingAppointmentService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
public class MakeAppointment {

 private  Long WAITING_PERIOD_HOURS;

 private  Long WAITING_PERIOD_FOR_NEXT_DAY_HOURS;

 private  String URL_CONFIRMA_SIM;

 private  String URL_CONFIRMA_NAO;

 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

 private  SlotService slotService;

 private  MessageService messageService;


}