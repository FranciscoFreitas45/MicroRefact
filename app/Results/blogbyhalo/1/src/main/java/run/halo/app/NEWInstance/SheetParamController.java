package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SheetParamController {

 private SheetParam sheetparam;

 private SheetParam sheetparam;


@PutMapping
("/update")
public void update(@RequestParam(name = "sheet") Sheet sheet){
sheetparam.update(sheet);
}


}