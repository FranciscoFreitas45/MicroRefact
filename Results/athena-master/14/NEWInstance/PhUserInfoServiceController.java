import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhUserInfoServiceController {

 private PhUserInfoService phuserinfoservice;


@GetMapping
("/findByUnionid")
public PhUserInfo findByUnionid(@RequestParam(name = "unionid") String unionid){
  return phuserinfoservice.findByUnionid(unionid);
}


}