package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AppServiceController {

 private AppService appservice;


@PutMapping
("/updateModel")
public void updateModel(@RequestParam(name = "user") User user,@RequestParam(name = "model") Model model,@RequestParam(name = "page") String page,@RequestParam(name = "title") String title){
appservice.updateModel(user,model,page,title);
}


}