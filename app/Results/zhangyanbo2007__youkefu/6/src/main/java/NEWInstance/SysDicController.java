package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDicController {

 private SysDic sysdic;

 private SysDic sysdic;


@GetMapping
("/isDiscode")
public boolean isDiscode(){
  return sysdic.isDiscode();
}


}