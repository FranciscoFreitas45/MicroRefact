package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgUploadEntityController {

 private CgUploadEntity cguploadentity;

 private CgUploadEntity cguploadentity;


@PutMapping
("/setCgformId")
public void setCgformId(@RequestParam(name = "cgformId") String cgformId){
cguploadentity.setCgformId(cgformId);
}


@PutMapping
("/setCgformName")
public void setCgformName(@RequestParam(name = "cgformName") String cgformName){
cguploadentity.setCgformName(cgformName);
}


@PutMapping
("/setCgformField")
public void setCgformField(@RequestParam(name = "cgformField") String cgformField){
cguploadentity.setCgformField(cgformField);
}


}