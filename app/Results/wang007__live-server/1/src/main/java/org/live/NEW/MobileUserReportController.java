package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.live.entity.MobileUser;
@RestController
@CrossOrigin
public class MobileUserReportController {

@Autowired
 private MobileUserReportService mobileuserreportservice;


@GetMapping
("/Report/{id}/MobileUser/getUser")
public MobileUser getUser(@PathVariable(name="id") String idYUDA){
return mobileuserreportservice.getUser(idYUDA);
}


@PutMapping
("/Report/{id}/MobileUser/setUser")
public void setUser(@PathVariable(name="id") String idYUDA,@RequestBody MobileUser user){
mobileuserreportservice.setUser(idYUDA,user);
}


}