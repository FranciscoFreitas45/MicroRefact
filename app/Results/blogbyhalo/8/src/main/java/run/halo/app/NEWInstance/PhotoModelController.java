package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PhotoModelController {

 private PhotoModel photomodel;


@GetMapping
("/list")
public String list(@RequestParam(name = "page") Integer page,@RequestParam(name = "model") Model model){
  return photomodel.list(page,model);
}


}