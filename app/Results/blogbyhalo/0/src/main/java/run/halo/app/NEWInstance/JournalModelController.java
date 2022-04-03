package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JournalModelController {

 private JournalModel journalmodel;


@GetMapping
("/list")
public String list(@RequestParam(name = "page") Integer page,@RequestParam(name = "model") Model model){
  return journalmodel.list(page,model);
}


}