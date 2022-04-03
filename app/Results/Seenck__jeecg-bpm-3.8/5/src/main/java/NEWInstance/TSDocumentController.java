package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TSDocumentController {

 private TSDocument tsdocument;

 private TSDocument tsdocument;


@PutMapping
("/setTSType")
public void setTSType(@RequestParam(name = "tSType") TSType tSType){
tsdocument.setTSType(tSType);
}


}