package sn.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResponseDataMessageController {

 private ResponseDataMessage responsedatamessage;

 private ResponseDataMessage responsedatamessage;


@GetMapping
("/ok")
public ResponseDataMessage ok(){
  return responsedatamessage.ok();
}


}