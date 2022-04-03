package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlDocumentEntityDataBindingDescriptorController {

 private SqlDocumentEntityDataBindingDescriptor sqldocumententitydatabindingdescriptor;

 private SqlDocumentEntityDataBindingDescriptor sqldocumententitydatabindingdescriptor;


@GetMapping
("/isSingleKey")
public boolean isSingleKey(){
  return sqldocumententitydatabindingdescriptor.isSingleKey();
}


@GetMapping
("/builder")
public Builder builder(){
  return sqldocumententitydatabindingdescriptor.builder();
}


@GetMapping
("/setDocumentsRepository")
public Builder setDocumentsRepository(@RequestParam(name = "documentsRepository") DocumentsRepository documentsRepository){
  return sqldocumententitydatabindingdescriptor.setDocumentsRepository(documentsRepository);
}


@GetMapping
("/setTableName")
public Builder setTableName(@RequestParam(name = "sqlTableName") String sqlTableName){
  return sqldocumententitydatabindingdescriptor.setTableName(sqlTableName);
}


}