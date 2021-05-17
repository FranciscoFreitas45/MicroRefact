import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class MakeAppointmentController {

 private MakeAppointment makeappointment;


@GetMapping
("/mensagemConfirmacao")
public Message mensagemConfirmacao(@RequestParam(name = "patientWaiting") PatientWaitingAppointment patientWaiting,@RequestParam(name = "dateTime") LocalDateTime dateTime){
  return makeappointment.mensagemConfirmacao(patientWaiting,dateTime);
}


}