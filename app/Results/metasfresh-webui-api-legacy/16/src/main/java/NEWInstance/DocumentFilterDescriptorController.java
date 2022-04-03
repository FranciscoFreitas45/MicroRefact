package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentFilterDescriptorController {

 private DocumentFilterDescriptor documentfilterdescriptor;

 private DocumentFilterDescriptor documentfilterdescriptor;


@GetMapping
("/builder")
public Builder builder(){
  return documentfilterdescriptor.builder();
}


}