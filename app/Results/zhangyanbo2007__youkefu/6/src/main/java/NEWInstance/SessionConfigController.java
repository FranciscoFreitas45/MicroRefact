package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionConfigController {

 private SessionConfig sessionconfig;

 private SessionConfig sessionconfig;


@GetMapping
("/isEnablersession")
public boolean isEnablersession(){
  return sessionconfig.isEnablersession();
}


@GetMapping
("/isQuality")
public boolean isQuality(){
  return sessionconfig.isQuality();
}


@GetMapping
("/isLastagent")
public boolean isLastagent(){
  return sessionconfig.isLastagent();
}


@GetMapping
("/isHourcheck")
public boolean isHourcheck(){
  return sessionconfig.isHourcheck();
}


@GetMapping
("/isOtherquickplay")
public boolean isOtherquickplay(){
  return sessionconfig.isOtherquickplay();
}


@GetMapping
("/isEnabletransmsg")
public boolean isEnabletransmsg(){
  return sessionconfig.isEnabletransmsg();
}


@GetMapping
("/isAgentreplaytimeout")
public boolean isAgentreplaytimeout(){
  return sessionconfig.isAgentreplaytimeout();
}


@GetMapping
("/isSessiontimeout")
public boolean isSessiontimeout(){
  return sessionconfig.isSessiontimeout();
}


@GetMapping
("/isResessiontimeout")
public boolean isResessiontimeout(){
  return sessionconfig.isResessiontimeout();
}


@GetMapping
("/isQuene")
public boolean isQuene(){
  return sessionconfig.isQuene();
}


@GetMapping
("/isAgentautoleave")
public boolean isAgentautoleave(){
  return sessionconfig.isAgentautoleave();
}


}