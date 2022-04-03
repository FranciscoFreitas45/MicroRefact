package DTO;
 import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.descriptor.DocumentEntityDescriptor;
public class AddressDescriptor {

 public  DocumentId DocumentTypeId;

 private  DocumentEntityDescriptor entityDescriptor;

 private  AddressLayout layout;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public AddressLayout getLayout(){
    return layout;
}


public DocumentEntityDescriptor getEntityDescriptor(){
    return entityDescriptor;
}


public AddressDescriptor of(DocumentEntityDescriptor entityDescriptor,AddressLayout layout){
    return new AddressDescriptor(entityDescriptor, layout);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("entityDescriptor",entityDescriptor);
.queryParam("layout",layout);
AddressDescriptor aux = restTemplate.getForObject(builder.toUriString(),AddressDescriptor.class);
return aux;
}


}