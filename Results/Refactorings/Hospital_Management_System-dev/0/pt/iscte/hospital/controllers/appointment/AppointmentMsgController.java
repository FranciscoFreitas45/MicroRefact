import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.entities.Patient;
import pt.iscte.hospital.entities.Slot;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.objects.utils.AlertMessageImage;
import pt.iscte.hospital.services.AppointmentService;
import pt.iscte.hospital.services.SlotService;
import pt.iscte.hospital.services.waiting.PatientWaitingAppointmentService;
@Controller
public class AppointmentMsgController {

@Autowired
 private  Common common;

@Autowired
 private  PatientWaitingAppointmentService patientWaitingAppointmentService;

@Autowired
 private  SlotService slotService;

@Autowired
 private  AppointmentService appointmentService;


@GetMapping(value = "/patient/waiting-appointment/{awaitingIdNumber}/{reply}")
public String confirmarConsultaMsg(ModelMap modelMap,Long awaitingIdNumber,String reply){
    if (awaitingIdNumber == null || reply == null) {
        return "/patient/messages";
    }
    modelMap.addAllAttributes(common.sideNavMap());
    modelMap.put("hasButton2", true);
    modelMap.put("button2_text", "Mensagens");
    modelMap.put("button2_url", "/patient/messages");
    PatientWaitingAppointment patientWA = patientWaitingAppointmentService.findByPatientWaitingAppointmentId(awaitingIdNumber);
    boolean hasAlreadyRepliedToOffer = patientWA.isRepliedToOffer();
    if (hasAlreadyRepliedToOffer) {
        modelMap.put("message", "Já tinha respondido a esta mensagem ou não respondeu a tempo.");
        modelMap.put("imageURL", AlertMessageImage.FAILURE.getImageURL());
        return "components/alert-message";
    }
    Slot slot = patientWA.getSlot();
    Patient patient = patientWA.getPatient();
    if (reply.equals("yes")) {
        slot.setAvailable(false);
        patientWA.setSlotAccepted(true);
        modelMap.put("message", "Consulta Confirmada.");
    } else {
        slot.setAvailable(true);
        patientWA.setSlotAccepted(false);
        modelMap.put("message", "Consulta Não Confirmada.");
    }
    patientWA.setRepliedToOffer(true);
    patientWaitingAppointmentService.save(patientWA);
    slotService.saveSlot(slot);
    Appointment appointment = new Appointment(patient, slot);
    appointmentService.saveAppointment(appointment);
    modelMap.put("imageURL", AlertMessageImage.SUCCESS.getImageURL());
    return "components/alert-message";
}


}