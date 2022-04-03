package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CheckinController {

 private Checkin checkin;


@PutMapping
("/setCarPlate")
public void setCarPlate(@RequestParam(name = "carPlate") String carPlate){
checkin.setCarPlate(carPlate);
}


@PutMapping
("/setCompanyId")
public void setCompanyId(@RequestParam(name = "companyId") String companyId){
checkin.setCompanyId(companyId);
}


@PutMapping
("/setUserName")
public void setUserName(@RequestParam(name = "userName") String userName){
checkin.setUserName(userName);
}


@PutMapping
("/setCheckinId")
public void setCheckinId(@RequestParam(name = "checkinId") String checkinId){
checkin.setCheckinId(checkinId);
}


@PutMapping
("/setCheckinStatus")
public void setCheckinStatus(@RequestParam(name = "checkinStatus") String checkinStatus){
checkin.setCheckinStatus(checkinStatus);
}


@PutMapping
("/setUserPhone")
public void setUserPhone(@RequestParam(name = "userPhone") String userPhone){
checkin.setUserPhone(userPhone);
}


}