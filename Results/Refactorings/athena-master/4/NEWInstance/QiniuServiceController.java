import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QiniuServiceController {

 private QiniuService qiniuservice;


@GetMapping
("/token")
public String token(){
  return qiniuservice.token();
}


}