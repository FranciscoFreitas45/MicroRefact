package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class AddressDescriptorFactoryImpl implements AddressDescriptorFactory{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public AddressDescriptor getAddressDescriptor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAddressDescriptor"))
  AddressDescriptor aux = restTemplate.getForObject(builder.toUriString(), AddressDescriptor.class);

 return aux;
}


}