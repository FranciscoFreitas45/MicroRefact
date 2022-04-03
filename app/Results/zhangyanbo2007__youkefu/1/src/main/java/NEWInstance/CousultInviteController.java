package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CousultInviteController {

 private CousultInvite cousultinvite;

 private CousultInvite cousultinvite;


@GetMapping
("/isRecordhis")
public boolean isRecordhis(){
  return cousultinvite.isRecordhis();
}


@GetMapping
("/isLeavemsgunlimit")
public boolean isLeavemsgunlimit(){
  return cousultinvite.isLeavemsgunlimit();
}


@GetMapping
("/isConsult_info")
public boolean isConsult_info(){
  return cousultinvite.isConsult_info();
}


@GetMapping
("/isConsult_info_cookies")
public boolean isConsult_info_cookies(){
  return cousultinvite.isConsult_info_cookies();
}


@GetMapping
("/isShowcontacts")
public boolean isShowcontacts(){
  return cousultinvite.isShowcontacts();
}


@GetMapping
("/isAi")
public boolean isAi(){
  return cousultinvite.isAi();
}


@GetMapping
("/isAifirst")
public boolean isAifirst(){
  return cousultinvite.isAifirst();
}


@GetMapping
("/isLoadhismsg")
public boolean isLoadhismsg(){
  return cousultinvite.isLoadhismsg();
}


@GetMapping
("/isSkill")
public boolean isSkill(){
  return cousultinvite.isSkill();
}


}