package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlDocumentFieldDataBindingDescriptorController {

 private SqlDocumentFieldDataBindingDescriptor sqldocumentfielddatabindingdescriptor;

 private SqlDocumentFieldDataBindingDescriptor sqldocumentfielddatabindingdescriptor;


@GetMapping
("/castOrNull")
public SqlDocumentFieldDataBindingDescriptor castOrNull(@RequestParam(name = "descriptor") DocumentFieldDataBindingDescriptor descriptor){
  return sqldocumentfielddatabindingdescriptor.castOrNull(descriptor);
}


@GetMapping
("/builder")
public Builder builder(){
  return sqldocumentfielddatabindingdescriptor.builder();
}


@GetMapping
("/setFieldName")
public Builder setFieldName(@RequestParam(name = "fieldName") String fieldName){
  return sqldocumentfielddatabindingdescriptor.setFieldName(fieldName);
}


@GetMapping
("/isVirtualColumn")
public boolean isVirtualColumn(){
  return sqldocumentfielddatabindingdescriptor.isVirtualColumn();
}


}