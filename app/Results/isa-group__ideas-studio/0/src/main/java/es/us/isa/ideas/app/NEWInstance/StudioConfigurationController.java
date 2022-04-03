package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StudioConfigurationController {

 private StudioConfiguration studioconfiguration;


@GetMapping
("/getAdvancedMode")
public Boolean getAdvancedMode(){
  return studioconfiguration.getAdvancedMode();
}


}