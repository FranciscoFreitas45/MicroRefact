package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessServiceController {

 private ProcessService processservice;


@PutMapping
("/user")
public void user(@RequestParam(name = "page") int page,@RequestParam(name = "size") int size,@RequestParam(name = "model") Model model){
processservice.user(page,size,model);
}


}