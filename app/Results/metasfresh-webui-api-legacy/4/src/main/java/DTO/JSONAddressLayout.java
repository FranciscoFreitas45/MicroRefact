package DTO;
 import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.address.AddressLayout;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
public class JSONAddressLayout implements Serializable{

 private  List<JSONDocumentLayoutElement> elements;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


public JSONAddressLayout of(AddressLayout layout,JSONDocumentLayoutOptions options){
    return new JSONAddressLayout(layout, options);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("layout",layout);
.queryParam("options",options);
JSONAddressLayout aux = restTemplate.getForObject(builder.toUriString(),JSONAddressLayout.class);
return aux;
}


}