package app.qienuren;
 import app.qienuren.controller.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class Event implements ApplicationListener<ApplicationReadyEvent>{

@Autowired
 private MedewerkerService medewerkerService;


@Override
public void onApplicationEvent(ApplicationReadyEvent event){
// genereerLeegFormulierVoorAlleMedewerkers();
}


}