package DTO;
 import java.util.ArrayList;
import java.util.List;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementDescriptor;
import de.metas.util.Check;
import de.metas.util.GuavaCollectors;
public class AddressLayout {

 private  List<DocumentLayoutElementDescriptor> elements;

 private  List<DocumentLayoutElementDescriptor.Builder> elementBuilders;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<DocumentLayoutElementDescriptor> getElements(){
    return elements;
}


public Builder builder(){
    return new Builder();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))

Builder aux = restTemplate.getForObject(builder.toUriString(),Builder.class);
return aux;
}


}