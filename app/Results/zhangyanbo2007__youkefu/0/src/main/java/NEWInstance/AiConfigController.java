package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AiConfigController {

 private AiConfig aiconfig;

 private AiConfig aiconfig;


@PutMapping
("/setAiid")
public void setAiid(@RequestParam(name = "aiid") String aiid){
aiconfig.setAiid(aiid);
}


}