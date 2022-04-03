package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgFormFieldEntityController {

 private CgFormFieldEntity cgformfieldentity;

 private CgFormFieldEntity cgformfieldentity;


@PutMapping
("/setFieldName")
public void setFieldName(@RequestParam(name = "fieldName") java.lang.String fieldName){
cgformfieldentity.setFieldName(fieldName);
}


@PutMapping
("/setDictField")
public void setDictField(@RequestParam(name = "dictField") java.lang.String dictField){
cgformfieldentity.setDictField(dictField);
}


}