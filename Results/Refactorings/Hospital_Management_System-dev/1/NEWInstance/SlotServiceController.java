import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SlotServiceController {

 private SlotService slotservice;


@GetMapping
("/countAllByIsAvailableAndDoctorAndDateBetween")
public Long countAllByIsAvailableAndDoctorAndDateBetween(@RequestParam(name = "isAvailable") boolean isAvailable,@RequestParam(name = "doctor") Doctor doctor,@RequestParam(name = "dateBegin") LocalDate dateBegin,@RequestParam(name = "dateEnd") LocalDate dateEnd){
  return slotservice.countAllByIsAvailableAndDoctorAndDateBetween(isAvailable,doctor,dateBegin,dateEnd);
}


@GetMapping
("/countAllByIsAvailableAndDoctorSpecialityAndDateBetween")
public Long countAllByIsAvailableAndDoctorSpecialityAndDateBetween(@RequestParam(name = "isAvailable") boolean isAvailable,@RequestParam(name = "speciality") Speciality speciality,@RequestParam(name = "dateBegin") LocalDate dateBegin,@RequestParam(name = "dateEnd") LocalDate dateEnd){
  return slotservice.countAllByIsAvailableAndDoctorSpecialityAndDateBetween(isAvailable,speciality,dateBegin,dateEnd);
}


}