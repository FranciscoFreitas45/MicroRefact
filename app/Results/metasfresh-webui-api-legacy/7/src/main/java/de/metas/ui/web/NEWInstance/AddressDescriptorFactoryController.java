package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AddressDescriptorFactoryController {

 private AddressDescriptorFactory addressdescriptorfactory;


@GetMapping
("/getAddressDescriptor")
public AddressDescriptor getAddressDescriptor(){
  return addressdescriptorfactory.getAddressDescriptor();
}


}