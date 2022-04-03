package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SheetModelController {

 private SheetModel sheetmodel;


@GetMapping
("/content")
public String content(@RequestParam(name = "sheet") Sheet sheet,@RequestParam(name = "token") String token,@RequestParam(name = "model") Model model){
  return sheetmodel.content(sheet,token,model);
}


}