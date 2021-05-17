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
@Component
public class MakeAppointment {

 private  Long WAITING_PERIOD_HOURS;

 private  Long WAITING_PERIOD_FOR_NEXT_DAY_HOURS;

 private  String URL_CONFIRMA_SIM;

 private  String URL_CONFIRMA_NAO;

@Autowired
 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

@Autowired
 private  SlotService slotService;

@Autowired
 private  MessageService messageService;


@Scheduled(fixedRate = 60000)
public void marcarConsultasEmEspera(){
    System.out.println("Iniciado sistema de marcação de consultas em lista de espera.");
    LocalDateTime todayDateTime = LocalDateTime.now();
    // Verifica se à consultas em espera
    List<PatientWaitingAppointment> listaDeEspera = patientWaitingAppointmentService.findAllByClosedOrderByDate(false);
    // Para cada elemento em espera verifica se à vaga
    for (PatientWaitingAppointment patientWaiting : listaDeEspera) {
        Doctor doctor = patientWaiting.getDoctor();
        // Verifica lista de slot disponíveis
        List<Slot> slots = slotService.findAllByDoctorAndIsAvailableOrderByTimeBeginAsc(doctor, true);
        for (Slot slot : slots) {
            LocalDate slotDate = slot.getDate();
            LocalTime slotTime = slot.getTimeBegin();
            LocalDateTime slotDateTime = LocalDateTime.of(slotDate, slotTime);
            if (slotDateTime.isAfter(todayDateTime.plusHours(WAITING_PERIOD_FOR_NEXT_DAY_HOURS))) {
                slot.setAvailable(false);
                slotService.saveSlot(slot);
                patientWaiting.setClosed(true);
                patientWaiting.setSlot(slot);
                if (slotDateTime.isBefore(todayDateTime.plusHours(24L))) {
                    patientWaiting.setLimitDateToReply(todayDateTime.plusHours(WAITING_PERIOD_FOR_NEXT_DAY_HOURS));
                } else {
                    patientWaiting.setLimitDateToReply(todayDateTime.plusHours(WAITING_PERIOD_HOURS));
                }
                patientWaitingAppointmentService.save(patientWaiting);
                Message message = mensagemConfirmacao(patientWaiting, slotDateTime);
                messageService.save(message);
                System.out.println("Consulta marcada!");
                break;
            }
        }
    }
}


@Scheduled(fixedRate = 60000)
public void verificarRespostas(){
    System.out.println("Verifica se utentes responderam às vagas propostas.");
    List<PatientWaitingAppointment> listaDeEspera = patientWaitingAppointmentService.findAllByClosedAndRepliedToOffer(true, false);
    LocalDateTime todayDateTime = LocalDateTime.now();
    for (PatientWaitingAppointment patientWaitingAppointment : listaDeEspera) {
        if (patientWaitingAppointment.getLimitDateToReply().isBefore(todayDateTime)) {
            Slot slot = patientWaitingAppointment.getSlot();
            slot.setAvailable(true);
            slotService.saveSlot(slot);
            patientWaitingAppointment.setRepliedToOffer(true);
            patientWaitingAppointment.setSlotAccepted(false);
            patientWaitingAppointmentService.save(patientWaitingAppointment);
            System.out.println("Processo de atribuição de vaga fechado.");
        }
    }
}


public Message mensagemConfirmacao(PatientWaitingAppointment patientWaiting,LocalDateTime dateTime){
    Long patientWaiting_id = patientWaiting.getPatientWaitingAppointmentId();
    Doctor doctor = patientWaiting.getDoctor();
    Patient patient = patientWaiting.getPatient();
    String artigo = "";
    if (doctor.getSex().equalsIgnoreCase("masculino")) {
        artigo = "o";
    } else {
        artigo = "a";
    }
    String drName = doctor.getTitleAndName();
    String especialidade = doctor.getSpeciality().getName();
    String data = dateTime.format(Calendar.FORMATTER);
    String horas = dateTime.format(Calendar.TIME_FORMATTER);
    String messageStr = String.format("Por favor confirme se deseja a sua consulta com %s %s, %s, para a data %s às %s horas. " + "Confirma: <a class=\"btn-msg btn-msg-green\" href=\"%s\">Sim</a>  " + "  <a <a class=\"btn-msg btn-msg-blue\"href=\"%s\">Não</a>", artigo, drName, especialidade, data, horas, String.format(URL_CONFIRMA_SIM, patientWaiting_id), String.format(URL_CONFIRMA_NAO, patientWaiting_id));
    return new Message("Marcação de consulta", messageStr, patient);
}


}