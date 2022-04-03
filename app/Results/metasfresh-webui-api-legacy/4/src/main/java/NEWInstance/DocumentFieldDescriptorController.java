package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFieldDescriptorController {

 private DocumentFieldDescriptor documentfielddescriptor;

 private DocumentFieldDescriptor documentfielddescriptor;


@GetMapping
("/isSupportZoomInto")
public boolean isSupportZoomInto(){
  return documentfielddescriptor.isSupportZoomInto();
}


@GetMapping
("/isAllowShowPassword")
public boolean isAllowShowPassword(){
  return documentfielddescriptor.isAllowShowPassword();
}


}